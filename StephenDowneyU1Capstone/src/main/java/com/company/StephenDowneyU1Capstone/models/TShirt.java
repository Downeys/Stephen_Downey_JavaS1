package com.company.StephenDowneyU1Capstone.models;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class TShirt extends Item{
    @NotBlank
    private String size;
    @NotBlank
    private String color;
    @NotBlank
    private String description;

    public TShirt() {
        this.setItemType("T-Shirts");
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TShirt tShirt = (TShirt) o;
        return Objects.equals(size, tShirt.size) && Objects.equals(color, tShirt.color) && Objects.equals(description, tShirt.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), size, color, description);
    }

    @Override
    public String toString() {
        return "TShirt{" +
                "size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
