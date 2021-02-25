package com.company;

import java.util.Scanner;

public class AddingValuesInALoop {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("I will add the values you give me.");
        int userInput = 1;
        int runningTotal = 0;
        while(userInput != 0){
            System.out.print("Number: ");
            userInput = Integer.parseInt(scan.nextLine());
            runningTotal += userInput;
            System.out.println("The total so far is " + runningTotal);
        }
        System.out.println("The total is " + runningTotal + ".");
    }

}
