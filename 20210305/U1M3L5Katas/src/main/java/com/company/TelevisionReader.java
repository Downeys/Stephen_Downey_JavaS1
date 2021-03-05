package com.company;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TelevisionReader {

    public static void main(String[] args) {
        try{
            //Kata 1: Read the Data Into a List
            List<Television> televisionList = FileIO.getTelevisions("televisions.csv");

            //Kata 2: Find and Print the Big Screen TVs
            System.out.println("Here are the televisions we have with large screen sizes.");
            televisionList
                    .stream()
                    .filter(tele -> tele.getScreenSize() > 60)
                    .forEach(tele -> {
                        System.out.println("The " + tele.getBrand() + " " + tele.getModel() +
                                " has a screen size of " + tele.getScreenSize() + " inches.");
                    });
            System.out.println("\n");

            //Kata 3: Group the TVs by Brand
            System.out.println("Our file has televisions of the following brands: ");
            Map<String, List<Television>> tvsGroupedByBrand = televisionList
                    .stream()
                    .collect(Collectors.groupingBy(tele -> tele.getBrand()));
            for (String brand: tvsGroupedByBrand.keySet()) {
                System.out.println(brand);
            }
            System.out.println("\n");

            //Kata 4: Find Average Screen Size
            double averageScreenSize = televisionList
                    .stream()
                    .mapToInt(tele -> tele.getScreenSize())
                    .average()
                    .getAsDouble();
            System.out.println("The average screen size of the televisions in our file is " + averageScreenSize);
            System.out.println("\n");

            //Kata 5: Find the Largest Screen
            int largestScreenSize = televisionList
                    .stream()
                    .mapToInt(tele -> tele.getScreenSize())
                    .max()
                    .getAsInt();
            System.out.println("The largest screen size available is " + largestScreenSize);
            System.out.println("\n");

            //Challenge Kata 6: Sort by Screen Size
            System.out.println("Here is the list of the TVs sorted from smallest to largest.");
            List<Television> sortedTelevisionList = televisionList
                    .stream()
                    .sorted(Comparator.comparingInt(Television::getScreenSize))
                    .collect(Collectors.toList());
            for (Television tele: sortedTelevisionList) {
                System.out.println("The " + tele.getBrand() + " " + tele.getModel() +
                        " has a screen size of " + tele.getScreenSize() + " inches.");
            }


        }catch (FileNotFoundException ex){
            System.out.println("File not found. " + ex.getMessage());
        }
    }
}
