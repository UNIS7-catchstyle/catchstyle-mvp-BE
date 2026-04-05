package com.catchstyle.api.ranking.repository;

import com.catchstyle.api.ranking.entity.RankingSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankingSummaryRepository extends JpaRepository<RankingSummary, Long> {
    // Spring data JPA 명명 규칙:
    // 상위 5개를 찾는데, searchCount 기준으로 내림차순(Desc) 정렬
    List<RankingSummary> findTop5ByOrderBySearchCountDesc();
}
