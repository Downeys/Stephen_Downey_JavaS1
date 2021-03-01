package com.company;

import java.util.Objects;

public class Kitchen extends Room {
    private boolean sinkOn;

    public Kitchen() {
    }

    public Kitchen(double roomLength, double roomWidth, String flooringType,
                   double ceilingHeight) {
        this.squareFootage = roomWidth * roomLength;
        this.roomLength = roomLength;
        this.roomWidth = roomWidth;
        this.flooringType = flooringType;
        this.ceilingHeight = ceilingHeight;
        lightOn = false;
        doorOpen = false;
        sinkOn = false;
    }

    public boolean toggleSink(){
        if (sinkOn) {
            System.out.println("Turning the kitchen sink off.");
            sinkOn = false;
        } else {
            System.out.println("Turning the kitchen sink on.");
            sinkOn = true;
        }
        return sinkOn;
    }

    public void mopFloor(){
        System.out.println("The kitchen performs its self-mopping function.");
    }

    @Override
    protected boolean toggleDoor() {
        if (this.isDoorOpen()) {
            System.out.println("Closing the kitchen door.");
            doorOpen = false;
        } else {
            System.out.println("Opening the kitchen door.");
            doorOpen = true;
        }
        return doorOpen;
    }

    @Override
    protected boolean toggleLights() {
        if (this.isLightOn()) {
            System.out.println("Turning the kitchen light off.");
            lightOn = false;
        } else {
            System.out.println("Turning the kitchen light on.");
            lightOn = true;
        }
        return lightOn;
    }

    public boolean isSinkOn() {
        return sinkOn;
    }

    public void setSinkOn(boolean sinkOn) {
        this.sinkOn = sinkOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Kitchen kitchen = (Kitchen) o;
        return sinkOn == kitchen.sinkOn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sinkOn);
    }

    @Override
    public String toString() {
        return "Kitchen{" +
                "sinkOn=" + sinkOn +
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

