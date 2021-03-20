package com.company.U1M4ChallengeDowneyStephen.controllers;

import com.company.U1M4ChallengeDowneyStephen.models.Quote;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class QuoteController {
    private final List<Quote> quoteList;
    private final Random rand;


    public QuoteController(){
        rand = new Random();

        Quote quote1 = new Quote("Lao Tzu", "When I let go of what I am, I become what I might be.");
        Quote quote2 = new Quote("Lao Tzu", "A leader is best when people barely know he exists, " +
                "when his work is done, his aim fulfilled, they will say: we did it ourselves.");
        Quote quote3 = new Quote("Lao Tzu", "He who knows that enough is enough will always have enough.");
        Quote quote4 = new Quote("Benjamin Graham", "The intelligent investor is a realist who " +
                "sells to optimists and buys from pessimists.");
        Quote quote5 = new Quote("Benjamin Graham", "An investment operation is one which, " +
                "upon thorough analysis, promises safety of principal and an adequate return.");
        Quote quote6 = new Quote("Benjamin Graham", "In the short run, the market is a voting machine " +
                "but in the long run, it is a weighing machine.");
        Quote quote7 = new Quote("Ayn Rand", "The question isn't who is going to let me; " +
                "it's who is going to stop me.");
        Quote quote8 = new Quote("Ayn Rand", "I take no pride in hopeless longing; I wouldn't " +
                "hold a stillborn aspiration. I'd want to have it, to make it, to live it.");
        Quote quote9 = new Quote("Warren Buffet", "Someone's sitting in the shade today because " +
                "someone planted a tree a long time ago.");
        Quote quote10 = new Quote("Warren Buffet", "The most important quality for an investor is " +
                "temperament, not intellect. You need a temperament that neither derives great pleasure from " +
                "being with the crowd or against the crowd.");
        quoteList = new ArrayList<>();
        quoteList.add(quote1);
        quoteList.add(quote2);
        quoteList.add(quote3);
        quoteList.add(quote4);
        quoteList.add(quote5);
        quoteList.add(quote6);
        quoteList.add(quote7);
        quoteList.add(quote8);
        quoteList.add(quote9);
        quoteList.add(quote10);
    }

    @RequestMapping(value = "/quote", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Quote getRandomQuote(){
        int randomNumber = rand.nextInt(quoteList.size());
        return quoteList.get(randomNumber);
    }

    public List<Quote> getAllQuotes(){
        return quoteList;
    }
}
