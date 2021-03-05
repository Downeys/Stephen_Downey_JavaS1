package com.company;

import java.util.*;

public class App {

    public static void main(String[] args) {
        City newYork = new City("New York", 8654321);
        City losAngeles = new City("Los Angeles", 4563218);
        City chicago = new City("Chicago", 2716520);
        City denver = new City("Denver", 704621);
        City desMoines = new City("Des Moines", 217521);
        City atlanta = new City("Atlanta", 486213);


        Map<String, City> citiesMappedToStates = new HashMap<>();
        citiesMappedToStates.put("New York", newYork);
        citiesMappedToStates.put("California", losAngeles);
        citiesMappedToStates.put("Illinois", chicago);
        citiesMappedToStates.put("Colorado", denver);
        citiesMappedToStates.put("Iowa", desMoines);
        citiesMappedToStates.put("Georgia", atlanta);

        Set<String> mapKeys = citiesMappedToStates.keySet();
        Collection<City> cities = citiesMappedToStates.values();

        for (String state: mapKeys) {
            String city = citiesMappedToStates.get(state).getName();
            int population = citiesMappedToStates.get(state).getPopulation();
            System.out.println(city + ", " + state +  " has a population of " + population + ".");
        }
        System.out.println("");

        Scanner scan = new Scanner(System.in);
        int population = -1;
        System.out.println("Please enter a population, and I will provide you with a list of" +
                " cities with populations larger than the one you entered.");

        while(population < 0){
            try{
                population = Integer.parseInt(scan.nextLine());
                if(population < 0){
                    System.out.println("Please enter a positive number.");
                }
            }catch (NumberFormatException ex){
                System.out.println("Please enter a number.");
            }
        }

        Map<String, City> newUserMap = new HashMap<>();
        newUserMap = filterByPopulation(citiesMappedToStates, population);
        displayResults(newUserMap);

    }

    public static Map<String, City> filterByPopulation(Map<String, City> cityMap, int population){
        Map<String, City> newCityMap = new HashMap<>();
        Set<String> keys = cityMap.keySet();
        for (String key: keys) {
            if(cityMap.get(key).getPopulation() >= population){
                newCityMap.put(key, cityMap.get(key));
            }
        }
        return newCityMap;

    }

    public static void displayHeader(){
        System.out.println("");
        System.out.println("|       City      |    Population     |");
        System.out.println("|-----------------|-------------------|");
    }

    public static void displayResults(Map<String, City> cityMap){
        displayHeader();
        Set<String> outputKey = cityMap.keySet();
        Map<String, String> outputLine = new HashMap<>();
        outputLine.put("New York", "| New York        |      8654321      |");
        outputLine.put("California", "| Los Angeles     |      4563218      |");
        outputLine.put("Illinois", "| Chicago         |      2716520      |");
        outputLine.put("Colorado", "| Denver          |       704621      |");
        outputLine.put("Iowa", "| Des Moines      |       217521      |");
        outputLine.put("Georgia", "| Atlanta         |       486213      |");

        for (String key: outputKey) {
            System.out.println(outputLine.get(key));
        }
    }


}
