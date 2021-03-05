package com.company;

import java.util.*;

public class App {

    public void printKeys(Map<String, String> map){
        Set<String> keys = map.keySet();
        for (String key: keys) {
            System.out.println(key);
        }
    }

    public void printValues(Map<String, String> map){
        Collection<String> values = map.values();
        for (String value: values) {
            System.out.println(value);
        }
    }

    public void printKeysAndValues(Map<String, String> map){
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry: entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public Map<String, Integer> mapFun(Map<String, Integer> map){
        map.put("Ford Explorer", 2012);
        map.put("Smart Fortwo", 2013);
        map.remove("Jeep Wrangler");
        return map;
    }

    public Map<String, List<Car>> listCars(List<Car> cars){
        List<Car> toyotaList = new ArrayList<>();
        List<Car> fordList = new ArrayList<>();
        List<Car> hondaList = new ArrayList<>();
        Map<String, List<Car>> returnMap = new HashMap();

        for (Car car: cars) {
            switch (car.getMake()){
                case "Toyota":
                    toyotaList.add(car);
                    break;
                case "Ford":
                    fordList.add(car);
                    break;
                case "Honda":
                    hondaList.add(car);
                    break;
            }
            returnMap.put("Toyota", toyotaList);
            returnMap.put("Ford", fordList);
            returnMap.put("Honda", hondaList);

        }
        return returnMap;
    }

}
