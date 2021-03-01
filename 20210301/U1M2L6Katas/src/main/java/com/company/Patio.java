package com.company;

public class Patio extends Room{

    public Patio() {
    }

    public Patio(double roomLength, double roomWidth, String flooringType,
                 double ceilingHeight) {
        this.squareFootage = roomWidth * roomLength;
        this.roomLength = roomLength;
        this.roomWidth = roomWidth;
        this.flooringType = flooringType;
        this.ceilingHeight = ceilingHeight;
        lightOn = false;
        doorOpen = false;
    }

    public void applySalt(){
        System.out.println("Applying salt to help prevent ice.");
    }

    public void sweepSidewalk(){
        System.out.println("The patio performs self sweeping function.");
    }

    @Override
    protected boolean toggleDoor() {
        if (this.isDoorOpen()) {
            System.out.println("Closing the patio door.");
            doorOpen = false;
        } else {
            System.out.println("Opening the patio door.");
            doorOpen = true;
        }
        return doorOpen;
    }

    @Override
    protected boolean toggleLights() {
        if (this.isLightOn()) {
            System.out.println("Turning the patio light off.");
            lightOn = false;
        } else {
            System.out.println("Turning the patio light on.");
            lightOn = true;
        }
        return lightOn;
    }

    @Override
    public String toString() {
        return "Patio{}";
    }
}
