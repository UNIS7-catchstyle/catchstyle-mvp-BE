package com.catchstyle.api.ranking.controller;

import com.catchstyle.api.ranking.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internal/rankings")
@RequiredArgsConstructor
public class RankingCronController {

    private final RankingService rankingService;

    // application.yml 또는 Render 환경변수에서 값을 가져옴
    @Value("${cron.secret}")
    private String serverCronSecret;

    @PostMapping("/update")
    public ResponseEntity<String> forceUpdate(
            @RequestHeader(value = "X-Cron-Secret", required = false) String requestSecret) {

        // 1. 헤더에 담긴 키와 서버에 설정된 키가 일치하는지 확인
        if (requestSecret == null || !requestSecret.equals(serverCronSecret)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

        // 2. 일치하면 랭킹 업데이트 실행
        rankingService.updateRankings();
        return ResponseEntity.ok("Success");
    }
}
