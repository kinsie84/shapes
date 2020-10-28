package com.peter.shapes.controllers;

import com.peter.shapes.models.Shape;
import com.peter.shapes.services.CircleService;
import com.peter.shapes.services.ShapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shape")
public class ShapeController {

    @Autowired
    private ShapeService shapeService;

    @GetMapping
    public List<Shape> generateShapes(){
        return shapeService.generateShapes();
    }

    @GetMapping("/sort")
    public List<Shape> getSortedShapes() {
        return shapeService.getAllShapesSortedByAreaDescending();
    }

    @GetMapping("/mean")
    public Shape getShapeWithAreaClosestToMean(){
        return shapeService.findShapeWithAreaClosestToMean();
    }

    @GetMapping("/means")
    public List<Shape> getShapesWithAreaClosesToMean(){
        return shapeService.findShapesWithAreaClosestToMean();
    }


}
