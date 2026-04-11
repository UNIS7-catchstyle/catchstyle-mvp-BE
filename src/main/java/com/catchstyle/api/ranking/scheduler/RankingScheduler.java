package com.catchstyle.api.ranking.scheduler;

import com.catchstyle.api.ranking.service.RankingService;
import lombok.RequiredArgsConstructor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//scheduler:시간에 맞춰 알람을 울리는 역할만 함. 내부에 RankingService를 주입받아
//정각마다 rankingService.renewRankings() 메서드 실행시킴
@Component
@RequiredArgsConstructor
public class RankingScheduler {
    private final RankingService rankingService;

    //n시간마다 RankingService의 기능2를 자동 실행하는 트리거 클래스
    //cron 표현식:"초 분 시 일 월 요일" 순서
    //하루 한번 실행을 의미
    @Scheduled(cron="0 0 0 * * *")
    public void rankingUpdate() {
        rankingService.updateRankings();

    }

}
