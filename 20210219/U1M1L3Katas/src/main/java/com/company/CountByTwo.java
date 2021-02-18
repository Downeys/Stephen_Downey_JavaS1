package com.company;

import java.util.Scanner;
import java.util.Scanner;

public class CountByTwo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a number, and I will count to it by 2s.");
        int usersNumber = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i <= usersNumber; i += 2){
            System.out.println(i);
        }
    }
}
