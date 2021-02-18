package com.company;

import java.util.Scanner;

public class FavoriteProgrammingLanguage {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String programmingLanguage;
        do{
            System.out.println("What is your favorite programming language. Answer wisely.");
            programmingLanguage = scanner.nextLine();
        } while(!programmingLanguage.equals("Java"));
        System.out.println("That's what I was looking for! Java is definitely the answer!");
    }
}
