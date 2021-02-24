package com.ourcompany;

import java.util.Random;
import java.util.Scanner;

public class ANumberGuessingGame {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        int randomNumber = rand.nextInt(10) + 1;
        System.out.println("TEH WORST NUBMER GESSING GAME EVAR!!!!!!!1!");
        System.out.println("I\"M THKING OF A NBR FROM 1-10.  TRY 2 GESS!");
        int userGuess = Integer.parseInt(scan.nextLine());
        if(userGuess == randomNumber){
            System.out.println("LOL!!! U GOT IT!  I CANT BELEIVE U GESSED IT WAS " + randomNumber + "!");
        }else{
            System.out.println("W00T!  U SUX0R!!!  I PWN J00!!!  IT WAS " + randomNumber + "!");
        }
    }
}
