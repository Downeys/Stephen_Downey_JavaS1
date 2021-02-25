package com.company;

import java.util.Random;

public class DiceDoubles {

    public static void main(String[] args) {
        Random rand = new Random();
        int die1 = 1;
        int die2 = 2;
        while(die1 != die2){
            die1 = rand.nextInt(6) + 1;
            die2 = rand.nextInt(6) + 1;
            int total = die1 + die2;
            System.out.println("HERE COMES THE DICE!");
            System.out.println("");
            System.out.println("Roll #1: " + die1);
            System.out.println("Roll #2: " + die2);
            System.out.println("The total is " + total + "!");
        }
    }

}
