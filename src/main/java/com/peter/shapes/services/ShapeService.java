package com.peter.shapes.services;

import com.peter.shapes.models.Shape;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShapeService {

    List<Shape> generateShapes();

    List<Shape> generateShapes(int number);

    List<Shape> getAllShapesSortedByAreaAscending();

    List<Shape> getAllShapesSortedByAreaDescending();

    Shape findShapeWithAreaClosestToMean();

    List<Shape> findShapesWithAreaClosestToMean();

}
