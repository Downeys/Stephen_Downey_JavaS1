package com.company;

import java.util.Scanner;

public class AgeAgain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your age.");
        int age = Integer.parseInt(scanner.nextLine());
        if(age < 14){
            System.out.println("Ok. What grade are you in?");
            String grade = scanner.nextLine();
            System.out.println("Wow! " + grade + " grade - that sounds exciting!");
        }else if (age >= 14 && age <= 18){
            System.out.println("Ok. Are you planning on going to college?");
            String answer = scanner.nextLine();
            if (answer.equals("yes")){
                System.out.println("Cool. What college?");
                String college = scanner.nextLine();
                System.out.println(college + " is a great school!");
            }else if (answer.equals("no")){
                System.out.println("Cool. What are you going to do after high school then?");
                String plan = scanner.nextLine();
                System.out.println("Wow, " + plan + " sounds like a plan!");
            }
        }else if(age > 18){
            System.out.println("Ok. What is your job?");
            String job = scanner.nextLine();
            System.out.println(job + " sounds like a great job!");
        }
    }
}
