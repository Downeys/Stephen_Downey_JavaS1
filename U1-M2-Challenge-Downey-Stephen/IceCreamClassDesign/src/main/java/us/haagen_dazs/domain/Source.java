package us.haagen_dazs.domain;

import java.util.Arrays;

public class Source extends BusinessUnit{
    private Product[] products;

    public String orderProduct(Product product) {
        if (product.isInStock()) {
            System.out.println("Your order has been placed. Thank you for your business.");
            return "Order Successful";
        } else {
            System.out.println("Sorry, we are currently out of this product.");
            return "There was a problem with the order.";
        }
    }

    public Source(){}

    public Source(Product[] products) {
        this.products = products;
    }

    public Source(String businessName, Contact contact, Address address, String firstDateKnown, Product[] products) {
        super(businessName, contact, address, firstDateKnown);
        this.products = products;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Source source = (Source) o;
        return Arrays.equals(products, source.products);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(products);
    }

    @Override
    public String toString() {
        return "Source{" +
                "address=" + address +
                ", businessId='" + businessId + '\'' +
                ", businessName='" + businessName + '\'' +
                ", contacts=" + Arrays.toString(contacts) +
                ", firstDateKnown='" + firstDateKnown + '\'' +
                ", invoices=" + Arrays.toString(invoices) +
                ", products=" + Arrays.toString(products) +
                '}';
    }
}
