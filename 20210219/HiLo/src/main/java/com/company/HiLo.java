package com.company;

import java.util.Random;
import java.util.Scanner;

public class HiLo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int numberOfGuesses = 0;
        System.out.println("Welcome to Hi-Low!");
        System.out.println("What is your name?");
        String name = scanner.nextLine();
        System.out.println("OK, " + name + ", here are the rules:");
        int randomNumber = random.nextInt(100 ) + 1;
        int userGuess;
        System.out.println("I'm thinking of a number between 1-100 (inclusive). What number am I thinking of?");
        do{
            userGuess = Integer.parseInt(scanner.nextLine());
            if(userGuess > randomNumber){
                System.out.println("Too high!");
            }else if(userGuess < randomNumber){
                System.out.println("Too low!");
            }
            numberOfGuesses++;
        }while(userGuess != randomNumber);
        System.out.println("Congratulations, " + name + "! You win!");
        System.out.println("It took you " + numberOfGuesses + " guesses to find my number!");
    }
}
