package com.company;

import java.util.Objects;

public class Sedan extends Car{
    private int numDoors;
    private boolean poweredWindows;

    public Sedan(){};

    public Sedan(int numDoors, boolean poweredWindows) {
        this.numDoors = numDoors;
        this.poweredWindows = poweredWindows;
    }

    public int getNumDoors() {
        return numDoors;
    }

    public void setNumDoors(int numDoors) {
        this.numDoors = numDoors;
    }

    public boolean isPoweredWindows() {
        return poweredWindows;
    }

    public void setPoweredWindows(boolean poweredWindows) {
        this.poweredWindows = poweredWindows;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Sedan sedan = (Sedan) o;
        return numDoors == sedan.numDoors && poweredWindows == sedan.poweredWindows;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numDoors, poweredWindows);
    }

    @Override
    public String toString() {
        return "Sedan{" +
                "numDoors=" + numDoors +
                ", poweredWindows=" + poweredWindows +
                '}';
    }
}
