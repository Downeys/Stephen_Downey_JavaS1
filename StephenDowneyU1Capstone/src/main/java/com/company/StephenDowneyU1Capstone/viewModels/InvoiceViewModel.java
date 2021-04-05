package com.company.StephenDowneyU1Capstone.viewModels;

import com.company.StephenDowneyU1Capstone.models.Item;
import com.company.StephenDowneyU1Capstone.models.SalesTaxRate;

import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceViewModel {
    private Integer invoiceId;
    private String name;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private Item item;
    private BigDecimal subtotal;
    private BigDecimal salesTaxRate; //make sure this is included in test models
    private BigDecimal tax;
    private BigDecimal processingFee;
    private BigDecimal total;

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getSalesTaxRate() {
        return salesTaxRate;
    }

    public void setSalesTaxRate(BigDecimal salesTaxRate) {
        this.salesTaxRate = salesTaxRate;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return Objects.equals(invoiceId, that.invoiceId) && Objects.equals(name, that.name) && Objects.equals(street, that.street) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(zipcode, that.zipcode) && Objects.equals(item, that.item) && Objects.equals(subtotal, that.subtotal) && Objects.equals(salesTaxRate, that.salesTaxRate) && Objects.equals(tax, that.tax) && Objects.equals(processingFee, that.processingFee) && Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, name, street, city, state, zipcode, item, subtotal, salesTaxRate, tax, processingFee, total);
    }

    @Override
    public String toString() {
        return "InvoiceViewModel{" +
                "invoiceId=" + invoiceId +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", item=" + item +
                ", subtotal=" + subtotal +
                ", salesTaxRate=" + salesTaxRate +
                ", tax=" + tax +
                ", processingFee=" + processingFee +
                ", total=" + total +
                '}';
    }
}
