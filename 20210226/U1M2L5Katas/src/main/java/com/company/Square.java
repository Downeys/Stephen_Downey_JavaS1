package com.company;

import com.company.interfaces.Shape;

public class Square implements Shape {
    private double lengthOfSides;
    private final double DEFAULT_SIDE_LENGTH = 1;

    public Square(){
        lengthOfSides = DEFAULT_SIDE_LENGTH;
    }

    public Square(double lengthOfSides) {
        if(lengthOfSides > 0){
            this.lengthOfSides = lengthOfSides;
        }
    }

    @Override
    public double perimeter() {
        return lengthOfSides * 4;
    }

    @Override
    public double area() {
        return lengthOfSides * lengthOfSides;
    }
}
