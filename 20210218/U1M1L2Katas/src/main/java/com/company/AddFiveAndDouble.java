package com.company;

import java.util.Scanner;

public class AddFiveAndDouble {

    public static void main(String[] args) {
        System.out.println("We're going to add 5 to the number you provide, and then we're going to double it.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number.");
        int userNumber = Integer.parseInt(scanner.nextLine());
        int solution = (userNumber + 5) * 2;
        System.out.println("The solution to that problem is  " + solution + ".");
    }
}
