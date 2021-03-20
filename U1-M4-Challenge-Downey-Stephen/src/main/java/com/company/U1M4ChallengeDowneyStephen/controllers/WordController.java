package com.company.U1M4ChallengeDowneyStephen.controllers;

import com.company.U1M4ChallengeDowneyStephen.models.Definition;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class WordController {
    private final List<Definition> definitionList;

    public WordController(){
        Definition definition1 = new Definition("Borborygmus", "A rumbling or gurgling noise in the intestines");
        Definition definition2 = new Definition("Deipnophobia", "A morbid fear of dinner parties");
        Definition definition3 = new Definition("Erinaceous", "Of, pertaining to, or resembling a hedgehog");
        Definition definition4 = new Definition("Fudgel", "Pretending to work when you're not actually doing anything at all");
        Definition definition5 = new Definition("Impignorate", "To pawn or mortgage something");
        Definition definition6 = new Definition("Nudiustertian", "The day before yesterday");
        Definition definition7 = new Definition("Cashew", "The sound of sneezing");
        Definition definition8 = new Definition("Quomodocunquizing", "Making money in any way that you can");
        Definition definition9 = new Definition("Ulotrichous", "Having wooly or crispy hair");
        Definition definition10 = new Definition("Zoanthropy", "Delusion of a person who believes himself changed into an animal");
        definitionList = new ArrayList<>();
        definitionList.add(definition1);
        definitionList.add(definition2);
        definitionList.add(definition3);
        definitionList.add(definition4);
        definitionList.add(definition5);
        definitionList.add(definition6);
        definitionList.add(definition7);
        definitionList.add(definition8);
        definitionList.add(definition9);
        definitionList.add(definition10);
    }

    @RequestMapping(value = "/word", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Definition getDefinition() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(definitionList.size());
        return definitionList.get(randomNumber);
    }

    public List<Definition> getAllDefinitions(){
        return definitionList;
    }
}
