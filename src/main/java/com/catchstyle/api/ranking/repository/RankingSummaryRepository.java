package com.catchstyle.api.ranking.repository;

import com.catchstyle.api.ranking.entity.RankingSummary;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
// 이곳은 계산된 결과를 저장하고, 덮어쓰고, 프론트엔드에게 꺼내주는 역할
//계산된 상위 5개 검색어만 저장하는 테이블 RankingSummary를 관리하는 repository


public interface RankingSummaryRepository extends JpaRepository<RankingSummary, Long> {
    // 프론트엔드 조회용
    List<RankingSummary> findTop5ByOrderBySearchCountDesc();
}