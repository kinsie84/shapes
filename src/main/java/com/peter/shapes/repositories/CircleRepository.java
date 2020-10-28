package com.peter.shapes.repositories;

import com.peter.shapes.models.Circle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CircleRepository extends JpaRepository<Circle, Long> {

    @Query("select c from Circle c where c.area > (:areaParam - 1.0) and c.area < (:areaParam + 1.0)")
    List<Circle> findCirclesByAreas(@Param("areaParam") double areaParam);
}
