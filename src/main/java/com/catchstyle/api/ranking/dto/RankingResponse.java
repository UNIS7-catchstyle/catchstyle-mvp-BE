package com.catchstyle.api.ranking.dto;
//서버->클라이언트
import java.time.LocalDateTime;
import java.util.List;

public record RankingResponse (
        LocalDateTime updatedAt,
        List<RankingItem> rankings
){
    public record RankingItem (
        int rank,
        String keyword,
        Long searchCount
    ){}
}
