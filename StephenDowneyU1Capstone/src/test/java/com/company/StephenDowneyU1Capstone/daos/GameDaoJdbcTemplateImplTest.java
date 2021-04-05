package com.company.StephenDowneyU1Capstone.daos;

import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GameDaoJdbcTemplateImplTest {

    @Autowired
    GameDao gameDao;

    @Before
    public void setUp() throws Exception {
        List<Game> gameList = gameDao.findAllGames();
        for (Game g: gameList) {
            gameDao.deleteGame(g.getItemId());
        }
    }

    @Test
    public void shouldAddGetDeleteGame() throws JdbcOperationFailedException {
        //Arrange
        Game testGame1 = new Game();
        testGame1.setTitle("Skyrim");
        testGame1.setDescription("Epic installation in the Elder Scrolls series.");
        testGame1.setEsrbRating("T");
        testGame1.setStudio("Bethesda");
        testGame1.setPrice(new BigDecimal("69.99"));
        testGame1.setQuantity(6);

        //Act
        testGame1 = gameDao.saveGame(testGame1);
        Game gameReceivedBackFromDatabase = gameDao.findGame(testGame1.getItemId());
        List<Game> allGamesInDatabase = gameDao.findAllGames();

        //Assert
        assertEquals(1, allGamesInDatabase.size());
        assertEquals(testGame1, gameReceivedBackFromDatabase);

        //Act
        gameDao.deleteGame(testGame1.getItemId());
        allGamesInDatabase = gameDao.findAllGames();

        //Assert
        assertEquals(0, allGamesInDatabase.size());

    }

    @Test
    public void shouldFindGameByStudio() {
        //Arrange
        Game testGame1 = new Game();
        testGame1.setTitle("Skyrim");
        testGame1.setDescription("Epic installation in the Elder Scrolls series.");
        testGame1.setEsrbRating("T");
        testGame1.setStudio("Bethesda");
        testGame1.setPrice(new BigDecimal("69.99"));
        testGame1.setQuantity(6);

        Game testGame2 = new Game();
        testGame2.setTitle("Fallout 4");
        testGame2.setDescription("Epic installation in the Fallout series.");
        testGame2.setEsrbRating("M");
        testGame2.setStudio("Bethesda");
        testGame2.setPrice(new BigDecimal("69.99"));
        testGame2.setQuantity(11);

        Game testGame3 = new Game();
        testGame3.setTitle("Final Fantasy VII Remake");
        testGame3.setDescription("Epic remake of the classic Playstation 1 game.");
        testGame3.setEsrbRating("T");
        testGame3.setStudio("Square Enix");
        testGame3.setPrice(new BigDecimal("59.99"));
        testGame3.setQuantity(3);

        gameDao.saveGame(testGame1);
        gameDao.saveGame(testGame2);
        gameDao.saveGame(testGame3);

        //Act
        List<Game> bethesdaGames = gameDao.findGameByStudio("Bethesda");
        List<Game> squareEnixGames = gameDao.findGameByStudio("Square Enix");

        //Assert
        assertEquals(2, bethesdaGames.size());
        assertEquals("Bethesda", bethesdaGames.get(0).getStudio());
        assertEquals("Bethesda", bethesdaGames.get(1).getStudio());

        assertEquals(1, squareEnixGames.size());
        assertEquals("Square Enix", squareEnixGames.get(0).getStudio());

    }

    @Test
    public void shouldFindGameByErsbRating() {
        //Arrange
        Game testGame1 = new Game();
        testGame1.setTitle("Skyrim");
        testGame1.setDescription("Epic installation in the Elder Scrolls series.");
        testGame1.setEsrbRating("T");
        testGame1.setStudio("Bethesda");
        testGame1.setPrice(new BigDecimal("69.99"));
        testGame1.setQuantity(6);

        Game testGame2 = new Game();
        testGame2.setTitle("Fallout 4");
        testGame2.setDescription("Epic installation in the Fallout series.");
        testGame2.setEsrbRating("M");
        testGame2.setStudio("Bethesda");
        testGame2.setPrice(new BigDecimal("69.99"));
        testGame2.setQuantity(11);

        Game testGame3 = new Game();
        testGame3.setTitle("Final Fantasy VII Remake");
        testGame3.setDescription("Epic remake of the classic Playstation 1 game.");
        testGame3.setEsrbRating("T");
        testGame3.setStudio("Square Enix");
        testGame3.setPrice(new BigDecimal("59.99"));
        testGame3.setQuantity(3);

        testGame1 = gameDao.saveGame(testGame1);
        testGame2 = gameDao.saveGame(testGame2);
        testGame3 = gameDao.saveGame(testGame3);

        //Act
        List<Game> teenGames = gameDao.findGameByEsrbRating("T");
        List<Game> matureGames = gameDao.findGameByEsrbRating("M");

        //Assert
        assertEquals(2, teenGames.size());
        assertEquals("T", teenGames.get(0).getEsrbRating());
        assertEquals("T", teenGames.get(1).getEsrbRating());

        assertEquals(1, matureGames.size());
        assertEquals("M", matureGames.get(0).getEsrbRating());
    }

    @Test
    public void shouldFindGameByTitle() {
        //Arrange
        Game testGame1 = new Game();
        testGame1.setTitle("Skyrim");
        testGame1.setDescription("Epic installation in the Elder Scrolls series.");
        testGame1.setEsrbRating("T");
        testGame1.setStudio("Bethesda");
        testGame1.setPrice(new BigDecimal("69.99"));
        testGame1.setQuantity(6);

        Game testGame2 = new Game();
        testGame2.setTitle("Fallout 4");
        testGame2.setDescription("Epic installation in the Fallout series.");
        testGame2.setEsrbRating("M");
        testGame2.setStudio("Bethesda");
        testGame2.setPrice(new BigDecimal("69.99"));
        testGame2.setQuantity(11);

        Game testGame3 = new Game();
        testGame3.setTitle("Final Fantasy VII Remake");
        testGame3.setDescription("Epic remake of the classic Playstation 1 game.");
        testGame3.setEsrbRating("T");
        testGame3.setStudio("Square Enix");
        testGame3.setPrice(new BigDecimal("59.99"));
        testGame3.setQuantity(3);

        testGame1 = gameDao.saveGame(testGame1);
        testGame2 = gameDao.saveGame(testGame2);
        testGame3 = gameDao.saveGame(testGame3);

        //Act
        List<Game> skyrim = gameDao.findGameByTitle("Skyrim");
        List<Game> fallout = gameDao.findGameByTitle("Fallout 4");
        List<Game> finalFantasy = gameDao.findGameByTitle("Final Fantasy VII Remake");

        //Assert
        assertEquals(1, skyrim.size());
        assertEquals("Skyrim", skyrim.get(0).getTitle());

        assertEquals(1, fallout.size());
        assertEquals("Fallout 4", fallout.get(0).getTitle());

        assertEquals(1, finalFantasy.size());
        assertEquals("Final Fantasy VII Remake", finalFantasy.get(0).getTitle());
    }

    @Test
    public void shouldFindAllGames() throws JdbcOperationFailedException{
        //Arrange
        Game testGame1 = new Game();
        testGame1.setTitle("Skyrim");
        testGame1.setDescription("Epic installation in the Elder Scrolls series.");
        testGame1.setEsrbRating("T");
        testGame1.setStudio("Bethesda");
        testGame1.setPrice(new BigDecimal("69.99"));
        testGame1.setQuantity(6);

        Game testGame2 = new Game();
        testGame2.setTitle("Fallout 4");
        testGame2.setDescription("Epic installation in the Fallout series.");
        testGame2.setEsrbRating("M");
        testGame2.setStudio("Bethesda");
        testGame2.setPrice(new BigDecimal("69.99"));
        testGame2.setQuantity(11);

        Game testGame3 = new Game();
        testGame3.setTitle("Final Fantasy VII Remake");
        testGame3.setDescription("Epic remake of the classic Playstation 1 game.");
        testGame3.setEsrbRating("T");
        testGame3.setStudio("Square Enix");
        testGame3.setPrice(new BigDecimal("59.99"));
        testGame3.setQuantity(3);

        testGame1 = gameDao.saveGame(testGame1);

        //Act
        List<Game> allGamesInDatabase = gameDao.findAllGames();

        //Assert
        assertEquals(1, allGamesInDatabase.size());

        testGame2 = gameDao.saveGame(testGame2);

        //Act
        allGamesInDatabase = gameDao.findAllGames();

        //Assert
        assertEquals(2, allGamesInDatabase.size());

        testGame3 = gameDao.saveGame(testGame3);

        //Act
        allGamesInDatabase = gameDao.findAllGames();

        //Assert
        assertEquals(3, allGamesInDatabase.size());

        gameDao.deleteGame(testGame1.getItemId());
        gameDao.deleteGame(testGame3.getItemId());

        //Act
        allGamesInDatabase = gameDao.findAllGames();

        //Assert
        assertEquals(1, allGamesInDatabase.size());
        assertEquals(testGame2, allGamesInDatabase.get(0));

    }

    @Test
    public void shouldUpdateGame() throws JdbcOperationFailedException {
        //Arrange
        Game testGame1 = new Game();
        testGame1.setTitle("Skyrim");
        testGame1.setDescription("Epic installation in the Elder Scrolls series.");
        testGame1.setEsrbRating("T");
        testGame1.setStudio("Bethesda");
        testGame1.setPrice(new BigDecimal("69.99"));
        testGame1.setQuantity(6);

        testGame1 = gameDao.saveGame(testGame1);
        Game updatedTestGame = gameDao.findGame(testGame1.getItemId());
        updatedTestGame.setQuantity(5);
        updatedTestGame.setPrice(new BigDecimal("29.99"));
        updatedTestGame.setDescription("An oldie but a goody.");

        //Act
        gameDao.updateGame(updatedTestGame);

        Game gameReturnedFromDatabase = gameDao.findGame(testGame1.getItemId());

        //Assert
        assertEquals(updatedTestGame, gameReturnedFromDatabase);
        assertNotEquals(testGame1, gameReturnedFromDatabase);
    }

    @Test
    public void shouldDeleteGame() throws JdbcOperationFailedException{
        //Arrange
        Game testGame1 = new Game();
        testGame1.setTitle("Skyrim");
        testGame1.setDescription("Epic installation in the Elder Scrolls series.");
        testGame1.setEsrbRating("T");
        testGame1.setStudio("Bethesda");
        testGame1.setPrice(new BigDecimal("69.99"));
        testGame1.setQuantity(6);

        Game testGame2 = new Game();
        testGame2.setTitle("Fallout 4");
        testGame2.setDescription("Epic installation in the Fallout series.");
        testGame2.setEsrbRating("M");
        testGame2.setStudio("Bethesda");
        testGame2.setPrice(new BigDecimal("69.99"));
        testGame2.setQuantity(11);

        Game testGame3 = new Game();
        testGame3.setTitle("Final Fantasy VII Remake");
        testGame3.setDescription("Epic remake of the classic Playstation 1 game.");
        testGame3.setEsrbRating("T");
        testGame3.setStudio("Square Enix");
        testGame3.setPrice(new BigDecimal("59.99"));
        testGame3.setQuantity(3);

        testGame1 = gameDao.saveGame(testGame1);
        testGame2 = gameDao.saveGame(testGame2);
        testGame3 = gameDao.saveGame(testGame3);

        //the purpose of these two lines is to establish that their are 3 games in the database before deleting
        List<Game> allGamesInDatabase = gameDao.findAllGames();
        assertEquals(3, allGamesInDatabase.size());


        //Act
        gameDao.deleteGame(testGame2.getItemId());

        allGamesInDatabase = gameDao.findAllGames();

        //Assert
        assertEquals(2, allGamesInDatabase.size());

        //Act
        gameDao.deleteGame(testGame1.getItemId());

        allGamesInDatabase = gameDao.findAllGames();

        //Assert
        assertEquals(1, allGamesInDatabase.size());
        assertEquals(testGame3, allGamesInDatabase.get(0));

        //Act
        gameDao.deleteGame(testGame3.getItemId());

        allGamesInDatabase = gameDao.findAllGames();

        //Assert
        assertEquals(0, allGamesInDatabase.size());
    }

    @Test
    public void shouldThrowJdbcOperationFailedExceptionWhenAnInvalidIdIsPassedToDeleteOrFindByIdOrInvalidGameIsPassedToUpdate(){
        //Arrange
        Game testGame1 = new Game();
        testGame1.setTitle("Skyrim");
        testGame1.setDescription("Epic installation in the Elder Scrolls series.");
        testGame1.setEsrbRating("T");
        testGame1.setStudio("Bethesda");
        testGame1.setPrice(new BigDecimal("69.99"));
        testGame1.setQuantity(6);

        Game testGame2 = new Game();
        testGame2.setTitle("Fallout 4");
        testGame2.setDescription("Epic installation in the Fallout series.");
        testGame2.setEsrbRating("M");
        testGame2.setStudio("Bethesda");
        testGame2.setPrice(new BigDecimal("69.99"));
        testGame2.setQuantity(11);

        Game testGame3 = new Game();
        testGame3.setTitle("Final Fantasy VII Remake");
        testGame3.setDescription("Epic remake of the classic Playstation 1 game.");
        testGame3.setEsrbRating("T");
        testGame3.setStudio("Square Enix");
        testGame3.setPrice(new BigDecimal("59.99"));
        testGame3.setQuantity(3);

        testGame1 = gameDao.saveGame(testGame1);
        testGame2 = gameDao.saveGame(testGame2);

        assertThrows(JdbcOperationFailedException.class, () -> {
            gameDao.findGame(35);
        });

        assertThrows(JdbcOperationFailedException.class, () -> {
            gameDao.deleteGame(35);
        });

        assertThrows(JdbcOperationFailedException.class, () -> {
            gameDao.updateGame(testGame3);
        });
    }
}