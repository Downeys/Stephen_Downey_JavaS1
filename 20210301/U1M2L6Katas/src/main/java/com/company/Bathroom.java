package com.company;

import java.util.Objects;

public class Bathroom extends Room {
    private boolean sinkOn;
    private boolean showerOn;

    public Bathroom() {
    }

    public Bathroom(double roomLength, double roomWidth, String flooringType,
                    double ceilingHeight) {
        this.squareFootage = roomWidth * roomLength;
        this.roomLength = roomLength;
        this.roomWidth = roomWidth;
        this.flooringType = flooringType;
        this.ceilingHeight = ceilingHeight;
        lightOn = false;
        doorOpen = false;
        sinkOn = false;
        showerOn = false;
    }

    public boolean toggleSink(){
        if (sinkOn) {
            System.out.println("Turning the bathroom sink off.");
            sinkOn = false;
        } else {
            System.out.println("Turning the bathroom sink on.");
            sinkOn = true;
        }
        return sinkOn;
    }

    public void flushToilet(){
        System.out.println("Flushing toilet.");
    }

    public boolean toggleShower(){
        if (showerOn) {
            System.out.println("Turning the shower off.");
            showerOn = false;
        } else {
            System.out.println("Turning the shower on.");
            showerOn = true;
        }
        return showerOn;
    }

    @Override
    protected boolean toggleDoor() {
        if (this.isDoorOpen()) {
            System.out.println("Closing the bathroom door.");
            doorOpen = false;
        } else {
            System.out.println("Opening the bathroom door.");
            doorOpen = true;
        }
        return doorOpen;
    }

    @Override
    protected boolean toggleLights() {
        if (this.isLightOn()) {
            System.out.println("Turning the bathroom light off.");
            lightOn = false;
        } else {
            System.out.println("Turning the bathroom light on.");
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

    public boolean isShowerOn() {
        return showerOn;
    }

    public void setShowerOn(boolean showerOn) {
        this.showerOn = showerOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bathroom bathroom = (Bathroom) o;
        return sinkOn == bathroom.sinkOn && showerOn == bathroom.showerOn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sinkOn, showerOn);
    }

    @Override
    public String toString() {
        return "Bathroom{" +
                "sinkOn=" + sinkOn +
                ", showerOn=" + showerOn +
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
