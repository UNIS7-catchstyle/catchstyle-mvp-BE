package com.catchstyle.api.ranking.controller;

import com.catchstyle.api.ranking.dto.RankingResponse;
import com.catchstyle.api.ranking.dto.SearchRequest;
import com.catchstyle.api.ranking.service.RankingService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;

    //POST /api/v1/search
    //프론트엔드에서 검색어 받음
    //RankingService의 save() 사용?
    @PostMapping("/search")
    public ResponseEntity<Void> saveKeyword(
            @Valid @RequestBody SearchRequest searchRequest,
            //프엔이 헤더에 키값으로 보낸 데이터를 변수에 담음
            @RequestHeader("X-Session-Id") String sessionId
            ){
        //DTO의 데이터와 세션ID를 모두 service로 넘김
            rankingService.saveLog(searchRequest.keyword(),sessionId);
            return ResponseEntity.ok().build();
    }

    //GET /api.v1/rankings
    //프론트엔드에 순위 데이터 응답(RankingResponse에는 순위 데이터가 들어있고, HTTP 상태코드등과 함께 ResponseEntity 객체로 포장함
    @GetMapping("/rankings")
    public ResponseEntity<RankingResponse> getRankings() {
        //Service에서 조립된 dto 형태 데이터를 받음
        RankingResponse rankData=rankingService.getRankings();
        //상태 코드와 함께 반환
        return ResponseEntity.ok(rankData);
    }

    //cron-job.org 접근용 엔드포인트
    @GetMapping("/ping")
    public ResponseEntity<String> keepAlive() {
        return ResponseEntity.ok("pong");
    }

}
