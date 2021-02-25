package com.company;

import java.util.Scanner;

public class SafeSquareRoot {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("SQUARE ROOT!");
        System.out.print("Enter a number: ");
        double userInput = Integer.MIN_VALUE;
        while(userInput < 0){
            userInput = Double.parseDouble(scan.nextLine());
            if(userInput < 0){
                System.out.println("You can't take the square root of a negative number, silly.");
                System.out.print("Try again: ");
            }
        }
        System.out.println("The square root of " + userInput + " is " + Math.sqrt(userInput) + ".");
    }
}
