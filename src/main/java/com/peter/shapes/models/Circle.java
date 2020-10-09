package com.peter.shapes.models;

import javax.persistence.Entity;

@Entity
public class Circle extends Shape {

    private double radius;

    protected Circle() {
        super();
    }
    public Circle(double radius) {
        this.radius = radius;
        this.calculateArea();
    }

    @Override
    public Double getArea() {
        return this.area;
    }

    private void setArea(double area) {
        this.area = area;
    }

    @Override
    public void calculateArea() {
        double area = (radius * radius) * Math.PI;
        this.setArea(area);
    }

    @Override
    public String toString() {
        return String.format("Circle with Radius: %f and Area: %f", this.radius, super.area);
    }

    public Double getRadius() {
        return this.radius;
    }
}
