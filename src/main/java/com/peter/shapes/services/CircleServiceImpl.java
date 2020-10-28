package com.peter.shapes.services;

import com.peter.shapes.models.Circle;
import com.peter.shapes.models.Shape;
import com.peter.shapes.repositories.CircleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CircleServiceImpl implements CircleService{

    @Autowired
    private CircleRepository circleRepository;

    @Override
    public Circle createCircle(Circle circle){
        return circleRepository.saveAndFlush(circle);
    }

    @Override
    public Optional<Circle> getCircle(long id) {
        return circleRepository.findById(id);
    }

    @Override
    public List<Circle> getAllCircles() {
        return circleRepository.findAll();
    }

    @Override
    public Circle updateCircle(long id, Circle circle) {
        Optional<Circle> databaseVersion = getCircle(id);
        if(databaseVersion.isPresent()) {
            BeanUtils.copyProperties(circle, databaseVersion);
            return circleRepository.saveAndFlush(circle);
        }
        throw new NullPointerException();
    }

    @Override
    public void deleteCircle(long id) {
        Optional<Circle> circle = getCircle(id);
        circle.ifPresent(value -> circleRepository.delete(value));
    }

    /*
     * Takes in a List<Shape> along with an end point. Creates and persists new
     * circle objects with randomly generated widths adds them to the list
     */
    @Override
    public void generateCircles(List<Shape> circles, int end) {
        if(circles != null){
            for(int i=0; i <= end; i++){
                circles.add(createCircle(new Circle(Math.random() * 50.0)));
            }
        }

    }

    /*
     * Takes a double and queries the database to find matches + or - 1.0 on
     * area field
     */
    @Override
    public List<Circle> getAllCirclesByArea(double area) {
        return circleRepository.findCirclesByAreas(area);
    }
}
