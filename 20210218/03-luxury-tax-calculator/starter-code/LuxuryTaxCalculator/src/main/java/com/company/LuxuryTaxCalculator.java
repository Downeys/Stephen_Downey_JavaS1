package com.company;
import java.util.Scanner;

public class LuxuryTaxCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int teamSpendingLimit = 40000000;
        double luxuryTaxRate = .18;
        double taxesPayable = 0;
        System.out.println("In this program we will determine your luxury tax payable based on the three" +
                "salaries you enter here.");
        System.out.println("Please enter the first salary.");
        int firstSalary = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter the second salary.");
        int secondSalary = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter the third salary.");
        int thirdSalary = Integer.parseInt(scanner.nextLine());
        int totalSalary = firstSalary + secondSalary + thirdSalary;
        System.out.println("The total salary expense for these three players was: " + totalSalary);
        if(totalSalary > teamSpendingLimit){
            double taxableSalaryPaid = totalSalary - teamSpendingLimit;
            taxesPayable = taxableSalaryPaid * luxuryTaxRate;
        }
        System.out.println("Taxes payable will be " + taxesPayable);
/*        If the team exceeded their spending limit, calculate and display the cost of the luxury tax.


        The team's spending limit is $40,000,000.


        The tax rate is 18%. Note that the 18% luxury tax is only paid on the the amount of salary OVER
        the team's spending limit. For example, if a team's payroll is $55,000,000, they only pay luxury
        tax on $15,000,000.*/

    }
}
