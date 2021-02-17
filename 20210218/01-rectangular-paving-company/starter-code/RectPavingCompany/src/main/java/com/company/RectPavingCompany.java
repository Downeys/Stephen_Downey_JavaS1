package com.company;
import java.util.Scanner;

public class RectPavingCompany {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("We are going to calculate the price of paving a rectangle area.");
        System.out.println("Please enter the width of the rectangle.");
        int width = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter the length of the rectangle.");
        int length = Integer.parseInt(scanner.nextLine());
        int area = width * length;
        System.out.println("The area is " + area + ".");
        int perimeter = (2 * width) + (2 * length);
        System.out.println("The perimeter is " + perimeter + ".");

        System.out.println("Please enter the price of cement per square foot.");
        double cementCostPerSquareFoot = Double.parseDouble(scanner.nextLine());
        System.out.println("Please enter the price of framing/footers per foot.");
        double framingCostPerFoot = Double.parseDouble(scanner.nextLine());;
        double totalCementCost = cementCostPerSquareFoot * area;
        double totalFramingCost = framingCostPerFoot * perimeter;

        System.out.format("The total cost of the cement will be %.2f.", totalCementCost);
        System.out.format("The total cost of the framing/footers will be %.2f.", totalFramingCost);



    }
}
