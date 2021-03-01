package com.company;

import java.util.Objects;

public class Bedroom extends Room {
    private boolean nightLightOn;
    private boolean ceilingFanOn;

    public Bedroom() {
    }

    public Bedroom(double roomLength, double roomWidth, String flooringType,
                   double ceilingHeight) {
        this.squareFootage = roomWidth * roomLength;
        this.roomLength = roomLength;
        this.roomWidth = roomWidth;
        this.flooringType = flooringType;
        this.ceilingHeight = ceilingHeight;
        lightOn = false;
        doorOpen = false;
        nightLightOn = true;
    }

    public boolean toggleNightLight(){
        if (nightLightOn) {
            System.out.println("The night light turns off.");
            nightLightOn = false;
        } else {
            System.out.println("The night light turns on.");
            nightLightOn = true;
        }
        return nightLightOn;
    }

    public boolean toggleCeilingFan(){
        if (ceilingFanOn) {
            System.out.println("Turning the bedroom ceiling fan off.");
            ceilingFanOn= false;
        } else {
            System.out.println("Turning the bedroom ceiling fan on.");
            ceilingFanOn = true;
        }
        return ceilingFanOn;
    }

    @Override
    protected boolean toggleDoor() {
        if (this.isDoorOpen()) {
            System.out.println("Closing the bedroom door.");
            doorOpen = false;
        } else {
            System.out.println("Opening the bedroom door.");
            doorOpen = true;
        }
        return doorOpen;
    }

    @Override
    protected boolean toggleLights() {
        if (this.isLightOn()) {
            System.out.println("Turning the bedroom light off.");
            lightOn = false;
        } else {
            System.out.println("Turning the bedroom light on.");
            lightOn = true;
        }
        toggleNightLight();
        return lightOn;
    }

    public boolean isNightLightOn() {
        return nightLightOn;
    }

    public void setNightLightOn(boolean nightLightOn) {
        this.nightLightOn = nightLightOn;
    }

    public boolean isCeilingFanOn() {
        return ceilingFanOn;
    }

    public void setCeilingFanOn(boolean ceilingFanOn) {
        this.ceilingFanOn = ceilingFanOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bedroom bedroom = (Bedroom) o;
        return nightLightOn == bedroom.nightLightOn && ceilingFanOn == bedroom.ceilingFanOn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nightLightOn, ceilingFanOn);
    }

    @Override
    public String toString() {
        return "Bedroom{" +
                "nightLightOn=" + nightLightOn +
                ", ceilingFanOn=" + ceilingFanOn +
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
