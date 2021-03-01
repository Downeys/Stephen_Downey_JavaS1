package com.company;

import java.util.Arrays;
import java.util.Objects;

public class House {
    private Bedroom[] bedrooms = new Bedroom[10];
    private Bathroom[] bathrooms = new Bathroom[10];
    private Kitchen[] kitchen = new Kitchen[3];
    private Basement basement = new Basement();
    private Patio[] patios = new Patio[4];
    private LivingRoom[] livingRoom = new LivingRoom[3];

    public House(){};

    public House(Bedroom[] bedrooms, Bathroom[] bathrooms, Kitchen[] kitchen, Basement basement, Patio[] patios, LivingRoom[] livingRoom) {
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.kitchen = kitchen;
        this.basement = basement;
        this.patios = patios;
        this.livingRoom = livingRoom;
    }

    public void burglarAlarm(){
        System.out.println("Someone has triggered the house alarm.");
    }

    public void repelVampires(){
        System.out.println("Sorry vampires, you can't come in unless you're invited.");
    }

    public Bedroom[] getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Bedroom[] bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Bathroom[] getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Bathroom[] bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Kitchen[] getKitchen() {
        return kitchen;
    }

    public void setKitchen(Kitchen[] kitchen) {
        this.kitchen = kitchen;
    }

    public Basement getBasement() {
        return basement;
    }

    public void setBasement(Basement basement) {
        this.basement = basement;
    }

    public Patio[] getPatios() {
        return patios;
    }

    public void setPatios(Patio[] patios) {
        this.patios = patios;
    }

    public LivingRoom[] getLivingRoom() {
        return livingRoom;
    }

    public void setLivingRoom(LivingRoom[] livingRoom) {
        this.livingRoom = livingRoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Arrays.equals(bedrooms, house.bedrooms) && Arrays.equals(bathrooms, house.bathrooms) && Arrays.equals(kitchen, house.kitchen) && Objects.equals(basement, house.basement) && Arrays.equals(patios, house.patios) && Arrays.equals(livingRoom, house.livingRoom);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(basement);
        result = 31 * result + Arrays.hashCode(bedrooms);
        result = 31 * result + Arrays.hashCode(bathrooms);
        result = 31 * result + Arrays.hashCode(kitchen);
        result = 31 * result + Arrays.hashCode(patios);
        result = 31 * result + Arrays.hashCode(livingRoom);
        return result;
    }

    @Override
    public String toString() {
        return "House{" +
                "bedrooms=" + Arrays.toString(bedrooms) +
                ", bathrooms=" + Arrays.toString(bathrooms) +
                ", kitchen=" + Arrays.toString(kitchen) +
                ", basement=" + basement +
                ", patios=" + Arrays.toString(patios) +
                ", livingRoom=" + Arrays.toString(livingRoom) +
                '}';
    }
}
