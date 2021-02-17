package com.company;

import java.util.Scanner;

public class DoubleAndAddFive {

    public static void main(String[] args) {
        System.out.println("We're going to double the number you provide, and then we're going to add five to it.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number.");
        int userNumber = Integer.parseInt(scanner.nextLine());
        int solution = (userNumber * 2) + 5;
        System.out.println("The solution to that problem is  " + solution + ".");
    }
}
