package com.company;

import java.util.Scanner;

public class AddFive {

    public static void main(String[] args) {
        System.out.println("We're going to find the sum of five numbers.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the first number.");
        int firstNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter the second number.");
        int secondNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter the third number.");
        int thirdNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter the fourth number.");
        int fourthNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter the fifth number.");
        int fifthNumber = Integer.parseInt(scanner.nextLine());
        int sum = firstNumber + secondNumber + thirdNumber + fourthNumber + fifthNumber;
        System.out.println("The product of those three numbers is " + sum + ".");
    }
}
