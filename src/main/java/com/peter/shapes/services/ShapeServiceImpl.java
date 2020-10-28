package com.peter.shapes.services;

import com.peter.shapes.models.Shape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        if (number > 0) {
            List<Shape> shapes = new ArrayList<>(number);
            if (number > 1) {
                boolean odd = number % 2 != 0;
                int half = (number / 2);
                circleService.generateCircles(shapes, odd ? half : half - 1);
                squareService.generateSquares(shapes, half - 1);
            } else {
                circleService.generateCircles(shapes, 0);
            }
            return shapes;
        }
        return null;
    }

    /* gets a list of shapes from the db and sorts the list by area ascending*/
    @Override
    public List<Shape> getAllShapesSortedByAreaAscending() {
        List<Shape> shapeList = getAllShapes();
        sortShapeByArea(shapeList, false);
        return shapeList;
    }

    /* gets a list of shapes from the db and sorts the list by area descending*/
    @Override
    public List<Shape> getAllShapesSortedByAreaDescending() {
        List<Shape> shapeList = getAllShapes();
        sortShapeByArea(shapeList, true);
        return shapeList;
    }

    @Override
    public Shape findShapeWithAreaClosestToMean() {
        List<Shape> shapeList = getAllShapes();
        if (shapeList.isEmpty()) {
            return null;
        }
        if (shapeList.size() == 1) {
            return shapeList.get(0);
        }
        int mean = findMeanAreaOfShapes(shapeList);

        return searchForMean(shapeList, mean);
    }

    /*
     * Finds the mean area of the list
     * Performs binary search to find the shapes with
     * area that is closest to the mean area (+1 or -1)
     */
    @Override
    public List<Shape> findShapesWithAreaClosestToMean() {
        Shape shape = findShapeWithAreaClosestToMean();
        if (shape == null) {
            return Collections.emptyList();
        }
        double closestArea = Math.round(shape.getArea());
        List<Shape> meanList = new ArrayList<>();

        meanList.addAll(circleService.getAllCirclesByArea(closestArea));
        meanList.addAll(squareService.getAllSquaresByArea(closestArea));

        return meanList;
    }

    private List<Shape> getAllShapes() {
        List<Shape> shapeList = new ArrayList<>();
        shapeList.addAll(circleService.getAllCircles());
        shapeList.addAll(squareService.getAllSquares());
        return shapeList;
    }

    private void sortShapeByArea(List<Shape> shapeList, boolean descending) {
        if (shapeList != null && !shapeList.isEmpty()) {
            if (descending) {
                shapeList.sort(Comparator.comparing(Shape::getArea).reversed());
            } else {
                shapeList.sort(Comparator.comparing(Shape::getArea));
            }
        }
    }

    private int findMeanAreaOfShapes(List<Shape> shapeList) {
        double totalArea = 0;
        for (Shape shape : shapeList) {
            totalArea += shape.getArea();
        }
        return (int) Math.ceil(totalArea / shapeList.size());
    }

    private Shape searchForMean(List<Shape> shapeList, int mean) {
        if (shapeList == null || shapeList.isEmpty()) {
            return null;
        }
        // have to sort list before performing binary search
        sortShapeByArea(shapeList, false);

        int listLen = shapeList.size();

        if (mean < shapeList.get(0).getArea()) {
            return shapeList.get(0);
        }
        if (mean > shapeList.get(listLen - 1).getArea()) {
            return shapeList.get(listLen - 1);
        }

        int start = 0;
        int end = listLen - 1;
        int middle = 0;
        while (start <= end) {

            middle = (start + end) / 2;
            int middleArea = (int) Math.round(shapeList.get(middle).getArea());

            if (mean < middleArea) {
                end = middle - 1;

            } else if (mean > middleArea) {
                start = middle + 1;

            } else {
                return shapeList.get(middle);
            }
        }

        double startDistance = Math.abs(shapeList.get(start).getArea() - mean);
        double endDistance = Math.abs(shapeList.get(end).getArea() - mean);

        return (startDistance > endDistance) ? shapeList.get(end) : shapeList.get(start);

    }
}
