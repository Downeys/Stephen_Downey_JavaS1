package us.haagen_dazs.domain;

import us.haagen_dazs.util.UniqueID;

import java.util.Objects;

public class Invoice {
    private String balanceDueDate;
    private double balanceForward;
    private double currentBalance;
    private String invoiceID;
    private double latestCharge;

    public Invoice(){};

    public Invoice(String businessName, double latestCharge, String balanceDueDate) {
        this.latestCharge = latestCharge;
        this.balanceDueDate = balanceDueDate;
        this.currentBalance = latestCharge + balanceForward;
        this.invoiceID = UniqueID.generateUniqueID(businessName);
    }

    public String getBalanceDueDate() {
        return balanceDueDate;
    }

    public void setBalanceDueDate(String balanceDueDate) {
        this.balanceDueDate = balanceDueDate;
    }

    public double getBalanceForward() {
        return balanceForward;
    }

    public void setBalanceForward(double balanceForward) {
        this.balanceForward = balanceForward;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public double getLatestCharge() {
        return latestCharge;
    }

    public void setLatestCharge(double latestCharge) {
        this.latestCharge = latestCharge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Double.compare(invoice.balanceForward, balanceForward) == 0 && Double.compare(invoice.currentBalance, currentBalance) == 0 && Double.compare(invoice.latestCharge, latestCharge) == 0 && Objects.equals(balanceDueDate, invoice.balanceDueDate) && Objects.equals(invoiceID, invoice.invoiceID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balanceDueDate, balanceForward, currentBalance, invoiceID, latestCharge);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "balanceDueDate='" + balanceDueDate + '\'' +
                ", balanceForward=" + balanceForward +
                ", currentBalance=" + currentBalance +
                ", invoiceID='" + invoiceID + '\'' +
                ", latestCharge=" + latestCharge +
                '}';
    }
}
