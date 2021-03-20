package com.company.U1M4ChallengeDowneyStephen.controllers;

import com.company.U1M4ChallengeDowneyStephen.models.Quote;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(QuoteController.class)
public class QuoteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    QuoteController quoteController;

    ObjectMapper mapper = new ObjectMapper();

    List<Quote> quoteList;

    @Before
    public void setUp() {
        quoteList = quoteController.getAllQuotes();
    }

    @Test
    public void shouldReturnAnObjectWithOnlyTheExpectedKeys() throws Exception {
        for (int i = 0; i < 20; i++) {
            mockMvc.perform(
                    get("/quote")
            )
                    .andDo(print())
                    .andExpect(jsonPath("$").value(hasKey("id")))
                    .andExpect(jsonPath("$").value(hasKey("author")))
                    .andExpect(jsonPath("$").value(hasKey("quote")))
                    .andExpect(jsonPath("$", aMapWithSize(3)));
        }
    }

    @Test
    public void shouldReturnNonEmptyStringsForEachExpectedKey() throws Exception{
        for (int i = 0; i < 100; i++) {
            mockMvc.perform(
                    get("/quote")
            )
                    .andDo(print())
                    .andExpect(jsonPath("$.id").isNotEmpty())
                    .andExpect(jsonPath("$.author").isNotEmpty())
                    .andExpect(jsonPath("$.quote").isNotEmpty());
        }
    }

    @Test
    public void ShouldReturnAQuoteFromExistingListOfQuotesAndStatusOk() throws Exception {
        //Arrange
        List<String> expectedJsonOutputString = new ArrayList<>();
        for (Quote quote : quoteList) {
            expectedJsonOutputString.add(mapper.writeValueAsString(quote));
        }

        //Act: perform the test 30 times to make sure that each time the result is one of the
        // quotes from the list
        for (int i = 0; i < 30; i++) {
            String actualJsonOutput = mockMvc.perform(
                    get("/quote")
            )
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();

            boolean quoteListContainsReturnedJson = false;
            for (String expectedOutputString : expectedJsonOutputString) {
                if (expectedOutputString.equalsIgnoreCase(actualJsonOutput)) {
                    quoteListContainsReturnedJson = true;
                    break;
                }
            }

            //Assert
            assertTrue(quoteListContainsReturnedJson);
        }

    }

    @Test
    public void shouldReturnQuotesInRandomManner() throws Exception {
        //Arrange
        int[] quoteCounter = new int[quoteList.size()];

        for (int i = 0; i < quoteList.size() - 1; i++) {
            quoteCounter[i] = 0;
        }

        List<String> expectedJsonOutputString = new ArrayList<>();
        for (Quote quote : quoteList) {
            expectedJsonOutputString.add(mapper.writeValueAsString(quote));
        }

        double nbrOfTimesToRunTest = 1000;
        for (int i = 0; i < nbrOfTimesToRunTest; i++) {

            //This mockMBC returns the Quote as a json String
            String returnedJson = mockMvc.perform(
                    get("/quote")
            )
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            for (int j = 0; j < expectedJsonOutputString.size() - 1; j++) {
                if (returnedJson.equalsIgnoreCase(expectedJsonOutputString.get(j))) {
                    quoteCounter[j]++;
                }
            }
        }

        //Assert
        //make sure that each counter got initialized in the if else block
        for (int i = 0; i < quoteCounter.length - 1; i++) {
            assertTrue(quoteCounter[i] > 0);
        }

        //make sure that no counter makes up 15% or more of the total test results
        double maxWeighting = .15;
        for (int i = 0; i < quoteCounter.length - 1; i++) {
            assertTrue(quoteCounter[i] / nbrOfTimesToRunTest < maxWeighting);
        }

    }

}
