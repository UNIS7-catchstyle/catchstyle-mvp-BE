package com.catchstyle.api.ranking.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)//JPA 필수 생성자
public class SearchLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //연예인 이름
    @Column(columnDefinition = "TEXT")
    private String keyword;

    //검색 시간
    private LocalDateTime createdAt;

    //유니크 방문자 통계 분석용 식별자
    private String sessionId;

    //Service 계층에서 sessionId를 함께 넘겨받아 객체 생성
    public SearchLog(String keyword,String sessionId) {
        this.keyword = keyword;
        this.sessionId=sessionId;
        this.createdAt = LocalDateTime.now();
    }


}
