package com.catchstyle.api.ranking.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)//JPA 필수 생성자
public class RankingSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //연예인 이름
    @Column(columnDefinition = "TEXT", unique=true)
    private String keyword;

    //누적 검색 횟수
    private Long searchCount;

    //최근 갱신 시간
    private LocalDateTime updatedAt;


    public RankingSummary(String keyword,Long searchCount) {
        this.keyword = keyword;
        this.searchCount = searchCount;
        this.updatedAt = LocalDateTime.now();

    }
}
