package com.catchstyle.api.ranking.service;

import com.catchstyle.api.ranking.dto.RankingResponse;
import com.catchstyle.api.ranking.entity.RankingSummary;
import com.catchstyle.api.ranking.entity.SearchLog;
import com.catchstyle.api.ranking.repository.RankingSummaryRepository;
import com.catchstyle.api.ranking.repository.SearchLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RankingService {
    //쓰기용
    private final RankingSummaryRepository rankingSummaryRepository;
    //읽기용
    private final SearchLogRepository searchLogRepository;

    //기능 1. 컨트롤러에서 받은 새 검색어와 세션 id를 db에 저장
    @Transactional
    public void saveLog(String keyword,String sessionId){
        SearchLog entity = new SearchLog(keyword, sessionId);
        searchLogRepository.save(entity);
    }


// 기능 2: 매일 원본 데이터를 요약 테이블로 밀어넣는 갱신 로직
    @Transactional
    public void updateRankings() {
        List<Object[]> results = searchLogRepository.findTopKeywordsLast72Hours();
        if (results.size() < 5) return; //72시간 동안 업데이트 된게 5개 미만이면 그대로 둠

        // 트랜잭션 내에서 기존 테이블의 과거 랭킹을 일괄 삭제
        rankingSummaryRepository.deleteAllInBatch();

        // 새로운 상위 5개 데이터 삽입
        List<RankingSummary> newRankings = new ArrayList<>();

        for (Object[] result : results) {
            String keyword = (String) result[0];
            Long count = ((Number) result[1]).longValue();
            newRankings.add(new RankingSummary(keyword, count));
        }
        //업데이트 된 랭킹을 테이블에 저장
        rankingSummaryRepository.saveAll(newRankings);
    }



    //기능 3. 컨트롤러가 호출하면 RankingSummary에서 순위 데이터 Entity 꺼내고
    // Dto 형태로 반환
    @Transactional
    public RankingResponse getRankings(){

        //1.db에서 조회
        List<RankingSummary> rankingSummaries = rankingSummaryRepository.findTop5ByOrderBySearchCountDesc();

        //2.변환할 빈 상자(DTO List) 준비
        List<RankingResponse.RankingItem> rankingItems = new ArrayList<>();

        //3.Entity->DTO 변환 및 순위(Rank) 부여
        int currentRank=1;
        for(RankingSummary entity: rankingSummaries){
            RankingResponse.RankingItem item=new RankingResponse.RankingItem(
                    currentRank++,
                    entity.getKeyword(),
                    entity.getSearchCount()
            );
            rankingItems.add(item);
        }

        //4.현재 시간과 DTO List를 최종 응답 상자(RankingResponse)에 담아서 리턴
        return new RankingResponse(LocalDateTime.now(), rankingItems);
    }





}
