package com.wmata.repository;

import com.wmata.model.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, String> {

    List<Station> findByLineCode1OrLineCode2OrLineCode3OrLineCode4(
            String lineCode1, String lineCode2, String lineCode3, String lineCode4);

    @Query("SELECT s FROM Station s WHERE s.lineCode1 = :lineCode OR s.lineCode2 = :lineCode OR s.lineCode3 = :lineCode OR s.lineCode4 = :lineCode")
    List<Station> findAllByLineCode(@Param("lineCode") String lineCode);

    List<Station> findByStationNameContainingIgnoreCase(String name);
}