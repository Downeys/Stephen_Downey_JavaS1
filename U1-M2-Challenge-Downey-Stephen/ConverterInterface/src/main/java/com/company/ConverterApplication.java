package com.company;

public class ConverterApplication {
    public static void main(String[] args) {
        ConverterIf ifConverter = new ConverterIf();
        ConverterSwitch switchConverter = new ConverterSwitch();
        System.out.println("Using the if converter.");
        for (int i = 1; i <= 13; i++) {
            System.out.println("Month " + i + " out of 12 is " + ifConverter.convertMonth(i));
        }

        for (int i = 1; i <= 8; i++) {
            System.out.println("Day " + i + " out of 7 is " + ifConverter.convertDay(i));
        }

        System.out.println("");
        System.out.println("Using the switch converter.");
        for (int i = 1; i <= 13; i++) {
            System.out.println("Month " + i + " out of 12 is " + switchConverter.convertMonth(i));
        }

        for (int i = 1; i <= 8; i++) {
            System.out.println("Day " + i + " out of 7 is " + switchConverter.convertDay(i));
        }
    }
}
