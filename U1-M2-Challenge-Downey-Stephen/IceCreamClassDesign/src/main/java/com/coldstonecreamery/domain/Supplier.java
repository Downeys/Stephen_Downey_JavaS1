package com.coldstonecreamery.domain;

import java.util.Arrays;

public class Supplier extends Entity{
    private us.haagen_dazs.domain.Product[] products;

    public Supplier(){}

    public Supplier(us.haagen_dazs.domain.Product[] products) {
        this.products = products;
    }

    public Supplier(String entityName, Contact contact, Address address, String firstDateKnown, us.haagen_dazs.domain.Product[] products) {
        super(entityName, contact, address, firstDateKnown);
        this.products = products;
    }

    public String orderProduct(Product product) {
        if (product.isInStock()) {
            System.out.println("Your order has been placed. Thank you for your business.");
            return "Order Successful";
        } else {
            System.out.println("Sorry, we are currently out of this product.");
            return "There was a problem with the order.";
        }
    }

    public us.haagen_dazs.domain.Product[] getProducts() {
        return products;
    }

    public void setProducts(us.haagen_dazs.domain.Product[] products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return Arrays.equals(products, supplier.products);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(products);
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "address=" + address +
                ", businessId='" + businessId + '\'' +
                ", contacts=" + Arrays.toString(contacts) +
                ", entityName='" + entityName + '\'' +
                ", firstDateKnown='" + firstDateKnown + '\'' +
                ", products=" + Arrays.toString(products) +
                '}';
    }
}
