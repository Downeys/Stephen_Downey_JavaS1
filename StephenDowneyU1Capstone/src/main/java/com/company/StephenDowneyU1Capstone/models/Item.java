package com.company.StephenDowneyU1Capstone.models;

import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Objects;

public abstract class Item {
    private Integer itemId;
    private String itemType;
    @PositiveOrZero
    private Integer quantity;
    @PositiveOrZero
    private BigDecimal price;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(itemId, item.itemId) && Objects.equals(itemType, item.itemType) && Objects.equals(quantity, item.quantity) && Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, itemType, quantity, price);
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemType='" + itemType + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
