package com.company;

import java.util.Scanner;

public class LoanCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double loanAmount, interestRate, monthlyInterestRate, paymentAmount;
        int loanTerm;
        System.out.println("Enter the amount of your loan.");
        loanAmount = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter the term of your loan.");
        loanTerm = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the interest rate of your loan.");
        interestRate = Double.parseDouble(scanner.nextLine());
        monthlyInterestRate = (interestRate/100)/12;
        paymentAmount = loanAmount * (monthlyInterestRate * Math.pow((1 + monthlyInterestRate), loanTerm))/(Math.pow((1 + monthlyInterestRate), loanTerm) - 1);
        System.out.println("Your monthly payment will be " + paymentAmount + ".");
    }
}
