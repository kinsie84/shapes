package com.peter.shapes.repositories;

import com.peter.shapes.models.Square;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SquareRepository extends JpaRepository<Square,Long> {

    @Query("select s from Square s where s.area > (:areaParam - 1.0) and s.area < (:areaParam + 1.0)")
    List<Square> findSquaresByArea(@Param("areaParam") double areaParam);
}
