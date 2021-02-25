package com.company;

import java.util.Random;
import java.util.Scanner;

public class ThreeCardMonte {

    public static void main(String[] args) {
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);
        int aceCard = 0;
        System.out.println("You slide up to Fast Eddie's card table and plop down your cash.");
        System.out.println("He glances at you out of the corner of his eye and starts shuffling.");
        System.out.println("He lays down three cards.");
        System.out.println("");
        System.out.println("Which one is the ace?");
        System.out.println("");
        cardDisplay(aceCard);
        System.out.println("");
        System.out.print(">");
        aceCard = rand.nextInt(3) + 1;
        int userChoice = Integer.parseInt(scan.nextLine());
        if(userChoice == aceCard){
            System.out.println("You nailed it! Fast Eddie reluctantly hands over your winnings, scowling.");
        } else {
            System.out.println("Ha! Fast Eddie wins again! The ace was card number " + aceCard);
        }
        System.out.println("");
        cardDisplay(aceCard);
    }

    public static void cardDisplay(int display){
        switch(display){
            case 0:
                System.out.format("%5s%s %3s%s %3s%s %n", "#", "#", "#", "#", "#", "#");
                System.out.format("%5s%s %3s%s %3s%s %n", "#", "#", "#", "#", "#", "#");
                break;
            case 1:
                System.out.format("%5s%s %3s%s %3s%s %n", "A", "A", "#", "#", "#", "#");
                System.out.format("%5s%s %3s%s %3s%s %n", "A", "A", "#", "#", "#", "#");
                break;
            case 2:
                System.out.format("%5s%s %3s%s %3s%s %n", "#", "#", "A", "A", "#", "#");
                System.out.format("%5s%s %3s%s %3s%s %n", "#", "#", "A", "A", "#", "#");
                break;
            case 3:
                System.out.format("%5s%s %3s%s %3s%s %n", "#", "#", "#", "#", "A", "A");
                System.out.format("%5s%s %3s%s %3s%s %n", "#", "#", "#", "#", "A", "A");
                break;
        }
    }

}
