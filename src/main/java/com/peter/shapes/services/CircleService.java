package com.peter.shapes.services;

import com.peter.shapes.models.Circle;
import com.peter.shapes.models.Shape;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CircleService {

    Circle createCircle(Circle circle);

    Optional<Circle> getCircle(long id);

    List<Circle> getAllCircles();

    Circle updateCircle(long id, Circle circle);

    void deleteCircle(long id);

    void generateCircles(List<Shape> circles, int end);

    List<Circle> getAllCirclesByArea(double area);
}
