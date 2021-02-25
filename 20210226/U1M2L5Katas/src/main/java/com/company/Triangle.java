package com.company;

import com.company.interfaces.Shape;

public class Triangle implements Shape {
    private double side1;
    private double side2;
    private double side3;
    private final double DEFAULT_SIDE_LENGTH = 1;

    public Triangle(){
        side1 = DEFAULT_SIDE_LENGTH;
        side2 = DEFAULT_SIDE_LENGTH;
        side3 = DEFAULT_SIDE_LENGTH;
    }

    public Triangle(double side1, double side2, double side3) {
        if(side1 + side2 < side3 && side2 + side3 < side1 && side3 + side1 < side2){
            this.side1 = side1;
            this.side2 = side2;
            this.side3 = side3;
        }
    }

    @Override
    public double perimeter() {
        return side1 + side2 + side3;
    }

    @Override
    public double area() {
        double halfOfPerimeter = perimeter() / 2;
        return Math.sqrt(halfOfPerimeter * (halfOfPerimeter - side1) * (halfOfPerimeter - side2)* (halfOfPerimeter - side3));
    }

}
