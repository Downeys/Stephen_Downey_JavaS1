package com.company.StephenDowneyU1Capstone.daos;

import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.Game;

import java.util.List;

public interface GameDao {

    Game saveGame(Game game);
    Game findGame(Integer gameId) throws JdbcOperationFailedException;
    List<Game> findGameByStudio(String studio);
    List<Game> findGameByEsrbRating(String esrbRating);
    List<Game> findGameByTitle(String title);
    List<Game> findAllGames();
    void updateGame(Game game) throws JdbcOperationFailedException;
    void deleteGame(Integer gameId) throws JdbcOperationFailedException;
}
