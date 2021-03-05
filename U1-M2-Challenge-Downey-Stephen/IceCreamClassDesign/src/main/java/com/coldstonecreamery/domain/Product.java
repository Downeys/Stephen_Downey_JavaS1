package com.coldstonecreamery.domain;


import com.coldstonecreamery.util.UniqueID;

public class Product {
    protected double cost; //user's cost per each unitsInStock
    protected String description;
    protected boolean inStock;
    protected String lotNumber;
    protected double margin; // (price / cost) - 1; profit margin as a percentage
    protected String name;
    protected OrderHistory orderHistory;
    protected double price; //client facing price per each unitsInStock;
    protected String productId;
    protected Supplier[] suppliers = new Supplier[100];
    protected double standardUnit; //to indicate any unit of measurement;
    protected double unitsInStock;
    protected String unitOfMeasurement; //to define which unit we're measuring;

    public Product(){};

    public Product(String name, String description, double standardUnit,
                   String unitOfMeasurement, double unitsInStock, Supplier supplier) {
        this.name = name;
        this.description = description;
        this.standardUnit = standardUnit;
        this.unitOfMeasurement = unitOfMeasurement;
        this.unitsInStock = unitsInStock;
        this.inStock = unitsInStock > 0;
        this.productId = UniqueID.generateUniqueID(name);
        this.suppliers[0] = supplier;
    }

    //This constructor would be used for the Recipe Ingredients and will not have a Source, unitsInStock,
    //inStock, or productID initialized, which leaves them free to set to them equal to the their
    // sister properties in the inventory instances of the ingredients.
    public Product(String name, String description, double standardUnit, String unitOfMeasurement) {
        this.name = name;
        this.description = description;
        this.standardUnit = standardUnit;
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Supplier[] getSources() {
        return suppliers;
    }

    public Supplier getSource(int index){
        return suppliers[index];
    }

    public void setSources(Supplier[] suppliers) {
        this.suppliers = suppliers;
    }

    public double getStandardUnit() {
        return standardUnit;
    }

    public void setStandardUnit(double standardUnit) {
        this.standardUnit = standardUnit;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getMargin() {
        return margin;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }

    public OrderHistory getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(OrderHistory orderHistory) {
        this.orderHistory = orderHistory;
    }

    public double getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(double unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

}
