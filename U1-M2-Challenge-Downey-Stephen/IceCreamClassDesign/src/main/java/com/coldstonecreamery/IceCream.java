package com.coldstonecreamery;

import com.coldstonecreamery.domain.Customer;
import com.coldstonecreamery.domain.Ingredient;
import com.coldstonecreamery.domain.Product;
import com.coldstonecreamery.domain.Size;

import java.util.Arrays;
import java.util.Objects;

//This IceCream class models ice cream from the perspective of a point of sale system at
//a retail ice cream shop.
public class IceCream extends Product {
    private Customer customer;
    private String flavor;
    private Size size;
    Ingredient[] toppings;

    public IceCream(){}

    public IceCream(String flavor, Size size, Ingredient[] toppings, double price){
        this.flavor = flavor;
        this.size = size;
        this.toppings = toppings;
        this.setPrice(price);
    }

    public IceCream(String flavor, Size size, Ingredient[] toppings, double price, Customer customer){
        this(flavor, size, toppings, price);
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Ingredient[] getToppings() {
        return toppings;
    }

    public void setToppings(Ingredient[] toppings) {
        this.toppings = toppings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IceCream iceCream = (IceCream) o;
        return Objects.equals(customer, iceCream.customer) && Objects.equals(flavor, iceCream.flavor) && Objects.equals(size, iceCream.size) && Arrays.equals(toppings, iceCream.toppings);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(customer, flavor, size);
        result = 31 * result + Arrays.hashCode(toppings);
        return result;
    }

    @Override
    public String toString() {
        return "IceCream{" +
                "customer=" + customer +
                ", flavor='" + flavor + '\'' +
                ", size=" + size +
                ", toppings=" + Arrays.toString(toppings) +
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
