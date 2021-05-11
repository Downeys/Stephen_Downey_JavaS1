package com.company.StephenDowneyU1Capstone.controllers;

import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.Game;
import com.company.StephenDowneyU1Capstone.service.ServiceLayer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class GameController {

    private ServiceLayer service;

    public GameController(ServiceLayer service){
        this.service = service;
    }

    @RequestMapping(value = "/game", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Game addGame(@RequestBody @Valid Game game){
        return service.addGame(game);
    }

    @RequestMapping(value = "/game/{gameId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Game getGame(@PathVariable Integer gameId) throws JdbcOperationFailedException {
        return service.getGame(gameId);
    }

    @RequestMapping(value = "/game", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Game> getListOfGames(@RequestParam(required = false) String studio,
                                        @RequestParam(required = false) String rating,
                                        @RequestParam(required = false) String title){
        if(studio != null){
            return service.getGameByStudio(studio);
        }

        if(rating != null){
            return service.getGameByEsrbRating(rating);
        }

        if(title != null){
            return service.getGameByTitle(title);
        }

        return service.getAllGames();
    }

    @RequestMapping(value = "/game", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateGame(@RequestBody @Valid Game game) throws JdbcOperationFailedException{
        service.updateGame(game);
    }

    @RequestMapping(value = "/game/{gameId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable Integer gameId) throws JdbcOperationFailedException{
        service.deleteGame(gameId);
    }
}
