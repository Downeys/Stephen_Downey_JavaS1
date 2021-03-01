package com.company;

import com.company.interfaces.Vehicle;

import java.util.Objects;

public class Car implements Vehicle {
    private String make;
    private String model;
    private int milesTraveled;

    public Car(){};

    public Car(String make, String model, int milesTraveled) {
        this.make = make;
        this.model = model;
        this.milesTraveled = milesTraveled;
    }

    @Override
    public void drive(int miles) {
        System.out.println("The car drives " + miles + " miles.");
        milesTraveled += miles;
    }

    @Override
    public void displayMilesTraveled() {
        System.out.println("The car has traveled a total of " + milesTraveled + " miles.");
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMilesTraveled() {
        return milesTraveled;
    }

    public void setMilesTraveled(int milesTraveled) {
        this.milesTraveled = milesTraveled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return milesTraveled == car.milesTraveled && Objects.equals(make, car.make) && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, milesTraveled);
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", milesTraveled=" + milesTraveled +
                '}';
    }

}
