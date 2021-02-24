package com.ourcompany;

import java.util.Scanner;

public class ALittleQuiz {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numberAnsweredCorrectly = 0;
        int answer = 0;
        System.out.print("Are you ready for a quiz?");
        String thisStringDoesntSeemToDoAnythingInTheInstructions = scan.nextLine();
        System.out.println("Okay, here it comes!");
        System.out.println("");
        System.out.println("Q1) What is the capitol of Alaska?");
        System.out.format("%10s%n", "1) Melbourne");
        System.out.format("%10s%n", "2) Anchorage");
        System.out.format("%7s%n", "3) Juneau");
        System.out.println("");
        System.out.print(">");
        answer = Integer.parseInt(scan.nextLine());
        if(answer == 3){
            System.out.println("That's right!");
            numberAnsweredCorrectly++;
        } else {
            System.out.println("Sorry, Juneau is the capitol of Alaska.");
        }
        System.out.println("");

        System.out.println("Q2) Can you store the value \"cat\" in a variable of type int?");
        System.out.format("%10s%n", "1) yes");
        System.out.format("%9s%n", "2) no");
        System.out.println("");
        System.out.print(">");
        answer = Integer.parseInt(scan.nextLine());
        if(answer == 2){
            System.out.println("That's correct!");
            numberAnsweredCorrectly++;
        } else {
            System.out.println("Sorry, \"cat\" is a string. ints can only store numbers.");
        }
        System.out.println("");

        System.out.println("Q3) What is the result of 9+6/3?");
        System.out.format("%10s%n", "1) 5");
        System.out.format("%11s%n", "2) 11");
        System.out.format("%13s%n", "3) 15/3");
        System.out.println("");
        System.out.print(">");
        answer = Integer.parseInt(scan.nextLine());
        if(answer == 2){
            System.out.println("That's Correct!");
            numberAnsweredCorrectly++;
        } else {
            System.out.println("Sorry, the correct answer is 11.");
        }
        System.out.println("");
        System.out.println("");
        System.out.println("Overall, you got " + numberAnsweredCorrectly + " out of 3 correct.");
        System.out.println("Thanks for playing!");
    }
}
