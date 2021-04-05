package com.company.StephenDowneyU1Capstone.models;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class Console extends Item{
    @NotBlank(message = "Invalid Console: model cannot be blank.")
    private String model;
    @NotBlank(message = "Invalid Console: manufacturer cannot be blank.")
    private String manufacturer;
    @NotBlank(message = "Invalid Console: memory amount cannot be blank.")
    private String memoryAmount;
    @NotBlank(message = "Invalid Console: processor cannot be blank.")
    private String processor;

    public Console() {
        this.setItemType("Consoles");
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMemoryAmount() {
        return memoryAmount;
    }

    public void setMemoryAmount(String memoryAmount) {
        this.memoryAmount = memoryAmount;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Console console = (Console) o;
        return Objects.equals(model, console.model) && Objects.equals(manufacturer, console.manufacturer) && Objects.equals(memoryAmount, console.memoryAmount) && Objects.equals(processor, console.processor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), model, manufacturer, memoryAmount, processor);
    }

    @Override
    public String toString() {
        return "Console{" +
                "model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", memoryAmount='" + memoryAmount + '\'' +
                ", processor='" + processor + '\'' +
                '}';
    }
}
