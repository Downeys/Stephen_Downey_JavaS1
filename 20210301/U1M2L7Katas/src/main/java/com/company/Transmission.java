package com.company;

import java.util.Objects;

public class Transmission {
    private int numGears;
    private int currentGear;
    private String driveType;

    public Transmission(){};

    public Transmission(int numGears, int currentGear, String driveType) {
        this.numGears = numGears;
        this.currentGear = currentGear;
        this.driveType = driveType;
    }

    public int getNumGears() {
        return numGears;
    }

    public void setNumGears(int numGears) {
        this.numGears = numGears;
    }

    public int getCurrentGear() {
        return currentGear;
    }

    public void setCurrentGear(int currentGear) {
        this.currentGear = currentGear;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transmission that = (Transmission) o;
        return numGears == that.numGears && currentGear == that.currentGear && Objects.equals(driveType, that.driveType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numGears, currentGear, driveType);
    }

    @Override
    public String toString() {
        return "Transmission{" +
                "numGears=" + numGears +
                ", currentGear=" + currentGear +
                ", driveType='" + driveType + '\'' +
                '}';
    }
}
