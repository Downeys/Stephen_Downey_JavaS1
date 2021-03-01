package com.company;

import java.util.Objects;

public class Basement extends Room{
    private boolean sumpPumpOn;

    public Basement() {
    }

    public Basement(double roomLength, double roomWidth, String flooringType,
                    double ceilingHeight) {
        this.squareFootage = roomWidth * roomLength;
        this.roomLength = roomLength;
        this.roomWidth = roomWidth;
        this.flooringType = flooringType;
        this.ceilingHeight = ceilingHeight;
        lightOn = false;
        doorOpen = false;
        sumpPumpOn = false;
    }

    public boolean toggleSumpPump(){
        if (sumpPumpOn) {
            System.out.println("Turning the sump pump off.");
            sumpPumpOn = false;
        } else {
            System.out.println("Turning the sump pump on.");
            sumpPumpOn = true;
        }
        return sumpPumpOn;
    }

    public void storeStuff(){
        System.out.println("Storing stuff.");
    }

    @Override
    protected boolean toggleDoor() {
        if (this.isDoorOpen()) {
            System.out.println("Closing the basement door.");
            doorOpen = false;
        } else {
            System.out.println("Opening the basement door.");
            doorOpen = true;
        }
        return doorOpen;
    }

    @Override
    protected boolean toggleLights() {
        if (this.isLightOn()) {
            System.out.println("Turning the basement light off.");
            lightOn = false;
        } else {
            System.out.println("Turning the basement light on.");
            lightOn = true;
        }
        return lightOn;
    }

    public boolean isSumpPumpOn() {
        return sumpPumpOn;
    }

    public void setSumpPumpOn(boolean sumpPumpOn) {
        this.sumpPumpOn = sumpPumpOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Basement basement = (Basement) o;
        return sumpPumpOn == basement.sumpPumpOn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sumpPumpOn);
    }

    @Override
    public String toString() {
        return "Basement{" +
                "sumpPumpOn=" + sumpPumpOn +
                ", squareFootage=" + squareFootage +
                ", roomLength=" + roomLength +
                ", roomWidth=" + roomWidth +
                ", flooringType='" + flooringType + '\'' +
                ", ceilingHeight=" + ceilingHeight +
                ", doorOpen=" + doorOpen +
                ", lightOn=" + lightOn +
                '}';
    }
}
