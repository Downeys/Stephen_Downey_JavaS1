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

        System.out.println("How many times should we roll the dice?");
        int numberOfTimesToRollDice = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i < numberOfTimesToRollDice; i++){
            int dieOne = random.nextInt(6) + 1;
            int dieTwo = random.nextInt(6) + 1;
            int sum = dieOne + dieTwo;
            switch(sum){
                case 2:
                    timesRolled2++;
                    break;
                case 3:
                    timesRolled3++;
                    break;
                case 4:
                    timesRolled4++;
                    break;
                case 5:
                    timesRolled5++;
                    break;
                case 6:
                    timesRolled6++;
                    break;
                case 7:
                    timesRolled7++;
                    break;
                case 8:
                    timesRolled8++;
                    break;
                case 9:
                    timesRolled9++;
                    break;
                case 10:
                    timesRolled10++;
                    break;
                case 11:
                    timesRolled11++;
                    break;
                case 12:
                    timesRolled12++;
                    break;
            }
        }
        System.out.println("We rolled the dice " + numberOfTimesToRollDice + "times, and here are the results:");
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

    }
}
