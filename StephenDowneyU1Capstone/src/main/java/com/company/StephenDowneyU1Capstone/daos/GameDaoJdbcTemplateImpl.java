package com.company.StephenDowneyU1Capstone.daos;

import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GameDaoJdbcTemplateImpl implements GameDao{

    private static final String INSERT_GAME_SQL =
            "insert into game (title, esrb_rating, description, price, studio, quantity) " +
                    "values (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_GAME_SQL =
            "select * from game where game_id = ?";
    private static final String SELECT_GAME_BY_STUDIO_SQL =
            "select * from game where studio = ?";
    private static final String SELECT_GAME_BY_RATING_SQL =
            "select * from game where esrb_rating = ?";
    private static final String SELECT_GAME_BY_TITLE_SQL =
            "select * from game where title = ?";
    private static final String SELECT_ALL_GAME_SQL =
            "select * from game";
    private static final String UPDATE_GAME_SQL =
            "update game set title = ?, esrb_rating = ?, description = ?, price = ?, studio = ?, quantity = ? " +
                    "where game_id = ?";
    private static final String DELETE_GAME_SQL =
            "delete from game where game_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Game saveGame(Game game) {
        jdbcTemplate.update(INSERT_GAME_SQL, game.getTitle(), game.getEsrbRating(), game.getDescription(),
                game.getPrice(), game.getStudio(), game.getQuantity());
        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        game.setItemId(id);
        return game;
    }

    @Override
    public Game findGame(Integer gameId) throws JdbcOperationFailedException {
        try{
            return jdbcTemplate.queryForObject(SELECT_GAME_SQL, this::mapRowToGame, gameId);
        }catch (EmptyResultDataAccessException e){
            throw new JdbcOperationFailedException("Could not find specified game." + gameId + " is not a valid gameId.");
        }
    }

    @Override
    public List<Game> findGameByStudio(String studio) {
        return jdbcTemplate.query(SELECT_GAME_BY_STUDIO_SQL, this::mapRowToGame, studio);
    }

    @Override
    public List<Game> findGameByEsrbRating(String esrbRating) {
        return jdbcTemplate.query(SELECT_GAME_BY_RATING_SQL, this::mapRowToGame, esrbRating);
    }

    //I set this up to return a list, so later I can implement some sort of substring matching functionality
    //that would identify different editions of a title or even similar titles within a series.
    //First, I'll complete the capstone project, then I'll comeback and refactor this if I have time.
    @Override
    public List<Game> findGameByTitle(String title) {
        return jdbcTemplate.query(SELECT_GAME_BY_TITLE_SQL, this::mapRowToGame, title);
    }

    @Override
    public List<Game> findAllGames() {
        return jdbcTemplate.query(SELECT_ALL_GAME_SQL, this::mapRowToGame);
    }

    @Override
    public void updateGame(Game game) throws JdbcOperationFailedException{
        int rowsAffected = jdbcTemplate.update(UPDATE_GAME_SQL, game.getTitle(), game.getEsrbRating(), game.getDescription(),
                game.getPrice(), game.getStudio(), game.getQuantity(), game.getItemId());
        if (rowsAffected == 0){
            throw new JdbcOperationFailedException("Unable to update game: " + game.getTitle() + ". " + game.getItemId() + " is an invalid itemId");
        }
    }

    @Override
    public void deleteGame(Integer gameId) throws JdbcOperationFailedException{
        int rowsAffected = jdbcTemplate.update(DELETE_GAME_SQL, gameId);
        if (rowsAffected == 0){
            throw new JdbcOperationFailedException("Unable to update game." + gameId + " is an invalid itemId");
        }
    }

    private Game mapRowToGame(ResultSet rs, int rowNum) throws SQLException {
        Game g = new Game();
        g.setItemId(rs.getInt("game_id"));
        g.setTitle(rs.getString("title"));
        g.setEsrbRating(rs.getString("esrb_rating"));
        g.setDescription(rs.getString("description"));
        g.setPrice(rs.getBigDecimal("price"));
        g.setStudio(rs.getString("studio"));
        g.setQuantity(rs.getInt("quantity"));
        return g;
    }
}
