package com.company;

import com.company.interfaces.Alarm;
import com.company.interfaces.Clock;

public class DigitalClock implements Clock, Alarm {
    @Override
    public void sound() {
        System.out.println("BEEP BEEP BEEP BEEP BEEP");
    }

    @Override
    public void displayTime() {
        System.out.println("Displaying time digitally.");
    }

    @Override
    public void timer(int hour, int minute) {
        System.out.println("You have " + hour + " hours and " + minute + " minutes left on your timer.");
    }
}
