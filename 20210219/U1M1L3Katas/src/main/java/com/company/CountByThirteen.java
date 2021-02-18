package com.company;

import java.util.Scanner;

public class CountByThirteen {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a number, and I will count to it by 13s.");
        int usersNumber = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i <= usersNumber; i += 13){
            System.out.println(i);
        }
    }
}