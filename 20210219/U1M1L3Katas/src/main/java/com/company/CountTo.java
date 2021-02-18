package com.company;
import java.util.Scanner;

public class CountTo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a number, and I will count to it.");
        int usersNumber = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i <= usersNumber; i++){
            System.out.println(i);
        }
    }
}
