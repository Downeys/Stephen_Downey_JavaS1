package com.company;

import java.util.Scanner;

public class UltimateQuestion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number;
        do{
            System.out.println("Please enter a number between 1-100");
            number = Integer.parseInt(scanner.nextLine());
        } while(number != 42);
        System.out.println("That's the number I was looking for! 42 is definitely the answer!");
    }
}
