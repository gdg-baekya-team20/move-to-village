package com.gdgteam7.idohyangchon.repository;

import com.gdgteam7.idohyangchon.domain.RuralArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RuralAreaRepository extends JpaRepository<RuralArea, Long> {
    @Query(value = "SELECT * FROM rural_area " +
            "WHERE rural_name != :ruralName " +
            "ORDER BY RAND() LIMIT 1",
            nativeQuery = true)
    RuralArea findRandomExcludingSuch(@Param("ruralName") String ruralName);

    RuralArea findByRuralName(String ruralName);
}
