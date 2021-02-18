package com.company;
import java.util.Scanner;

public class RangeChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;
        while(userInput < 15 || userInput > 32){
            System.out.println("Please enter a number between 15 and 32 (inclusive).");
            userInput = Integer.parseInt(scanner.nextLine());
        }
        System.out.println(userInput);
    }
}
