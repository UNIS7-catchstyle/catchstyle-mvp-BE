package com.catchstyle.api.ranking.repository;

import com.catchstyle.api.ranking.entity.SearchLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface SearchLogRepository extends JpaRepository<SearchLog, Long> {
    //JPQL을 사용해 검색어와 해당 검색어의 개수를 DTO 객체에 담아 반환
    @Query("SELECT s.keyword, COUNT(s) FROM SearchLog s WHERE s.createdAt>= :since GROUP BY s.keyword")
    //[0]는 키워드, [1]는 개수가 담김
    List<Object[]> countKeywordsSince(LocalDateTime since);
}
