package com.company;

import java.util.Scanner;

public class App {

    public static int total(int[] userArray){
        int sum = 0;
        for(int i: userArray){
            sum += i;
        }
        return sum;
    }

    public static int totalOdd(int[] userArray){
        int sum = 0;
        for(int i = 1; i < userArray.length; i+=2){
            sum += userArray[i];
        }
        return sum;
    }

    public static int totalEven(int[] userArray){
        int sum = 0;
        for(int i = 0; i < userArray.length; i+=2){
            sum += userArray[i];
        }
        return sum;
    }

    public static int secondLargestNumber(int[] userArray){
        int largestNumber = Integer.MIN_VALUE;
        int secondLargestNumber = Integer.MIN_VALUE;

        for (int i = 0; i < userArray.length; i++) {
            if(userArray[i] > largestNumber){
                secondLargestNumber = largestNumber;
                largestNumber = userArray[i];
            }else if (userArray[i] > secondLargestNumber){
                secondLargestNumber = userArray[i];
            }
        }
        return secondLargestNumber;
    }

    public static String[] swapFirstAndLast(String[] userArray){
        String firstString = userArray[0];
        String lastString = userArray[userArray.length -1];
        userArray[0] = lastString;
        userArray[userArray.length -1] = firstString;
        return userArray;
    }

    public static int[] reverse(int[] userArray){
        int arrayLength = userArray.length;
        int[] returnArray = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            returnArray[arrayLength - 1 - i] = userArray[i];
        }
        return returnArray;
    }

    public static String concatenateString(String[] userArray){
        String bufferString = "";
        for(String nextString: userArray){
            bufferString = bufferString.concat(nextString);
        }
        return bufferString;
    }

    public static int[] everyThird(int[] userArray){
        if(userArray.length > 2){
            int arrayLength = userArray.length/3;
            int innerCounter = 0;
            int[] returnArray = new int[arrayLength];
            for (int i = 2; i < userArray.length; i += 3) {
                returnArray[innerCounter] = userArray[i];
                innerCounter++;
            }
            return returnArray;
        } else {
            return null;
        }
    }

    public static int[] lessThanFive(int[] userArray){
        int arrayLength = 0;
        String targetIndices = "";
        for (int i = 0; i < userArray.length; i++) {
            if(userArray[i] < 5){
                arrayLength++;
                targetIndices = targetIndices.concat(Integer.toString(i) + ",");
            }
        }
        String[] targetIndicesArray = targetIndices.split(",");
        int[] returnArray = new int[arrayLength];
        if(arrayLength > 0){
            for (int i = 0; i < arrayLength; i++) {
                returnArray[i] = userArray[Integer.parseInt(targetIndicesArray[i])];
            }
            return returnArray;
        } else {
            return null;
        }
    }

    public static int[][] splitAtFive(int[] userArray){
        //These two counters will determine the length of the inner arrays that will be returned.
        int lessThanFiveArrayLength = 0;
        int moreThanFiveArrayLength = 0;
        //These two strings will store the userArray indexes of their respective int ranges
        String lessThanFiveTargetIndices = "";
        String moreThanFiveTargetIndices = "";
        for (int i = 0; i < userArray.length; i++) {
            if(userArray[i] < 5){
                lessThanFiveArrayLength++;
                //every time I find a value less than five, I store its index in this String
                lessThanFiveTargetIndices = lessThanFiveTargetIndices.concat(Integer.toString(i) + ",");
            }else{
                moreThanFiveArrayLength++;
                //every time I find a value equal to or greater than five, I store its index in this String
                moreThanFiveTargetIndices =  moreThanFiveTargetIndices.concat(Integer.toString(i) + ",");
            }
        }
        //Now I break those index strings down into a String array of indexes for easier use.
        String[] lessThanFiveTargetIndicesArray = lessThanFiveTargetIndices.split(",");
        String[] moreThanFiveTargetIndicesArray = moreThanFiveTargetIndices.split(",");

        int[] lessThanFiveInnerArray = new int[lessThanFiveArrayLength];
        int[] moreThanFiveInnerArray = new int[moreThanFiveArrayLength];

        //These two for loops build the inner arrays using the indices we saved earlier
        for (int i = 0; i < lessThanFiveArrayLength; i++) {
            lessThanFiveInnerArray[i] = userArray[Integer.parseInt(lessThanFiveTargetIndicesArray[i])];
        }
        for (int i = 0; i < moreThanFiveArrayLength; i++) {
            moreThanFiveInnerArray[i] = userArray[Integer.parseInt(moreThanFiveTargetIndicesArray[i])];
        }
        //Then we build the return array out of the two arrays
        int[][] returnArray = {lessThanFiveInnerArray, moreThanFiveInnerArray};
        return returnArray;
    }

    public static String[][] evensAndOdds(String[] userArray){
        //These two counters will determine the length of the inner arrays that will be returned.
        int evensArrayLength = 0;
        int oddsArrayLength = 0;
        //These two strings will store the userArray indexes of their respective int ranges
        String evensTargetIndices = "";
        String oddsTargetIndices = "";
        for (int i = 0; i < userArray.length; i++) {
            if(i % 2 == 0){
                evensArrayLength++;
                //storing the even indexes in a string
                evensTargetIndices = evensTargetIndices.concat(Integer.toString(i) + ",");
            }else{
                oddsArrayLength++;
                //every time I find a value equal to or greater than five, I store its index in this String
                oddsTargetIndices =  oddsTargetIndices.concat(Integer.toString(i) + ",");
            }
        }
        //by breaking down my targetIndices strings into this array, I'll have an array of indices that is the
        //same length as the array I'm building which will allow me to use the same index for both arrays
        //in the for loop below.
        String[] evensTargetIndicesArray = evensTargetIndices.split(",");
        String[] oddsTargetIndicesArray = oddsTargetIndices.split(",");

        String[] evensInnerArray = new String[evensArrayLength];
        String[] oddsInnerArray = new String[oddsArrayLength];

        //These two for loops build the inner arrays using the indices we saved earlier
        for (int i = 0; i < evensArrayLength; i++) {
            evensInnerArray[i] = userArray[Integer.parseInt(evensTargetIndicesArray[i])];
        }
        for (int i = 0; i < oddsArrayLength; i++) {
            oddsInnerArray[i] = userArray[Integer.parseInt(oddsTargetIndicesArray[i])];
        }
        //Then we build the return array out of the two arrays
        String[][] returnArray = {evensInnerArray, oddsInnerArray};
        return returnArray;
    }

}
