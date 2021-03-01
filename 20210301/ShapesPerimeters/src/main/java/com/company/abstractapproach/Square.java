package com.company.abstractapproach;

public class Square extends Shape{

    private double side;

    public Square(double side) {
        this.side = side;
    }

    public double area() {
        return side * side;
    }

    public double perimeter() {
        return 4 * side;
    }

    public double getSide() {
        return side;
    }

}
