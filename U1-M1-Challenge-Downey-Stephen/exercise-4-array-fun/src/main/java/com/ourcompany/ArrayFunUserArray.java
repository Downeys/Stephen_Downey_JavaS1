package com.ourcompany;

import java.util.Scanner;

public class ArrayFunUserArray {
    public static void main(String[] args) {
        int[] userInputs = new int[5];
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < userInputs.length; i++) {
            System.out.println("Enter a number.");
            userInputs[i] = Integer.parseInt(scan.nextLine());
        }
        for (int i = 0; i < userInputs.length; i++) {
            System.out.println(userInputs[i]);
        }
    }
}
