package com.coldstonecreamery.domain;

import java.util.Arrays;
import java.util.Objects;

public class Ingredient extends Product{
    private int numberOfServings; //to be measured in .standardUnits

    public Ingredient(){};

    public Ingredient(int numberOfServings) {
        this.numberOfServings = numberOfServings;
    }

    public Ingredient(String name, String description, double standardUnit, String unitOfMeasurement, double unitsInStock, Supplier supplier, int numberOfServings) {
        super(name, description, standardUnit, unitOfMeasurement, unitsInStock, supplier);
        this.numberOfServings = numberOfServings;
    }

    public Ingredient(String name, String description, double standardUnit, String unitOfMeasurement, int numberOfServings) {
        super(name, description, standardUnit, unitOfMeasurement);
        this.numberOfServings = numberOfServings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return numberOfServings == that.numberOfServings;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfServings);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "numberOfServings=" + numberOfServings +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                ", inStock=" + inStock +
                ", lotNumber='" + lotNumber + '\'' +
                ", margin=" + margin +
                ", name='" + name + '\'' +
                ", orderHistory=" + orderHistory +
                ", price=" + price +
                ", productId='" + productId + '\'' +
                ", suppliers=" + Arrays.toString(suppliers) +
                ", standardUnit=" + standardUnit +
                ", unitsInStock=" + unitsInStock +
                ", unitOfMeasurement='" + unitOfMeasurement + '\'' +
                '}';
    }
}
