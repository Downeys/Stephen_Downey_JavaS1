package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Application {

    public static void main(String[] args) {
            System.out.println("Current saved animals file:");
        try{
            Pets.readPetsFromFile();
        }catch (FileNotFoundException fnfEx){
            System.out.println("The following file does not seem to exist: " + fnfEx.getMessage());
        }finally {
            System.out.println("Have a nice day");
        }

        int petIndex;

        try{
            petIndex = Pets.choosePet();
        }catch (NumberFormatException nfEx){
            System.out.println( "Please enter a valid number.");
            petIndex = -1;
        }finally {
            System.out.println("Have a nice day");
        }


        String chosenPet = "";

        try{
            chosenPet = Pets.retrievePet(petIndex);
        }catch (ArrayIndexOutOfBoundsException aioobEx){
            System.out.println( "Please choose an available pet.");
        }finally {
            System.out.println("Have a nice day");
        }

        try{
            Pets.writePetToFile(chosenPet);
        }catch (IOException ioEx){
            System.out.println("Something went wrong while writing your file.");
        }finally {
            System.out.println("Have a nice day");
        }

        System.out.println("New saved animals file:");

        try{
            Pets.readPetsFromFile();
        }catch (FileNotFoundException fnfEx){
            System.out.println("The following file does not seem to exist: " + fnfEx.getMessage());
        }finally {
            System.out.println("Have a nice day");
        }

    }
}
