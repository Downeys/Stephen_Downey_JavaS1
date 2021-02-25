package com.company;

import java.util.Scanner;

public class RightTriangleChecker {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double[] side = {-1, -1, -1};
        System.out.println("Enter three integers");
        while(side[0] <= 0) {
            System.out.print("Side 1: ");
            side[0] = Integer.parseInt(scan.nextLine());
            if(side[0] < 0){
                System.out.println(side[0] + " is less than 0. Try again.");
            }
        }
        while(side[1] < 0 && side[1] < side[0]) {
            System.out.print("Side 2: ");
            side[1] = Integer.parseInt(scan.nextLine());
            if(side[1] < side[0]){
                System.out.println(side[1] + " is less than " + side[1] + ". Try again.");
            }
        }
        while(side[2] < 0 && side[2] < side[1]) {
            System.out.print("Side 3: ");
            side[2] = Integer.parseInt(scan.nextLine());
            if(side[2] < side[1]){
                System.out.println(side[2] + " is less than " + side[1] + ". Try again.");
            }
        }
        System.out.format("Your three sides are %.0f %.0f %.0f.%n", side[0], side[1], side[2]);
        if(Math.pow(side[0], 2) + Math.pow(side[1], 2) == Math.pow(side[2], 2)){
            System.out.println("These sides *do* make a right triangle.  Yippy-skippy!");
        } else{
            System.out.println("NO!  These sides do not make a right triangle!");
        }
    }
}
