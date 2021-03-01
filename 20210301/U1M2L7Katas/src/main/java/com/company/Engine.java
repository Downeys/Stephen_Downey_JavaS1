package com.company;

import java.util.Objects;

public class Engine {
    private int numCylinders;
    private double cubicCentimeters;
    private String fuelType;
    private double horsePower;
    private boolean on;

    public Engine(){};

    public Engine(int numCylinders, double cubicCentimeters, String fuelType, double horsePower, boolean on) {
        this.numCylinders = numCylinders;
        this.cubicCentimeters = cubicCentimeters;
        this.fuelType = fuelType;
        this.horsePower = horsePower;
        this.on = on;
    }

    public boolean toggleEngine(){
        if(this.on){
            System.out.println("The engine stops.");
            this.on = false;
        }else{
            System.out.println("The engine starts.");
            this.on = true;
        }
        return this.on;
    }

    public int getNumCylinders() {
        return numCylinders;
    }

    public void setNumCylinders(int numCylinders) {
        this.numCylinders = numCylinders;
    }

    public double getCubicCentimeters() {
        return cubicCentimeters;
    }

    public void setCubicCentimeters(double cubicCentimeters) {
        this.cubicCentimeters = cubicCentimeters;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(double horsePower) {
        this.horsePower = horsePower;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return numCylinders == engine.numCylinders && Double.compare(engine.cubicCentimeters, cubicCentimeters) == 0 && Double.compare(engine.horsePower, horsePower) == 0 && on == engine.on && Objects.equals(fuelType, engine.fuelType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numCylinders, cubicCentimeters, fuelType, horsePower, on);
    }

    @Override
    public String toString() {
        return "Engine{" +
                "numCylinders=" + numCylinders +
                ", cubicCentimeters=" + cubicCentimeters +
                ", fuelType='" + fuelType + '\'' +
                ", horsePower=" + horsePower +
                ", on=" + on +
                '}';
    }
}
