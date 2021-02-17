package com.company;

import java.util.Scanner;

public class AddThirteen {

    public static void main(String[] args) {
        System.out.println("We're going to add 13 to the number you provide.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number.");
        int userNumber = Integer.parseInt(scanner.nextLine());
        int sum = userNumber + 13;
        System.out.println(userNumber + "+ 13 is " + sum + ".");
    }
}
