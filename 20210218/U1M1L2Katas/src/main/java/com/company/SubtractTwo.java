package com.company;

import java.util.Scanner;

public class SubtractTwo {

    public static void main(String[] args) {
        System.out.println("We're going to find the difference of two numbers.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the first number.");
        int firstNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter the second number.");
        int secondNumber = Integer.parseInt(scanner.nextLine());
        int difference = firstNumber - secondNumber;
        System.out.println("The product of those three numbers is " + difference + ".");
    }
}
