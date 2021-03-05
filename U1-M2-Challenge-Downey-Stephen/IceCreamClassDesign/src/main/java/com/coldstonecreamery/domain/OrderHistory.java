package com.coldstonecreamery.domain;

import us.haagen_dazs.util.UniqueID;

import java.util.Arrays;
import java.util.Objects;

public class OrderHistory {
    private String[] dateOfPurchase = new String[Integer.MAX_VALUE];
    private String[] itemCode = new String[Integer.MAX_VALUE];
    private double[] pricePayedPerUnit = new double[Integer.MAX_VALUE]; //to indicate any unit of measurement
    private String[] transactionId = new String[Integer.MAX_VALUE];
    private int[] quantityOrdered = new int[Integer.MAX_VALUE]; //should refer to same weight unit as above
    private String weightUnitForPrice; //to define which unit of measurement for this product or ingredient (or supply)


    public OrderHistory(){};

    public void logPurchase(String itemCode, double pricePayedPerUnit, String weightUnitForPrice,
                            int quantityOrdered, String dateOfPurchase){
        int i = 0;
        while(this.transactionId[i] == null){
            i++;
        }
        this.itemCode[i] = itemCode;
        this.pricePayedPerUnit[i] = pricePayedPerUnit;
        this.weightUnitForPrice = weightUnitForPrice;
        this.quantityOrdered[i] = quantityOrdered;
        this.dateOfPurchase[i] = dateOfPurchase;
        transactionId[i] = dateOfPurchase.concat(UniqueID.generateUniqueID(itemCode));
    }

    //no getters and setters, just a log.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderHistory that = (OrderHistory) o;
        return Arrays.equals(dateOfPurchase, that.dateOfPurchase) && Arrays.equals(itemCode, that.itemCode) && Arrays.equals(pricePayedPerUnit, that.pricePayedPerUnit) && Arrays.equals(transactionId, that.transactionId) && Arrays.equals(quantityOrdered, that.quantityOrdered) && Objects.equals(weightUnitForPrice, that.weightUnitForPrice);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(weightUnitForPrice);
        result = 31 * result + Arrays.hashCode(dateOfPurchase);
        result = 31 * result + Arrays.hashCode(itemCode);
        result = 31 * result + Arrays.hashCode(pricePayedPerUnit);
        result = 31 * result + Arrays.hashCode(transactionId);
        result = 31 * result + Arrays.hashCode(quantityOrdered);
        return result;
    }

    @Override
    public String toString() {
        return "OrderHistory{" +
                "dateOfPurchase=" + Arrays.toString(dateOfPurchase) +
                ", itemCode=" + Arrays.toString(itemCode) +
                ", pricePayedPerUnit=" + Arrays.toString(pricePayedPerUnit) +
                ", transactionId=" + Arrays.toString(transactionId) +
                ", quantityOrdered=" + Arrays.toString(quantityOrdered) +
                ", weightUnitForPrice='" + weightUnitForPrice + '\'' +
                '}';
    }

}
