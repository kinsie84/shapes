package com.peter.shapes.controllers;

import com.peter.shapes.models.Circle;
import com.peter.shapes.services.CircleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shape/circle")
public class CircleController {

    @Autowired
    private CircleService circleService;


    @PostMapping
    public Circle create(@RequestBody Circle circle) throws Exception {
        if (circle.getRadius() == 0.0) {
            throw new Exception("Cannot create a circle without a radius");
        }
        return circleService.createCircle(new Circle(circle.getRadius()));
    }

    @GetMapping()
    public List<Circle> getAll() {
        return circleService.getAllCircles();
    }

    @GetMapping("{/id}")
    public Optional<Circle> get(@PathVariable("id") long id) {
        return circleService.getCircle(id);
    }

    @PutMapping("/{id}")
    public Circle update(@PathVariable("id") long id, @RequestBody Circle circle) throws Exception {
        if (circle.getRadius() == 0.0) {
            throw new Exception("Cannot create a circle without a radius");
        }
        return circleService.updateCircle(id, circle);
    }

    @DeleteMapping("{/id}")
    public void delete(@PathVariable("id") long id) {
        circleService.deleteCircle(id);
    }

}
