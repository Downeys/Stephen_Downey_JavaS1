package com.company.U1M4ChallengeDowneyStephen.controllers;

import com.company.U1M4ChallengeDowneyStephen.models.Definition;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(WordController.class)
public class WordControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WordController wordController;

    ObjectMapper mapper = new ObjectMapper();

    List<Definition> definitionList;

    @Before
    public void setUp() {
        definitionList = wordController.getAllDefinitions();
    }

    @Test
    public void shouldReturnAnObjectWithOnlyTheExpectedKeysAndStatusOk() throws Exception {
        for (int i = 0; i < 20; i++) {
            mockMvc.perform(
                    get("/word")
            )
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").value(hasKey("id")))
                    .andExpect(jsonPath("$").value(hasKey("word")))
                    .andExpect(jsonPath("$").value(hasKey("definition")))
                    .andExpect(jsonPath("$", aMapWithSize(3)));

        }
    }

    @Test
    public void shouldReturnNonEmptyStringsForEachExpectedKey() throws Exception{
        for (int i = 0; i < 100; i++) {
            mockMvc.perform(
                    get("/word")
            )
                    .andDo(print())
                    .andExpect(jsonPath("$.id").isNotEmpty())
                    .andExpect(jsonPath("$.word").isNotEmpty())
                    .andExpect(jsonPath("$.definition").isNotEmpty());
        }
    }

    @Test
    public void ShouldReturnAQuoteFromExistingListOfQuotesAndStatusOk() throws Exception {
        //Arrange
        List<String> expectedJsonOutputString = new ArrayList<>();
        for (Definition definition : definitionList) {
            expectedJsonOutputString.add(mapper.writeValueAsString(definition));
        }

        //Act: perform the test 30 times to make sure that each time the result is one of the
        // definitions from the list
        for (int i = 0; i < 30; i++) {
            String actualJsonOutput = mockMvc.perform(
                    get("/word")
            )
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();

            boolean definitionListContainsReturnedJson = false;
            for (String expectedOutputString : expectedJsonOutputString) {
                if (expectedOutputString.equalsIgnoreCase(actualJsonOutput)) {
                    definitionListContainsReturnedJson = true;
                    break;
                }
            }

            //Assert
            assertTrue(definitionListContainsReturnedJson);
        }

    }

    @Test
    public void shouldReturnQuotesInRandomManner() throws Exception {
        //Arrange
        int[] definitionCounter = new int[definitionList.size()];

        for (int i = 0; i < definitionList.size() - 1; i++) {
            definitionCounter[i] = 0;
        }

        List<String> expectedJsonOutputString = new ArrayList<>();
        for (Definition definition : definitionList) {
            expectedJsonOutputString.add(mapper.writeValueAsString(definition));
        }

        double nbrOfTimesToRunTest = 1000;
        for (int i = 0; i < nbrOfTimesToRunTest; i++) {

            //This mockMBC returns the Quote as a json String
            String returnedJson = mockMvc.perform(
                    get("/word")
            )
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            for (int j = 0; j < expectedJsonOutputString.size() - 1; j++) {
                if (returnedJson.equalsIgnoreCase(expectedJsonOutputString.get(j))) {
                    definitionCounter[j]++;
                }
            }
        }

        //Assert
        //make sure that each counter got initialized in the if else block
        for (int i = 0; i < definitionCounter.length - 1; i++) {
            assertTrue(definitionCounter[i] > 0);
        }

        //make sure that no counter makes up 15% or more of the total test results
        double maxWeighting = .15;
        for (int i = 0; i < definitionCounter.length - 1; i++) {
            assertTrue(definitionCounter[i] / nbrOfTimesToRunTest < maxWeighting);
        }

    }

}