package com.company;

public class App {


	// subtract
    public static int subtract(int firstNumber, int secondNumber){
        return(firstNumber - secondNumber);
    }


	// subtractOrZero
    public static int subtractOrZero(int firstNumber,int secondNumber){
        int difference = subtract(firstNumber, secondNumber);
        if(difference > 0){
            return difference;
        }else{
            return 0;
        }
    }


	// max
    public static int max(int firstNumber, int secondNumber, int thirdNumber){
        return Math.max(Math.max(firstNumber, secondNumber), thirdNumber);
    }


	// min
    public static int min(int firstNumber, int secondNumber, int thirdNumber){
        return Math.min(Math.min(firstNumber, secondNumber), thirdNumber);
    }


	// isLarger
    public static boolean isLarger(int firstNumber, int secondNumber){
        if(firstNumber > secondNumber){
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {

    }
}
