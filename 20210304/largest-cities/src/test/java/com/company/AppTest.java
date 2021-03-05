package com.company;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class AppTest {
    Map<String, City> mapThatEveryTestShouldFilter;

    @Before
    public void setUp(){
        City newYork = new City("New York", 8654321);
        City losAngeles = new City("Los Angeles", 4563218);
        City chicago = new City("Chicago", 2716520);
        City denver = new City("Denver", 704621);
        City desMoines = new City("Des Moines", 217521);
        City atlanta = new City("Atlanta", 486213);

//    |       City      |    Population     |
//    |-----------------|-------------------|
//    | New York        |      8654321      |
//    | Los Angeles     |      4563218      |
//    | Chicago         |      2716520      |
//    | Denver          |       704621      |
//    | Des Moines      |       217521      |
//    | Atlanta         |       486213      |

        mapThatEveryTestShouldFilter = new HashMap<>();
        mapThatEveryTestShouldFilter.put("New York", newYork);
        mapThatEveryTestShouldFilter.put("California", losAngeles);
        mapThatEveryTestShouldFilter.put("Illinois", chicago);
        mapThatEveryTestShouldFilter.put("Colorado", denver);
        mapThatEveryTestShouldFilter.put("Iowa", desMoines);
        mapThatEveryTestShouldFilter.put("Georgia", atlanta);
    }

    @Test
    public void shouldReturnAllCitiesWithPopulationsLargerThanUserInput() {
        //Arrange
        Map<String, City> valueExpectedForTest1 = new HashMap<>();
        City newYork = new City("New York", 8654321);
        valueExpectedForTest1.put("New York", newYork);
        int userInputForTest1 = 5000000;
        Map<String, City> actualValueReturnedFromTest1 = new HashMap<>();

        Map<String, City> valueExpectedForTest2 = new HashMap<>();
        City losAngeles = new City("Los Angeles", 4563218 );
        valueExpectedForTest2.put("New York", newYork);
        valueExpectedForTest2.put("California", losAngeles);
        int userInputForTest2 = 3000000;
        Map<String, City> actualValueReturnedFromTest2 = new HashMap<>();

        Map<String, City> valueExpectedForTest3 = new HashMap<>();
        City chicago = new City("Chicago", 2716520);
        City denver = new City("Denver", 704621);
        City atlanta = new City("Atlanta", 486213);
        valueExpectedForTest3.put("New York", newYork);
        valueExpectedForTest3.put("California", losAngeles);
        valueExpectedForTest3.put("Illinois", chicago);
        valueExpectedForTest3.put("Colorado", denver);
        valueExpectedForTest3.put("Georgia", atlanta);
        int userInputForTest3 = 321654;
        Map<String, City> actualValueReturnedFromTest3 = new HashMap<>();

        //Act
        actualValueReturnedFromTest1 = App.filterByPopulation(mapThatEveryTestShouldFilter, userInputForTest1);
        actualValueReturnedFromTest2 = App.filterByPopulation(mapThatEveryTestShouldFilter, userInputForTest2);
        actualValueReturnedFromTest3 = App.filterByPopulation(mapThatEveryTestShouldFilter, userInputForTest3);

        //Assert
        assertEquals(valueExpectedForTest1, actualValueReturnedFromTest1);
        assertEquals(valueExpectedForTest2, actualValueReturnedFromTest2);
        assertEquals(valueExpectedForTest3, actualValueReturnedFromTest3);
    }
}