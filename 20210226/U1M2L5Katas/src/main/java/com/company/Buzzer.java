package com.company;

import com.company.interfaces.Alarm;

public class Buzzer implements Alarm {
    @Override
    public void sound() {
        System.out.println("Buzzzzzz");
    }
}
