package com.company.U1M4ChallengeDowneyStephen.controllers;

import com.company.U1M4ChallengeDowneyStephen.exceptions.NotAQuestionException;
import com.company.U1M4ChallengeDowneyStephen.models.Answer;
import com.company.U1M4ChallengeDowneyStephen.models.Question;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class Magic8BallController {
    private final List<Answer> answerList;
    private final Random rand;

    public Magic8BallController(){
        rand = new Random();

        Answer answer1 = new Answer("As I see it, yes.");
        Answer answer2 = new Answer("Ask again later.");
        Answer answer3 = new Answer("Better not tell you now.");
        Answer answer4 = new Answer("Don't count on it.");
        Answer answer5 = new Answer("Most likely.");
        Answer answer6 = new Answer("My sources say no.");
        Answer answer7 = new Answer("Signs point to yes.");
        Answer answer8 = new Answer("Very doubtful.");
        Answer answer9 = new Answer("Without a doubt.");
        Answer answer10 = new Answer("You may rely on it.");

        answerList = new ArrayList<>();
        answerList.add(answer1);
        answerList.add(answer2);
        answerList.add(answer3);
        answerList.add(answer4);
        answerList.add(answer5);
        answerList.add(answer6);
        answerList.add(answer7);
        answerList.add(answer8);
        answerList.add(answer9);
        answerList.add(answer10);
    }

    //maybe throw a custom error when the question does not end with a question mark?
    @RequestMapping(value = "/magic", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Answer getAnswer(@RequestBody @Valid Question q) throws NotAQuestionException{
        if(!q.getQuestion().matches(".*\\?")){
            throw  new NotAQuestionException("Please phrase your request in the form of a question.");
        }

        int randomNumber = rand.nextInt(answerList.size());
        answerList.get(randomNumber).setQuestion(q.getQuestion());
        return answerList.get(randomNumber);
    }

    public List<Answer> getAllAnswers(){
        return answerList;
    }

}

