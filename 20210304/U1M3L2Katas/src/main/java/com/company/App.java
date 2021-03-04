package com.company;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static int total (ArrayList<Integer> numbers) {

        int sum = 0;
        for(int num : numbers) {
            sum += num;
        }

        return sum;
    }

    public static int totalEven (ArrayList<Integer> numbers) {

        int sum = 0;
        for(int i = 0; i < numbers.size(); i += 2) {
            sum += numbers.get(i);
        }

        return sum;
    }

    public static List<String> swapFirstAndLast(ArrayList<String> strings) {

        String temp = strings.get(0);
        strings.remove(0);
        strings.add(0, strings.get(strings.size() - 1));
        strings.remove(strings.size() - 1);
        strings.add(temp);

        return strings;
    }

    public static List<Integer> reverse(ArrayList<Integer> numbers) {

        List<Integer> reversed = new ArrayList<>();

        for(int i = 0; i < numbers.size(); i++) {

            // length - (i + 1) is the n-th to last element
            // so when i = 0, it would be the last element
            // when i = 3, it would be the fourth to last element since i=3 is the 4th element, etc
            reversed.add(numbers.get(numbers.size() - (i + 1)));
        }

        return reversed;
    }

    public static List<Integer> lessThanFive(ArrayList<Integer> numbers) {

        int numLessThanFive = 0;

        for(int num : numbers) {
            if ( num < 5 ) {
                numLessThanFive++;
            }
        }

        if ( numLessThanFive == 0 ) {
            return null;
        }

        List<Integer> lessThan = new ArrayList<>();

        for(int num : numbers) {
            if ( num < 5 ) {

                // subtracting numLessThanFive from length then decrementing numLessThanFive
                // allows us to go from 0 to length - 1 in order without additional variables
                lessThan.add(num);
            }
        }

        return lessThan;
    }

    //challenge
    public static List<List> splitAtFive(ArrayList<Integer> numbers) {
        int numLessThanFive = 0;
        int numMoreThanFive = 0;

        for(int num : numbers) {
            if ( num < 5 ) {
                numLessThanFive++;
            } else {
                numMoreThanFive++;
            }
        }

        List<Integer> lessThan = new ArrayList<>();
        List<Integer> moreThan = new ArrayList<>()  ;


        for(int num : numbers) {

            // subtracting numLessThanFive from length then decrementing numLessThanFive
            // allows us to go from 0 to length - 1 in order without additional variables
            // same with numMoreThanFive
            if ( num < 5 ) {
                lessThan.add(num);
            } else {
                moreThan.add(num);
            }
        }
        List<List> returnList = new ArrayList<>();
        returnList.add(lessThan);
        returnList.add(moreThan);


        return returnList;
    }

    // Challenge
    public static List<List> evensAndOdds(ArrayList<String> strings) {

        //leveraging integer division
        List<String> odds = new ArrayList<>();

        // Set evens to null for reassignment below
        List<String> evens = new ArrayList<>();


        for(int i = 0; i < strings.size(); i++) {
            int currIndex = i/2;
            if( i % 2 == 0 ) {
                evens.add(strings.get(i));
            } else {
                odds.add(strings.get(i));
            }
        }

        List<List> returnList = new ArrayList<>();
        returnList.add(evens);
        returnList.add(odds);

        return returnList;
    }
}
