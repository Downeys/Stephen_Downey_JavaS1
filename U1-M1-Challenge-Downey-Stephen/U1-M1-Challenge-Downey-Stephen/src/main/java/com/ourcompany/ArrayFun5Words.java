package com.ourcompany;

import java.util.Scanner;

public class ArrayFun5Words {

    public static void main(String[] args) {
        String[] userInputs = new String[5];
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < userInputs.length; i++) {
            System.out.println("Enter a word.");
            userInputs[i] = scan.nextLine();
        }
        for (int i = 0; i < userInputs.length; i++) {
            System.out.println(userInputs[i]);
        }
    }

}
