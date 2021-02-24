package com.ourcompany;

import java.util.Random;

public class FortuneCookie {
    public static void main(String[] args) {

        Random rand = new Random();
        int[] lottoNumbers = new int[6];
        lottoNumbers = lottoBuilder(rand);
        int fortuneCookieNumber = rand.nextInt(6);
        switch (fortuneCookieNumber){
            case 0:
                System.out.println("The fortune you seek is in another cookie.");
                break;
            case 1:
                System.out.println("A closed mouth gathers no feet.");
                break;
            case 2:
                System.out.println("A foolish man listens to his heart. A wise man listens to cookies.");
                break;
            case 3:
                System.out.println("You will live long enough to open many fortune cookies.");
                break;
            case 4:
                System.out.println("He who laughs at himself never runs out of things to laugh at.");
                break;
            case 5:
                System.out.println("He who throws dirt is losing ground.");
                break;
        }
        System.out.format("%5d - %d - %d - %d - %d - %d", lottoNumbers[0], lottoNumbers[1], lottoNumbers[2],
                lottoNumbers[3], lottoNumbers[4], lottoNumbers[5]);
    }

    public static int[] lottoBuilder(Random rand){
        int[] returnArray = new int[6];
        for (int i = 0; i < 6; i++) {
            returnArray[i] = rand.nextInt(54) + 1;
        }
        return returnArray;
    }
}
