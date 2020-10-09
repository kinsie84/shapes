package com.peter.shapes.models;

import javax.persistence.Entity;

@Entity
public class Square extends Shape {

    private double width;

    protected Square() {
        super();
    }

    public Square(double width) {
        this.width = width;
        this.calculateArea();
    }

    @Override
    public void calculateArea() {
        double area = width * width;
        setArea(area);
    }

    @Override
    public String toString() {
        return String.format("Square: Size = %f, Area = %f", this.width, this.area);
    }

    private void setArea(double area) {
        this.area = area;
    }

    @Override
    public Double getArea() {
        return this.area;
    }

    public double getWidth() {
        return width;
    }
}
