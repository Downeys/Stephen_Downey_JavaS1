package com.company;

import com.company.interfaces.Clock;

public class AnalogClock implements Clock {


    @Override
    public void displayTime() {
        System.out.println("*Displaying time*");
    }

    @Override
    public void timer(int hour, int minute) {
        System.out.println("You have " + hour + " hours and " + minute + " minutes left on your timer.");
    }
}
