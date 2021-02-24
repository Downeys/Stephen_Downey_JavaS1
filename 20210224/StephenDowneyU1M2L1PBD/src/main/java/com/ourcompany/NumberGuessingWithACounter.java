package com.ourcompany;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingWithACounter {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        int randomNumber = rand.nextInt(10) + 1;
        int userGuess = 0;
        int numberOfTimesGuessed = 0;
        System.out.println("I have chosen a number between 1 and 10. Try to guess it.");
        do{
            System.out.print("Your guess: ");
            userGuess = Integer.parseInt(scan.nextLine());
            if(userGuess == randomNumber){
                System.out.println("That's right. You're a good guesser.");
            }else{
                System.out.println("That is incorrect. Guess again.");
            }
            numberOfTimesGuessed++;
        }while(userGuess != randomNumber);
        System.out.println("It only took you " + numberOfTimesGuessed + " tries.");
    }
}
