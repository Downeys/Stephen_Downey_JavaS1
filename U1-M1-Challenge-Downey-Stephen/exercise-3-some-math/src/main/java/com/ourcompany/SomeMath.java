package com.ourcompany;

public class SomeMath {

    public static int total5(int a, int b, int c, int d, int e){
        return a + b + c + d + e;
    }

    public static double average5(int a, int b, int c, int d, int e){
        double total = total5(a, b, c, d, e);
        return total/5;
    }

    public static double largest5(double a, double b, double c, double d, double e){
        double largestNumber = Integer.MIN_VALUE;
        double[] arrayOfNumbers = {a, b, c, d, e};
        for(int i = 0; i < 5; i++){
            if(arrayOfNumbers[i] > largestNumber){
                largestNumber = arrayOfNumbers[i];
            }
        }
        return largestNumber;
    }

    public static void main(String[] args) {
        System.out.println(total5(1, 2, 3, 4, 5));
        System.out.println(average5(1, 3, 5, 7, 9));
        System.out.println(largest5(42.0, 35.1, 2.3, 40.2, 15.6));
    }
}
    