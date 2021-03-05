package com.coldstonecreamery.domain;

import java.util.Arrays;

public class Size {
    private double[] prices;
    private String[] sizeLabels;
    private double[] standardCosts;
    private int[] standardServingSizes;

    private final double[] DEFAULT_PRICES = {5.50, 6.65, 7.85};
    private final String[] DEFAULT_SIZE_LABELS = {"Small", "Medium", "Large"};
    private final double[] DEFAULT_STANDARD_COSTS = {3.25, 3.75, 4.25};
    private final int[] DEFAULT_STANDARD_SERVING_SIZES = {1, 2, 3};

    public Size(){
        this.prices = DEFAULT_PRICES;
        this.sizeLabels = DEFAULT_SIZE_LABELS;
        this.standardCosts = DEFAULT_STANDARD_COSTS;
        this.standardServingSizes = DEFAULT_STANDARD_SERVING_SIZES;
    }

    public Size(String[] sizeLabels, double[] prices, double[] standardCosts, int[] standardServingSizes) {
        this.prices = prices;
        this.sizeLabels = sizeLabels;
        this.standardCosts = standardCosts;
        this.standardServingSizes = standardServingSizes;
    }

    public double[] getPrices() {
        return prices;
    }

    public void setPrices(double[] prices) {
        this.prices = prices;
    }

    public String[] getSizeLabels() {
        return sizeLabels;
    }

    public void setSizeLabels(String[] sizeLabels) {
        this.sizeLabels = sizeLabels;
    }

    public double[] getStandardCosts() {
        return standardCosts;
    }

    public void setStandardCosts(double[] standardCosts) {
        this.standardCosts = standardCosts;
    }

    public int[] getStandardServingSizes() {
        return standardServingSizes;
    }

    public void setStandardServingSizes(int[] standardServingSizes) {
        this.standardServingSizes = standardServingSizes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Size size = (Size) o;
        return Arrays.equals(prices, size.prices) && Arrays.equals(sizeLabels, size.sizeLabels) && Arrays.equals(standardCosts, size.standardCosts) && Arrays.equals(standardServingSizes, size.standardServingSizes) && Arrays.equals(DEFAULT_PRICES, size.DEFAULT_PRICES) && Arrays.equals(DEFAULT_SIZE_LABELS, size.DEFAULT_SIZE_LABELS) && Arrays.equals(DEFAULT_STANDARD_COSTS, size.DEFAULT_STANDARD_COSTS) && Arrays.equals(DEFAULT_STANDARD_SERVING_SIZES, size.DEFAULT_STANDARD_SERVING_SIZES);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(prices);
        result = 31 * result + Arrays.hashCode(sizeLabels);
        result = 31 * result + Arrays.hashCode(standardCosts);
        result = 31 * result + Arrays.hashCode(standardServingSizes);
        result = 31 * result + Arrays.hashCode(DEFAULT_PRICES);
        result = 31 * result + Arrays.hashCode(DEFAULT_SIZE_LABELS);
        result = 31 * result + Arrays.hashCode(DEFAULT_STANDARD_COSTS);
        result = 31 * result + Arrays.hashCode(DEFAULT_STANDARD_SERVING_SIZES);
        return result;
    }

    @Override
    public String toString() {
        return "Size{" +
                "prices=" + Arrays.toString(prices) +
                ", sizeLabels=" + Arrays.toString(sizeLabels) +
                ", standardCosts=" + Arrays.toString(standardCosts) +
                ", standardServingSizes=" + Arrays.toString(standardServingSizes) +
                ", DEFAULT_PRICES=" + Arrays.toString(DEFAULT_PRICES) +
                ", DEFAULT_SIZE_LABELS=" + Arrays.toString(DEFAULT_SIZE_LABELS) +
                ", DEFAULT_STANDARD_COSTS=" + Arrays.toString(DEFAULT_STANDARD_COSTS) +
                ", DEFAULT_STANDARD_SERVING_SIZES=" + Arrays.toString(DEFAULT_STANDARD_SERVING_SIZES) +
                '}';
    }
}
