package com.peter.shapes.services;

import com.peter.shapes.models.Shape;
import com.peter.shapes.models.Square;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShapeServiceImpl implements ShapeService {

    @Autowired
    private CircleService circleService;
    @Autowired
    private SquareService squareService;

    /* creates 100 shapes in the database */
    @Override
    public List<Shape> generateShapes() {
        return generateShapes(100);
    }

    /* generates the specified number of shapes
    splits the shapes by circle and squares
    if the number is odd then an extra circle is created
     */
    @Override
    public List<Shape> generateShapes(int number) {
        if(number > 0){
            List<Shape> shapes = new ArrayList<>(number);
            if(number > 1){
                boolean odd = number % 2 != 0;
                int half = (number/2);

            }
        }
        return null;
    }

    @Override
    public Shape findShapeWithAreaClosestToMean() {
        return null;
    }

    @Override
    public List<Shape> findShapesWithAreaClosestToMean() {
        return null;
    }

    /* gets a list of shapes from the db and sorts the list by area ascending*/
    @Override
    public List<Shape> getAllShapesSortedByAreaAscending() {
        return null;
    }

    /* gets a list of shapes from the db and sorts the list by area descending*/
    @Override
    public List<Shape> getAllShapesSortedByAreaDescending() {
        return null;
    }
}
