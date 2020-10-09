package com.peter.shapes.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Shape {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;
    protected Double area;

    protected Shape() {
        this.id = null;
    }

    public abstract void calculateArea();

    public Double getArea() {
        return this.area;
    }

    public abstract String toString();

    public Long getId() {
        return this.id;
    }

    ;
}
