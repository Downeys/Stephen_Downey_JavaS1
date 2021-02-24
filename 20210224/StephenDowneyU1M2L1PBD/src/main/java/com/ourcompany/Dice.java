package com.ourcompany;

import java.util.Random;

public class Dice {
    public static void main(String[] args) {
        Random rand = new Random();
        int die1 = rand.nextInt(6) + 1;
        int die2 = rand.nextInt(6) + 1;
        int total = die1 + die2;

        System.out.println("HERE COMES THE DICE!");
        System.out.println("");
        System.out.println("Roll #1: " + die1);
        System.out.println("Roll #2: " + die2);
        System.out.println("The total is " + total + "!");

    }
}
