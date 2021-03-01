package com.company;

import java.util.Objects;

public abstract class Room {
    protected double squareFootage;
    protected double roomLength;
    protected double roomWidth;
    protected String flooringType;
    protected double ceilingHeight;
    protected boolean doorOpen;
    protected boolean lightOn;

    protected abstract boolean toggleDoor();
    protected abstract boolean toggleLights();

    protected boolean isDoorOpen() {
        return doorOpen;
    }

    protected void setDoorOpen(boolean doorOpen) {
        this.doorOpen = doorOpen;
    }

    protected boolean isLightOn() {
        return lightOn;
    }

    protected void setLightOn(boolean lightsOn) {
        this.lightOn = lightsOn;
    }

    protected double getSquareFootage() {
        return squareFootage;
    }

    protected void setSquareFootage(double squareFootage) {
        this.squareFootage = squareFootage;
    }

    protected double getRoomLength() {
        return roomLength;
    }

    protected void setRoomLength(double roomLength) {
        this.roomLength = roomLength;
    }

    protected double getRoomWidth() {
        return roomWidth;
    }

    protected void setRoomWidth(double roomWidth) {
        this.roomWidth = roomWidth;
    }

    protected String getFlooringType() {
        return flooringType;
    }

    protected void setFlooringType(String flooringType) {
        this.flooringType = flooringType;
    }

    protected double getCeilingHeight() {
        return ceilingHeight;
    }

    protected void setCeilingHeight(double ceilingHeight) {
        this.ceilingHeight = ceilingHeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Double.compare(room.squareFootage, squareFootage) == 0 && Double.compare(room.roomLength, roomLength) == 0 && Double.compare(room.roomWidth, roomWidth) == 0 && Double.compare(room.ceilingHeight, ceilingHeight) == 0 && Objects.equals(flooringType, room.flooringType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(squareFootage, roomLength, roomWidth, flooringType, ceilingHeight);
    }

    @Override
    public String toString() {
        return "Room{" +
                "squareFootage=" + squareFootage +
                ", roomLength=" + roomLength +
                ", roomWidth=" + roomWidth +
                ", flooringType='" + flooringType + '\'' +
                ", ceilingHeight=" + ceilingHeight +
                '}';
    }
}
