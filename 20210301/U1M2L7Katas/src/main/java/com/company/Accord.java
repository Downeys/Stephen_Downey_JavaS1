package com.company;

import java.util.Objects;

public class Accord extends Sedan{
    private String year;
    private Engine engine;
    private Transmission transmission;

    public Accord(){};

    public Accord(String year, Engine engine, Transmission transmission) {
        this.year = year;
        this.engine = engine;
        this.transmission = transmission;
    }

    public Accord(int numDoors, boolean poweredWindows, String year, Engine engine, Transmission transmission) {
        super(numDoors, poweredWindows);
        this.year = year;
        this.engine = engine;
        this.transmission = transmission;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Accord accord = (Accord) o;
        return Objects.equals(year, accord.year) && Objects.equals(engine, accord.engine) && Objects.equals(transmission, accord.transmission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), year, engine, transmission);
    }

    @Override
    public String toString() {
        return "Accord{" +
                "year='" + year + '\'' +
                ", engine=" + engine +
                ", transmission=" + transmission +
                '}';
    }
}
