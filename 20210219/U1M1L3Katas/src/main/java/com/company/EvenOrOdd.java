package com.company;

import java.util.Scanner;

public class EvenOrOdd {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a number, and I will tell you if it's even or odd.");
        int number = Integer.parseInt(scanner.nextLine());
        if(number%2 == 0){
            System.out.println("EVEN");
        } else{
            System.out.println("ODD");
        }

    }
}
