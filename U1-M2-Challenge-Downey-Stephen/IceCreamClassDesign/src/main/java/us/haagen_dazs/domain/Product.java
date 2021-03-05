package us.haagen_dazs.domain;

import us.haagen_dazs.util.UniqueID;

import java.util.Arrays;
import java.util.Objects;

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
    protected Source[] sources = new Source[100];
    protected double standardUnit; //to indicate any unit of measurement;
    protected double unitsInStock;
    protected String unitOfMeasurement; //to define which unit we're measuring;

    public Product(){};

    public Product(String name, String description, double standardUnit,
                   String unitOfMeasurement, double unitsInStock, Source source) {
        this.name = name;
        this.description = description;
        this.standardUnit = standardUnit;
        this.unitOfMeasurement = unitOfMeasurement;
        this.unitsInStock = unitsInStock;
        this.inStock = unitsInStock > 0;
        this.productId = UniqueID.generateUniqueID(name);
        this.sources[0] = source;
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

    public Source[] getSources() {
        return sources;
    }

    public Source getSource(int index){
        return sources[index];
    }

    public void setSources(Source[] sources) {
        this.sources = sources;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.standardUnit, standardUnit) == 0 && Double.compare(product.price, price) == 0 && Double.compare(product.cost, cost) == 0 && Double.compare(product.margin, margin) == 0 && Double.compare(product.unitsInStock, unitsInStock) == 0 && inStock == product.inStock && Objects.equals(name, product.name) && Objects.equals(productId, product.productId) && Objects.equals(lotNumber, product.lotNumber) && Objects.equals(description, product.description) && Arrays.equals(sources, product.sources) && Objects.equals(unitOfMeasurement, product.unitOfMeasurement) && Objects.equals(orderHistory, product.orderHistory);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, productId, lotNumber, description, standardUnit, unitOfMeasurement, price, cost, margin, orderHistory, unitsInStock, inStock);
        result = 31 * result + Arrays.hashCode(sources);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", productId='" + productId + '\'' +
                ", lotNumber='" + lotNumber + '\'' +
                ", description='" + description + '\'' +
                ", sources=" + Arrays.toString(sources) +
                ", standardUnit=" + standardUnit +
                ", unitOfMeasurement='" + unitOfMeasurement + '\'' +
                ", price=" + price +
                ", cost=" + cost +
                ", margin=" + margin +
                ", orderHistory=" + orderHistory +
                ", unitsInStock=" + unitsInStock +
                ", inStock=" + inStock +
                '}';
    }
}
