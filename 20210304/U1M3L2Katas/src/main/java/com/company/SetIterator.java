package com.company;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SetIterator {

    public void printSet(int a, int b, int c, int d, int e){
        Set<Integer> returnList = new HashSet<Integer>();
        returnList.add(a);
        returnList.add(b);
        returnList.add(c);
        returnList.add(d);
        returnList.add(e);
        Iterator iterator = returnList.iterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
