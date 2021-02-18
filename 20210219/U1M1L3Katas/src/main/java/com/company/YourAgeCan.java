package com.company;

import java.util.Scanner;

public class YourAgeCan {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your age.");
        int age = Integer.parseInt(scanner.nextLine());
        if(age > 100){
            System.out.println("Is more than a century old");
        }
        if(age >= 80 && age <= 89){
            System.out.println("Is an octogenarian");
        }
        if(age >= 65){
            System.out.println("Can retire");
        }
        if(age >= 55){
            System.out.println("Is eligible for AARP");
        }
        if(age >= 35){
            System.out.println("Can be president");
        }
        if(age >= 21){
            System.out.println("Can drink alcohol");
        }
        if(age >= 18){
            System.out.println("Can vote");
        }
    }
}
