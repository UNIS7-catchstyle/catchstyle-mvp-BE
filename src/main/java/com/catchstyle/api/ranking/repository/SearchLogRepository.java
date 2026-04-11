package com.catchstyle.api.ranking.repository;

import com.catchstyle.api.ranking.entity.SearchLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface SearchLogRepository extends JpaRepository<SearchLog, Long> {
    //JPQL을 사용해 검색어와 해당 검색어의 개수를 DTO 객체에 담아 반환
    // 최근 72시간 동안의 검색 로그를 집계하여 상위 5개를 리스트에 넣음(다 가져오면 시간 오래 걸림)
    @Query(value = "SELECT keyword, COUNT(*) as count FROM search_log " +
            "WHERE created_at >= NOW() - INTERVAL '72 hours' " +
            "GROUP BY keyword ORDER BY count DESC LIMIT 5", nativeQuery = true)
    List<Object[]> findTopKeywordsLast72Hours();

}
