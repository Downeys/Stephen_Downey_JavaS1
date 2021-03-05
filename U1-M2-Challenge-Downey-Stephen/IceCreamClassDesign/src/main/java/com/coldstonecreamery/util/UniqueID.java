package com.coldstonecreamery.util;

import java.util.Random;

public class UniqueID {
    private String ID;

    public static String generateUniqueID(String name){
        Random rand = new Random();
        String returnVal;
        int idLength = 10;
        if(name.length() < 3){
            returnVal = name;
        }else{
            returnVal = name.substring(0, 3);
        }
        for (int i = 0; i < idLength - returnVal.length(); i++) {
            int nextVal = rand.nextInt(9) + 1;
            returnVal = returnVal.concat(Integer.toString(nextVal));
        }
        return returnVal;
    }
}
