package com.coldstonecreamery.domain;

import java.util.Arrays;
import java.util.Objects;

public class Customer extends Entity{
    OrderHistory orderHistory;
    boolean rewardsClubMember;


    public Customer() {
    }

    public Customer(String businessName, Contact contact, Address address, String firstDateKnown) {
        super(businessName, contact, address, firstDateKnown);
    }

    public OrderHistory getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(OrderHistory orderHistory) {
        this.orderHistory = orderHistory;
    }

    public boolean isRewardsClubMember() {
        return rewardsClubMember;
    }

    public void setRewardsClubMember(boolean rewardsClubMember) {
        this.rewardsClubMember = rewardsClubMember;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return rewardsClubMember == customer.rewardsClubMember && Objects.equals(orderHistory, customer.orderHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderHistory, rewardsClubMember);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "orderHistory=" + orderHistory +
                ", rewardsClubMember=" + rewardsClubMember +
                ", address=" + address +
                ", businessId='" + businessId + '\'' +
                ", contacts=" + Arrays.toString(contacts) +
                ", entityName='" + entityName + '\'' +
                ", firstDateKnown='" + firstDateKnown + '\'' +
                '}';
    }
}
