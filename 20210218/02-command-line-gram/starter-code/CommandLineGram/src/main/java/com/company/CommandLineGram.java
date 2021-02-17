package com.company;
import java.util.Scanner;

public class CommandLineGram {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your first name.");
        String nameFirst = scanner.nextLine();
        System.out.println("Please enter your last name.");
        String nameLast = scanner.nextLine();
        System.out.println("Please enter your email.");
        String email = scanner.nextLine();
        System.out.println("Please enter your twitter handle.");
        String twitterHandle = scanner.nextLine();
        System.out.println("Please enter your age.");
        String age = scanner.nextLine();
        System.out.println("What country are you from?");
        String country = scanner.nextLine();
        System.out.println("What is your profession?");
        String profession = scanner.nextLine();
        System.out.println("Tell me your favorite song.");
        String favoriteSong = scanner.nextLine();
        System.out.println("Tell me your favorite programming language.");
        String favoriteProgammingLanguage = scanner.nextLine();
        System.out.println("Tell me your favorite computer scientist.");
        String favoriteComputerScientist = scanner.nextLine();
        System.out.println("Tell me your favorite song keyboard shortcut.");
        String favoriteKeyboardShortcut = scanner.nextLine();
        System.out.println("Have you ever built your own computer?");
        String computerBuild = scanner.nextLine();
        System.out.println("If you could be any superhero, who would it be?");
        String superhero = scanner.nextLine();
        System.out.println(nameFirst);
        System.out.println(nameLast);
        System.out.println(email);
        System.out.println(twitterHandle);
        System.out.println(age);
        System.out.println(country);
        System.out.println(profession);
        System.out.println(favoriteSong);
        System.out.println(favoriteProgammingLanguage);
        System.out.println(favoriteComputerScientist);
        System.out.println(favoriteKeyboardShortcut);
        System.out.println(computerBuild);
        System.out.println(superhero);

    }
}
