package com.company.StephenDowneyU1Capstone.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Scanner;

public class PasswordUtility {
    public static void main(String[] args) {
        PasswordEncoder enc = new BCryptPasswordEncoder();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Enter a string to encode: ");
            String stringToEncode = scanner.nextLine();
            String encodedPassword = enc.encode(stringToEncode);
            System.out.println("An encoded version of " + stringToEncode + " is " + encodedPassword);
        } while (true);
    }
}
