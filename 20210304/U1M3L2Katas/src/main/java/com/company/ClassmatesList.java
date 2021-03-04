package com.company;

import java.util.ArrayList;
import java.util.List;

public class ClassmatesList {
    private List<Classmate> classmates = new ArrayList<>();

    public void add(Classmate classmate){
        this.classmates.add(classmate);
    }

    public Classmate get(int index){
        return this.classmates.get(index);
    }

    public List<Classmate> getAll(){
        return this.classmates;
    }
}
