package com.ourcompany;

import java.util.Scanner;

public class SpaceBoxing {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Pleased enter your current Earth weight:");
        double currentEarthWeight = Integer.parseInt(scan.nextLine());
        System.out.println("I have information for the following planets:");
        System.out.format("%10s%10s%15s%n", "1. Venus", "2. Mars", "3. Jupiter");
        System.out.format("%11s%11s%13s%n", "4. Saturn", "5. Uranus", "6. Neptune");
        System.out.println("Which planet are you visiting?");
        int planet = Integer.parseInt(scan.nextLine());
        double weightOnForeignPlanet = 0;
        switch(planet) {
            case 1:
                weightOnForeignPlanet = currentEarthWeight * .78;
                break;
            case 2:
                weightOnForeignPlanet = currentEarthWeight * .39;
                break;
            case 3:
                weightOnForeignPlanet = currentEarthWeight * 2.65;
                break;
            case 4:
                weightOnForeignPlanet = currentEarthWeight * 1.17;
                break;
            case 5:
                weightOnForeignPlanet = currentEarthWeight * 1.05;
                break;
            case 6:
                weightOnForeignPlanet = currentEarthWeight * 1.23;
                break;
            default:
                System.out.println("Invalid input");
        }
        System.out.println("Your weight would be " + weightOnForeignPlanet + " pounds on that planet.");
    }
}
