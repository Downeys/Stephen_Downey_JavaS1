package com.ourcompany;

import java.util.Scanner;

public class ValidNumber {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int userInput;
        System.out.println("Please enter a number between 1-10.");
        do{
            userInput = Integer.parseInt(scan.nextLine());
            if(userInput > 10 || userInput < 1){
                System.out.println("You must enter a number between 1 and 10. Please try again.");
            }
        }while (userInput > 10  || userInput < 1);
        System.out.println(userInput);
    }

}
