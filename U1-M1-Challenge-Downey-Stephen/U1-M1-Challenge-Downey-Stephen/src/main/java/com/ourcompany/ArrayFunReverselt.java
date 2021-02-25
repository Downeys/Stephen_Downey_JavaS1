package com.ourcompany;

public class ArrayFunReverselt {

    public static void main(String[] args) {
        int[] arrayProvidedByInstructions = {1, 2, 3, 4, 5};
        int arrayLength = arrayProvidedByInstructions.length;
        int[] reverseltArray = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            reverseltArray[arrayLength - 1 - i] = arrayProvidedByInstructions[i];
        }
        displayArrayElements(arrayProvidedByInstructions);
        displayArrayElements(reverseltArray);
    }

    public static void displayArrayElements(int[] arrayToDisplay){
        for(int i: arrayToDisplay){
            System.out.println(i);
        }
    }

}
