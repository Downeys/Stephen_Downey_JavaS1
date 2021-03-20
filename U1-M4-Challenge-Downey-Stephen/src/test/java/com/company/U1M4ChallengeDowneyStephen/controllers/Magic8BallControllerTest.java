package com.company.U1M4ChallengeDowneyStephen.controllers;

import com.company.U1M4ChallengeDowneyStephen.models.Answer;
import com.company.U1M4ChallengeDowneyStephen.models.Question;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(Magic8BallController.class)
public class Magic8BallControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    Magic8BallController magic8BallController;

    ObjectMapper mapper = new ObjectMapper();

    List<Answer> answerList;

    @Before
    public void setUp() {
        answerList = magic8BallController.getAllAnswers();
    }

    @Test
    public void shouldReturnAnObjectWithOnlyTheExpectedKeysAndStatusOK() throws Exception {
        Question test1 = new Question("Will I pass this class?");
        Question test2 = new Question("Will Cognizant hire me?");
        Question test3 = new Question("What is the meaning of life, the universe, and everything?");

        String inputStringForTest1 = mapper.writeValueAsString(test1);
        String inputStringForTest2 = mapper.writeValueAsString(test2);
        String inputStringForTest3 = mapper.writeValueAsString(test3);

        mockMvc.perform(
                post("/magic")
                        .content(inputStringForTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(jsonPath("$").value(hasKey("id")))
                .andExpect(jsonPath("$").value(hasKey("question")))
                .andExpect(jsonPath("$").value(hasKey("answer")))
                .andExpect(jsonPath("$", aMapWithSize(3)));


        mockMvc.perform(
                post("/magic")
                        .content(inputStringForTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(jsonPath("$").value(hasKey("id")))
                .andExpect(jsonPath("$").value(hasKey("question")))
                .andExpect(jsonPath("$").value(hasKey("answer")))
                .andExpect(jsonPath("$", aMapWithSize(3)));


        mockMvc.perform(
                post("/magic")
                        .content(inputStringForTest3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(jsonPath("$").value(hasKey("id")))
                .andExpect(jsonPath("$").value(hasKey("question")))
                .andExpect(jsonPath("$").value(hasKey("answer")))
                .andExpect(jsonPath("$", aMapWithSize(3)));
    }

    @Test
    public void shouldReturnNonEmptyStringsForEachExpectedKey() throws Exception {
        Question test1 = new Question("Should I have another piece of pizza?");

        String inputStringForTest1 = mapper.writeValueAsString(test1);

        for (int i = 0; i < 100; i++) {
            mockMvc.perform(
                    post("/magic")
                            .content(inputStringForTest1)
                            .contentType(MediaType.APPLICATION_JSON)
            )
                    .andDo(print())
                    .andExpect(jsonPath("$.id").isNotEmpty())
                    .andExpect(jsonPath("$.question").isNotEmpty())
                    .andExpect(jsonPath("$.answer").isNotEmpty()); }

    }

    @Test
    public void shouldReturnAnswersInARandomManner() throws Exception {
        //Arrange
        Question testQuestion = new Question("Am I doing this right?");

        String inputStringForTheTest = mapper.writeValueAsString(testQuestion);

        //these counters will count how many times each possible Answer is returned
        int[] answerCounter = new int[answerList.size()];

        for (int i = 0; i < answerList.size() - 1; i++) {
            answerCounter[i] = 0;
        }

        //first, add the question to all of the answer possibilities to have a complete object to "expect"
        for (Answer answer : answerList) {
            answer.setQuestion(testQuestion.getQuestion());
        }

        //then, convert all of the complete objects to json String
        List<String> expectedJsonOutputString = new ArrayList<>();
        for (Answer answer : answerList) {
            expectedJsonOutputString.add(mapper.writeValueAsString(answer));
        }

        double nbrOfTimesToRunTest = 1000;
        for (int i = 0; i < nbrOfTimesToRunTest; i++) {

            //This mockMBC returns the Quote as a json String
            String returnedJson = mockMvc.perform(
                    post("/magic")
                            .content(inputStringForTheTest)
                            .contentType(MediaType.APPLICATION_JSON)
            )
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            for (int j = 0; j < expectedJsonOutputString.size() - 1; j++) {
                if (returnedJson.equalsIgnoreCase(expectedJsonOutputString.get(j))) {
                    answerCounter[j]++;
                }
            }
        }

        //Assert
        //make sure that each counter got initialized in the if else block
        for (int i = 0; i < answerCounter.length - 1; i++) {
            assertTrue(answerCounter[i] > 0);
        }

        //make sure that no counter makes up 15% or more of the total test results
        double maxWeighting = .15;
        for (int i = 0; i < answerCounter.length - 1; i++) {
            assertTrue(answerCounter[i] / nbrOfTimesToRunTest < maxWeighting);
        }
    }

    @Test
    public void shouldReturnAnswerContainingTheSameMessageThatWasSentToTheApi() throws Exception {
        Question test1 = new Question("Is there anybody out there?"); //Pink Floyd
        Question test2 = new Question("Could you be loved?"); //Bob Marley
        Question test3 = new Question("Do you really want to hurt me?"); //Culture Club

        String inputStringForTest1 = mapper.writeValueAsString(test1);
        String inputStringForTest2 = mapper.writeValueAsString(test2);
        String inputStringForTest3 = mapper.writeValueAsString(test3);

        mockMvc.perform(
                post("/magic")
                        .content(inputStringForTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(jsonPath("$.question").value(test1.getQuestion()));


        mockMvc.perform(
                post("/magic")
                        .content(inputStringForTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(jsonPath("$.question").value(test2.getQuestion()));

        mockMvc.perform(
                post("/magic")
                        .content(inputStringForTest3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(jsonPath("$.question").value(test3.getQuestion()));
    }

    @Test
    public void shouldReturnUnprocessableEntityStatusWhenPassedAnEmptyQuestion() throws Exception {
        Question test1 = new Question();
        Question test2 = new Question("");

        String inputStringForTest1 = mapper.writeValueAsString(test1);
        String inputStringForTest2 = mapper.writeValueAsString(test2);

        mockMvc.perform(
                post("/magic")
                        .content(inputStringForTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                post("/magic")
                        .content(inputStringForTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturnStatusBadRequestWhenQuestionDoesNotEndWithQuestionMark() throws Exception {
        Question test1 = new Question("Everything is awesome!");
        Question test2 = new Question("Everything is cool when you're part of a team.");
        Question test3 = new Question("Everything is awesome when you're living on a dream");

        String inputStringForTest1 = mapper.writeValueAsString(test1);
        String inputStringForTest2 = mapper.writeValueAsString(test2);
        String inputStringForTest3 = mapper.writeValueAsString(test3);

        mockMvc.perform(
                post("/magic")
                        .content(inputStringForTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isBadRequest());


        mockMvc.perform(
                post("/magic")
                        .content(inputStringForTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isBadRequest());

        mockMvc.perform(
                post("/magic")
                        .content(inputStringForTest3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}