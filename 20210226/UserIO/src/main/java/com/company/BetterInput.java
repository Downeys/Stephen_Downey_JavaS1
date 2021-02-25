package com.company;

import com.company.interfaces.UserIO;

import java.util.Scanner;

public class BetterInput implements UserIO {
    private Scanner scan = new Scanner(System.in);

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        try{
            return Integer.parseInt(scan.nextLine());
        }
        catch (Exception e){
            return readInt(prompt);
        }
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        try{
            return Long.parseLong(scan.nextLine());
        }
        catch (Exception e){
            return readLong(prompt);
        }
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        try{
            return Double.parseDouble(scan.nextLine());
        }
        catch (Exception e){
            return readDouble(prompt);
        }
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        try{
            return Float.parseFloat(scan.nextLine());
        }
        catch (Exception e){
            return readFloat(prompt);
        }
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        try{
            return scan.nextLine();
        }
        catch (Exception e){
            return readString(prompt);
        }
    }
}
