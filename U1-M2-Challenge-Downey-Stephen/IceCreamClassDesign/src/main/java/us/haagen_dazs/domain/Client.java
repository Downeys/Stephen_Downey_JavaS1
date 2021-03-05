package us.haagen_dazs.domain;

import java.util.Arrays;
import java.util.Objects;

public class Client extends BusinessUnit{
    private OrderHistory orderHistory;

    public Client() {
    }

    public Client(String businessName, Contact contact, Address address, String firstDateKnown) {
        super(businessName, contact, address, firstDateKnown);
    }

    public OrderHistory getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(OrderHistory orderHistory) {
        this.orderHistory = orderHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(orderHistory, client.orderHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderHistory);
    }

    @Override
    public String toString() {
        return "Client{" +
                "address=" + address +
                ", businessId='" + businessId + '\'' +
                ", businessName='" + businessName + '\'' +
                ", contacts=" + Arrays.toString(contacts) +
                ", firstDateKnown='" + firstDateKnown + '\'' +
                ", invoices=" + Arrays.toString(invoices) +
                ", orderHistory=" + orderHistory +
                '}';
    }
}
