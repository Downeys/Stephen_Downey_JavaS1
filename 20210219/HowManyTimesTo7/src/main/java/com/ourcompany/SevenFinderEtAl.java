package com.ourcompany;

import java.util.Random;
import java.util.Scanner;

public class SevenFinderEtAl {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int timesRolled2 = 0;
        int timesRolled3 = 0;
        int timesRolled4 = 0;
        int timesRolled5 = 0;
        int timesRolled6 = 0;
        int timesRolled7 = 0;
        int timesRolled8 = 0;
        int timesRolled9 = 0;
        int timesRolled10 = 0;
        int timesRolled11 = 0;
        int timesRolled12 = 0;

        int timesRolledTo2 = 0;
        int timesRolledTo3 = 0;
        int timesRolledTo4 = 0;
        int timesRolledTo5 = 0;
        int timesRolledTo6 = 0;
        int timesRolledTo7 = 0;
        int timesRolledTo8 = 0;
        int timesRolledTo9 = 0;
        int timesRolledTo10 = 0;
        int timesRolledTo11 = 0;
        int timesRolledTo12 = 0;

        boolean hasRolled2 = false;
        boolean hasRolled3 = false;
        boolean hasRolled4 = false;
        boolean hasRolled5 = false;
        boolean hasRolled6 = false;
        boolean hasRolled7 = false;
        boolean hasRolled8 = false;
        boolean hasRolled9 = false;
        boolean hasRolled10 = false;
        boolean hasRolled11 = false;
        boolean hasRolled12 = false;


        System.out.println("How many times should we roll the dice?");
        int numberOfTimesToRollDice = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i < numberOfTimesToRollDice; i++){
            int dieOne = random.nextInt(6) + 1;
            int dieTwo = random.nextInt(6) + 1;
            int sum = dieOne + dieTwo;
            if(hasRolled2 == false){
                timesRolledTo2++;
            }
            if(hasRolled3 == false){
                timesRolledTo3++;
            }
            if(hasRolled4 == false){
                timesRolledTo4++;
            }
            if(hasRolled5 == false){
                timesRolledTo5++;
            }
            if(hasRolled6 == false){
                timesRolledTo6++;
            }
            if(hasRolled7 == false){
                timesRolledTo7++;
            }
            if(hasRolled8 == false){
                timesRolledTo8++;
            }
            if(hasRolled9 == false){
                timesRolledTo9++;
            }
            if(hasRolled10 == false){
                timesRolledTo10++;
            }
            if(hasRolled11 == false){
                timesRolledTo11++;
            }
            if(hasRolled12 == false) {
                timesRolledTo12++;
            }
            switch(sum){
                case 2:
                    timesRolled2++;
                    hasRolled2 = true;
                    break;
                case 3:
                    timesRolled3++;
                    hasRolled3 = true;
                    break;
                case 4:
                    timesRolled4++;
                    hasRolled4 = true;
                    break;
                case 5:
                    timesRolled5++;
                    hasRolled5 = true;
                    break;
                case 6:
                    timesRolled6++;
                    hasRolled6 = true;
                    break;
                case 7:
                    timesRolled7++;
                    hasRolled7 = true;
                    break;
                case 8:
                    timesRolled8++;
                    hasRolled8 = true;
                    break;
                case 9:
                    timesRolled9++;
                    hasRolled9 = true;
                    break;
                case 10:
                    timesRolled10++;
                    hasRolled10 = true;
                    break;
                case 11:
                    timesRolled11++;
                    hasRolled11 = true;
                    break;
                case 12:
                    timesRolled12++;
                    hasRolled12 = true;
                    break;
            }
        }
        System.out.println("We rolled the dice " + numberOfTimesToRollDice + " times, and here are the results:");
        System.out.println("Two was rolled " + timesRolled2 + " times.");
        System.out.println("Three was rolled " + timesRolled3 + " times.");
        System.out.println("Four was rolled " + timesRolled4 + " times.");
        System.out.println("Five was rolled " + timesRolled5 + " times.");
        System.out.println("Six was rolled " + timesRolled6 + " times.");
        System.out.println("Seven was rolled " + timesRolled7 + " times.");
        System.out.println("Eight was rolled " + timesRolled8 + " times.");
        System.out.println("Nine was rolled " + timesRolled9 + " times.");
        System.out.println("Ten was rolled " + timesRolled10 + " times.");
        System.out.println("Eleven was rolled " + timesRolled11 + " times.");
        System.out.println("Twelve was rolled " + timesRolled12 + " times.");

        System.out.println("");

        if(hasRolled2){
            System.out.println("It took " + timesRolledTo2 + " rolls before we rolled two.");
        }else{
            System.out.println("Two was never rolled.");
        }
        if(hasRolled3){
            System.out.println("It took " + timesRolledTo3 + " rolls before we rolled three.");
        }else{
            System.out.println("Three was never rolled.");
        }
        if(hasRolled4){
            System.out.println("It took " + timesRolledTo4 + " rolls before we rolled four.");
        }else{
            System.out.println("Four was never rolled.");
        }
        if(hasRolled5){
            System.out.println("It took " + timesRolledTo5 + " rolls before we rolled five.");
        }else{
            System.out.println("Five was never rolled.");
        }
        if(hasRolled6){
            System.out.println("It took " + timesRolledTo6 + " rolls before we rolled six.");
        }else{
            System.out.println("Six was never rolled.");
        }
        if(hasRolled7){
            System.out.println("It took " + timesRolledTo7 + " rolls before we rolled seven.");
        }else{
            System.out.println("A seven was never rolled.");
        }
        if(hasRolled8){
            System.out.println("It took " + timesRolledTo8 + " rolls before we rolled eight.");
        }else{
            System.out.println("Eight was never rolled.");
        }
        if(hasRolled9){
            System.out.println("It took " + timesRolledTo9 + " rolls before we rolled nine.");
        }else{
            System.out.println("Nine was never rolled.");
        }
        if(hasRolled10){
            System.out.println("It took " + timesRolledTo10 + " rolls before we rolled ten.");
        }else{
            System.out.println("Ten was never rolled.");
        }
        if(hasRolled11){
            System.out.println("It took " + timesRolledTo11 + " rolls before we rolled eleven.");
        }else{
            System.out.println("Eleven was never rolled.");
        }
        if(hasRolled12){
            System.out.println("It took " + timesRolledTo12 + " rolls before we rolled twelve.");
        }else{
            System.out.println("Twelve was never rolled.");
        }
    }
}
