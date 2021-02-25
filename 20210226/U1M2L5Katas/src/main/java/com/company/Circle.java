package com.company;

import com.company.interfaces.Shape;

public class Circle implements Shape {
    private double radius;
    private final double DEFAULT_RADIUS = 1;

    public Circle(){
        radius = DEFAULT_RADIUS;
    }

    public Circle(double radius) {
        if(radius > 0){
            this.radius = radius;
        }
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }
}
