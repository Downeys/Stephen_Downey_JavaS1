package com.ourcompany;

import java.util.Random;
import java.util.Scanner;

public class HiLoWithLimitedGuesses {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        int randomNumber = rand.nextInt(100) + 1;
        int userGuess = 0;
        int numberOfTimesGuessed = 0;
        int guesseesLeft = 7;
        System.out.println("I have chosen a number between 1 and 100. You have 7 guesses.");
        do{
            System.out.print("Your guess: ");
            userGuess = Integer.parseInt(scan.nextLine());
            if(userGuess == randomNumber){
                System.out.println("You guessed it!  What are the odds?!?");
            } else if (userGuess < randomNumber){
                System.out.println("Sorry, you are too low.");
            } else {
                System.out.println("Sorry, that guess is too high.");
            }
            numberOfTimesGuessed++;
            guesseesLeft--;
        } while(userGuess != randomNumber && guesseesLeft > 0);
        if(userGuess != randomNumber){
            System.out.println("Sorry, you didn't guess it in 7 tries. You lose.");
        } else {
            System.out.println("It only took you " + numberOfTimesGuessed + " tries.");

        }
    }
}
