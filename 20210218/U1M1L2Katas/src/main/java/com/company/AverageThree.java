package com.company;
import java.util.Scanner;

public class AverageThree {

    public static void main(String[] args) {
        System.out.println("We're going to find the average of three numbers.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the first number.");
        double firstNumber = Double.parseDouble(scanner.nextLine());
        System.out.println("Please enter the second number.");
        double secondNumber = Double.parseDouble(scanner.nextLine());
        System.out.println("Please enter the third number.");
        double thirdNumber = Double.parseDouble(scanner.nextLine());
        double average = (firstNumber + secondNumber + thirdNumber)/3;
        System.out.format("The average of those three numbers is %.2f", average);

    }
}
