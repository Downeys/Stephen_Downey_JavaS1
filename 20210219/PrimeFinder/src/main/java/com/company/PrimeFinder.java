package com.company;

import java.util.Scanner;

public class PrimeFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number, any number.");
        int theNumber = Integer.parseInt(scanner.nextLine());
        for (int i = 2; i <= theNumber; i++) {
            boolean isNotPrime = false;
            for(int j = 2; j <= i; j++){
                if(i % j == 0 && j != i){
                    isNotPrime = true;
                }
            }
            if (!isNotPrime){
                System.out.println(i);
            }
        }
    }
}
