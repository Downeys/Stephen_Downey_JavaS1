package com.company;

import java.util.Objects;

public class LivingRoom extends Room {
    private boolean ceilingFanOn;
    private boolean fireplaceOn;

    public LivingRoom() {
    }

    public LivingRoom(double roomLength, double roomWidth, String flooringType,
                      double ceilingHeight) {
        this.squareFootage = roomWidth * roomLength;
        this.roomLength = roomLength;
        this.roomWidth = roomWidth;
        this.flooringType = flooringType;
        this.ceilingHeight = ceilingHeight;
        lightOn = false;
        doorOpen = false;
        ceilingFanOn = false;
        fireplaceOn = false;
    }

    public boolean toggleCeilingFan(){
        if (ceilingFanOn) {
            System.out.println("Turning the living room ceiling fan off.");
            ceilingFanOn = false;
        } else {
            System.out.println("Turning the living room ceiling fan on.");
            ceilingFanOn = true;
        }
        return ceilingFanOn;
    }

    public boolean toggleFireplace(){
        if (fireplaceOn) {
            System.out.println("Turning the living room ceiling fan off.");
            fireplaceOn = false;
        } else {
            System.out.println("Turning the living room ceiling fan on.");
            fireplaceOn = true;
        }
        return fireplaceOn;
    }

    @Override
    protected boolean toggleDoor() {
        if (this.isDoorOpen()) {
            System.out.println("Closing the living room door.");
            doorOpen = false;
        } else {
            System.out.println("Opening the living room door.");
            doorOpen = true;
        }
        return doorOpen;
    }

    @Override
    protected boolean toggleLights() {
        if (this.isLightOn()) {
            System.out.println("Turning the living room light off.");
            lightOn = false;
        } else {
            System.out.println("Turning the living room light on.");
            lightOn = true;
        }
        return lightOn;
    }

    public boolean isCeilingFanOn() {
        return ceilingFanOn;
    }

    public void setCeilingFanOn(boolean ceilingFanOn) {
        this.ceilingFanOn = ceilingFanOn;
    }

    public boolean isFireplaceOn() {
        return fireplaceOn;
    }

    public void setFireplaceOn(boolean fireplaceOn) {
        this.fireplaceOn = fireplaceOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LivingRoom that = (LivingRoom) o;
        return ceilingFanOn == that.ceilingFanOn && fireplaceOn == that.fireplaceOn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ceilingFanOn, fireplaceOn);
    }

    @Override
    public String toString() {
        return "LivingRoom{" +
                "ceilingFanOn=" + ceilingFanOn +
                ", fireplaceOn=" + fireplaceOn +
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
