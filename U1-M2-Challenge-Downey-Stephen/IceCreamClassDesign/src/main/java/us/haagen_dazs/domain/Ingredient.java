package us.haagen_dazs.domain;

import java.util.Arrays;
import java.util.Objects;

public class Ingredient extends Product{
    private double amountNeeded; //to be measured in .standardUnits.
    //amountNeeded can be used in the context of creating a blueprint for a recipe.
    //it can also be used in the context of inventory and ordering processes. //TODO

    public Ingredient(){};

    //This constructor is likely the one to use for recipes because you probably don't need to
    //know the source of each ingredient of the recipe or even the amount of that ingredient that
    // is in stock.
    public Ingredient(String name, String description, double standardUnit, String unitOfMeasurement,
                      double amountNeeded){
        super(name, description, standardUnit, unitOfMeasurement);
        this.amountNeeded = amountNeeded;
    }

    //This constructor is best for the inventory context of Ingredients because you will want to know
    //where to buy more raw materials, and you'll want to know what factory produced each lot of finished
    //product.
    public Ingredient(String name, String description, double standardUnit, String unitOfMeasurement,
                      double unitsInStock, Source source){
        super(name, description, standardUnit, unitOfMeasurement, unitsInStock, source);
    }

    //This constructor
    public Ingredient(String name, String description, double standardUnit,
                      String unitOfMeasurement, double unitsInStock, double amountNeeded,
                      Source source) {
        super(name, description, standardUnit, unitOfMeasurement, unitsInStock, source);
        this.amountNeeded = amountNeeded;
    }

    public double getAmountNeeded() {
        return amountNeeded;
    }

    public void setAmountNeeded(double amountNeeded) {
        this.amountNeeded = amountNeeded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Ingredient that = (Ingredient) o;
        return Double.compare(that.amountNeeded, amountNeeded) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), amountNeeded);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "amountNeeded=" + amountNeeded +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                ", inStock=" + inStock +
                ", lotNumber='" + lotNumber + '\'' +
                ", margin=" + margin +
                ", name='" + name + '\'' +
                ", orderHistory=" + orderHistory +
                ", price=" + price +
                ", productId='" + productId + '\'' +
                ", sources=" + Arrays.toString(sources) +
                ", standardUnit=" + standardUnit +
                ", unitsInStock=" + unitsInStock +
                ", unitOfMeasurement='" + unitOfMeasurement + '\'' +
                '}';
    }
}
