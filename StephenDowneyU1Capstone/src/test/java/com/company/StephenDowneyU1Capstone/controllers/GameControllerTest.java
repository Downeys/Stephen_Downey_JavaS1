package com.company.StephenDowneyU1Capstone.controllers;

import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.Game;
import com.company.StephenDowneyU1Capstone.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceLayer service;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception{
        setupServiceLayerMock();
    }

    @Test
    public void shouldAddGameAndReturnStatusCreated() throws Exception {
        //Arrange
        Game inputGame1 = new Game();
        inputGame1.setTitle("Skyrim");
        inputGame1.setDescription("Epic installation in the Elder Scrolls series.");
        inputGame1.setEsrbRating("T");
        inputGame1.setStudio("Bethesda");
        inputGame1.setPrice(new BigDecimal("69.99"));
        inputGame1.setQuantity(6);

        Game inputGame2 = new Game();
        inputGame2.setTitle("Fallout 4");
        inputGame2.setDescription("Epic installation in the Fallout series.");
        inputGame2.setEsrbRating("M");
        inputGame2.setStudio("Bethesda");
        inputGame2.setPrice(new BigDecimal("69.99"));
        inputGame2.setQuantity(11);

        Game inputGame3 = new Game();
        inputGame3.setTitle("Final Fantasy VII Remake");
        inputGame3.setDescription("Epic remake of the classic Playstation 1 game.");
        inputGame3.setEsrbRating("T");
        inputGame3.setStudio("Square Enix");
        inputGame3.setPrice(new BigDecimal("59.99"));
        inputGame3.setQuantity(3);

        Game outputGame1 = new Game();
        outputGame1.setItemId(1);
        outputGame1.setTitle("Skyrim");
        outputGame1.setDescription("Epic installation in the Elder Scrolls series.");
        outputGame1.setEsrbRating("T");
        outputGame1.setStudio("Bethesda");
        outputGame1.setPrice(new BigDecimal("69.99"));
        outputGame1.setQuantity(6);

        Game outputGame2 = new Game();
        outputGame2.setItemId(2);
        outputGame2.setTitle("Fallout 4");
        outputGame2.setDescription("Epic installation in the Fallout series.");
        outputGame2.setEsrbRating("M");
        outputGame2.setStudio("Bethesda");
        outputGame2.setPrice(new BigDecimal("69.99"));
        outputGame2.setQuantity(11);

        Game outputGame3 = new Game();
        outputGame3.setItemId(3);
        outputGame3.setTitle("Final Fantasy VII Remake");
        outputGame3.setDescription("Epic remake of the classic Playstation 1 game.");
        outputGame3.setEsrbRating("T");
        outputGame3.setStudio("Square Enix");
        outputGame3.setPrice(new BigDecimal("59.99"));
        outputGame3.setQuantity(3);

        String inputJsonStringForTest1 = mapper.writeValueAsString(inputGame1);
        String inputJsonStringForTest2 = mapper.writeValueAsString(inputGame2);
        String inputJsonStringForTest3 = mapper.writeValueAsString(inputGame3);

        String expectedOutPutStringFromTest1 = mapper.writeValueAsString(outputGame1);
        String expectedOutPutStringFromTest2 = mapper.writeValueAsString(outputGame2);
        String expectedOutPutStringFromTest3 = mapper.writeValueAsString(outputGame3);

        //Act and Assert
        mockMvc.perform(
                post("/game")
                        .content(inputJsonStringForTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedOutPutStringFromTest1));

        mockMvc.perform(
                post("/game")
                        .content(inputJsonStringForTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedOutPutStringFromTest2));

        mockMvc.perform(
                post("/game")
                        .content(inputJsonStringForTest3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedOutPutStringFromTest3));
    }

    @Test
    public void shouldGetGameByIdAndReturnStatusOk() throws Exception {
        //Arrange
        String test1 = "/game/1";
        String test2 = "/game/2";
        String test3 = "/game/3";

        Game outputGame1 = new Game();
        outputGame1.setItemId(1);
        outputGame1.setTitle("Skyrim");
        outputGame1.setDescription("Epic installation in the Elder Scrolls series.");
        outputGame1.setEsrbRating("T");
        outputGame1.setStudio("Bethesda");
        outputGame1.setPrice(new BigDecimal("69.99"));
        outputGame1.setQuantity(6);

        Game outputGame2 = new Game();
        outputGame2.setItemId(2);
        outputGame2.setTitle("Fallout 4");
        outputGame2.setDescription("Epic installation in the Fallout series.");
        outputGame2.setEsrbRating("M");
        outputGame2.setStudio("Bethesda");
        outputGame2.setPrice(new BigDecimal("69.99"));
        outputGame2.setQuantity(11);

        Game outputGame3 = new Game();
        outputGame3.setItemId(3);
        outputGame3.setTitle("Final Fantasy VII Remake");
        outputGame3.setDescription("Epic remake of the classic Playstation 1 game.");
        outputGame3.setEsrbRating("T");
        outputGame3.setStudio("Square Enix");
        outputGame3.setPrice(new BigDecimal("59.99"));
        outputGame3.setQuantity(3);

        String expectedOutputForTest1 = mapper.writeValueAsString(outputGame1);
        String expectedOutputForTest2 = mapper.writeValueAsString(outputGame2);
        String expectedOutputForTest3 = mapper.writeValueAsString(outputGame3);

        //Act and Assert
        mockMvc.perform(
                get(test1)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputForTest1));

        mockMvc.perform(
                get(test2)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputForTest2));

        mockMvc.perform(
                get(test3)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputForTest3));

    }

    @Test
    public void shouldReturnStatusNotFoundWhenPassedInvalidIdToUpdateDeleteOrGetByIdMethods() throws Exception{
        //Arrange
        String test1 = "/game/4";
        String test2 = "/game/6";

        Game invalidUpdateGame = new Game();
        invalidUpdateGame.setItemId(5);
        invalidUpdateGame.setTitle("Final Fantasy VII Remake");
        invalidUpdateGame.setDescription("Epic remake of the classic Playstation 1 game.");
        invalidUpdateGame.setEsrbRating("T");
        invalidUpdateGame.setStudio("Square Enix");
        invalidUpdateGame.setPrice(new BigDecimal("59.99"));
        invalidUpdateGame.setQuantity(3);

        String test3 = mapper.writeValueAsString(invalidUpdateGame);

        //Act and Assert
        mockMvc.perform(
                get(test1)
        )
                .andDo(print())
                .andExpect(status().isNotFound());

        mockMvc.perform(
                delete(test2)
        )
                .andDo(print())
                .andExpect(status().isNotFound());

        mockMvc.perform(
                put("/game")
                        .content(test3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    public void shouldGetGameByStudioAndReturnStatusOk() throws Exception{
        //Arrange
        String test1 = "/game?studio=Bethesda";
        String test2 = "/game?studio=Square Enix";

        Game outputGame1 = new Game();
        outputGame1.setItemId(1);
        outputGame1.setTitle("Skyrim");
        outputGame1.setDescription("Epic installation in the Elder Scrolls series.");
        outputGame1.setEsrbRating("T");
        outputGame1.setStudio("Bethesda");
        outputGame1.setPrice(new BigDecimal("69.99"));
        outputGame1.setQuantity(6);

        Game outputGame2 = new Game();
        outputGame2.setItemId(2);
        outputGame2.setTitle("Fallout 4");
        outputGame2.setDescription("Epic installation in the Fallout series.");
        outputGame2.setEsrbRating("M");
        outputGame2.setStudio("Bethesda");
        outputGame2.setPrice(new BigDecimal("69.99"));
        outputGame2.setQuantity(11);

        Game outputGame3 = new Game();
        outputGame3.setItemId(3);
        outputGame3.setTitle("Final Fantasy VII Remake");
        outputGame3.setDescription("Epic remake of the classic Playstation 1 game.");
        outputGame3.setEsrbRating("T");
        outputGame3.setStudio("Square Enix");
        outputGame3.setPrice(new BigDecimal("59.99"));
        outputGame3.setQuantity(3);

        List<Game> bethesdaGames = new ArrayList<>();
        bethesdaGames.add(outputGame1);
        bethesdaGames.add(outputGame2);

        List<Game> squareEnixGames = new ArrayList<>();
        squareEnixGames.add(outputGame3);

        String expectedOutputForTest1 = mapper.writeValueAsString(bethesdaGames);
        String expectedOutputForTest2 = mapper.writeValueAsString(squareEnixGames);

        //Act and Assert
        mockMvc.perform(
                get(test1)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputForTest1));

        mockMvc.perform(
                get(test2)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputForTest2));
    }

    @Test
    public void shouldGetGameByEsrbRatingAndReturnStatusOk() throws Exception{
        //Arrange
        String test1 = "/game?rating=T";
        String test2 = "/game?rating=M";

        Game outputGame1 = new Game();
        outputGame1.setItemId(1);
        outputGame1.setTitle("Skyrim");
        outputGame1.setDescription("Epic installation in the Elder Scrolls series.");
        outputGame1.setEsrbRating("T");
        outputGame1.setStudio("Bethesda");
        outputGame1.setPrice(new BigDecimal("69.99"));
        outputGame1.setQuantity(6);

        Game outputGame2 = new Game();
        outputGame2.setItemId(2);
        outputGame2.setTitle("Fallout 4");
        outputGame2.setDescription("Epic installation in the Fallout series.");
        outputGame2.setEsrbRating("M");
        outputGame2.setStudio("Bethesda");
        outputGame2.setPrice(new BigDecimal("69.99"));
        outputGame2.setQuantity(11);

        Game outputGame3 = new Game();
        outputGame3.setItemId(3);
        outputGame3.setTitle("Final Fantasy VII Remake");
        outputGame3.setDescription("Epic remake of the classic Playstation 1 game.");
        outputGame3.setEsrbRating("T");
        outputGame3.setStudio("Square Enix");
        outputGame3.setPrice(new BigDecimal("59.99"));
        outputGame3.setQuantity(3);

        List<Game> tRatedGames = new ArrayList<>();
        tRatedGames.add(outputGame1);
        tRatedGames.add(outputGame3);

        List<Game> mRatedGames = new ArrayList<>();
        mRatedGames.add(outputGame2);

        String expectedOutputForTest1 = mapper.writeValueAsString(tRatedGames);
        String expectedOutputForTest2 = mapper.writeValueAsString(mRatedGames);

        //Act and Assert
        mockMvc.perform(
                get(test1)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputForTest1));

        mockMvc.perform(
                get(test2)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputForTest2));
    }

    @Test
    public void shouldGetGameByTitleAndReturnStatusOk() throws Exception{
        //Arrange
        String test1 = "/game?title=Skyrim";
        String test2 = "/game?title=Fallout 4";
        String test3 = "/game?title=Final Fantasy VII Remake";

        Game outputGame1 = new Game();
        outputGame1.setItemId(1);
        outputGame1.setTitle("Skyrim");
        outputGame1.setDescription("Epic installation in the Elder Scrolls series.");
        outputGame1.setEsrbRating("T");
        outputGame1.setStudio("Bethesda");
        outputGame1.setPrice(new BigDecimal("69.99"));
        outputGame1.setQuantity(6);

        Game outputGame2 = new Game();
        outputGame2.setItemId(2);
        outputGame2.setTitle("Fallout 4");
        outputGame2.setDescription("Epic installation in the Fallout series.");
        outputGame2.setEsrbRating("M");
        outputGame2.setStudio("Bethesda");
        outputGame2.setPrice(new BigDecimal("69.99"));
        outputGame2.setQuantity(11);

        Game outputGame3 = new Game();
        outputGame3.setItemId(3);
        outputGame3.setTitle("Final Fantasy VII Remake");
        outputGame3.setDescription("Epic remake of the classic Playstation 1 game.");
        outputGame3.setEsrbRating("T");
        outputGame3.setStudio("Square Enix");
        outputGame3.setPrice(new BigDecimal("59.99"));
        outputGame3.setQuantity(3);

        List<Game> skyrimList = new ArrayList<>();
        skyrimList.add(outputGame1);

        List<Game> falloutList = new ArrayList<>();
        falloutList.add(outputGame2);

        List<Game> ffviiList = new ArrayList<>();
        ffviiList.add(outputGame3);

        String expectedOutputForTest1 = mapper.writeValueAsString(skyrimList);
        String expectedOutputForTest2 = mapper.writeValueAsString(falloutList);
        String expectedOutputForTest3 = mapper.writeValueAsString(ffviiList);

        //Act and Assert
        mockMvc.perform(
                get(test1)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputForTest1));

        mockMvc.perform(
                get(test2)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputForTest2));

        mockMvc.perform(
                get(test3)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputForTest3));
    }

    @Test
    public void shouldGetAllGamesAndReturnStatusOk() throws Exception{
        //Arrange
        String testUri = "/game";

        Game outputGame1 = new Game();
        outputGame1.setItemId(1);
        outputGame1.setTitle("Skyrim");
        outputGame1.setDescription("Epic installation in the Elder Scrolls series.");
        outputGame1.setEsrbRating("T");
        outputGame1.setStudio("Bethesda");
        outputGame1.setPrice(new BigDecimal("69.99"));
        outputGame1.setQuantity(6);

        Game outputGame2 = new Game();
        outputGame2.setItemId(2);
        outputGame2.setTitle("Fallout 4");
        outputGame2.setDescription("Epic installation in the Fallout series.");
        outputGame2.setEsrbRating("M");
        outputGame2.setStudio("Bethesda");
        outputGame2.setPrice(new BigDecimal("69.99"));
        outputGame2.setQuantity(11);

        Game outputGame3 = new Game();
        outputGame3.setItemId(3);
        outputGame3.setTitle("Final Fantasy VII Remake");
        outputGame3.setDescription("Epic remake of the classic Playstation 1 game.");
        outputGame3.setEsrbRating("T");
        outputGame3.setStudio("Square Enix");
        outputGame3.setPrice(new BigDecimal("59.99"));
        outputGame3.setQuantity(3);

        List<Game> gameList = new ArrayList<>();
        gameList.add(outputGame1);
        gameList.add(outputGame2);
        gameList.add(outputGame3);

        String expectedOutputForTest = mapper.writeValueAsString(gameList);

        //Act and Assert
        mockMvc.perform(
                get(testUri)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputForTest));

    }

    @Test
    public void shouldReturnStatusOkWhenSentValidGame() throws Exception{
        //Arrange
        Game inputGame1 = new Game();
        inputGame1.setTitle("Skyrim");
        inputGame1.setDescription("Epic installation in the Elder Scrolls series.");
        inputGame1.setEsrbRating("T");
        inputGame1.setStudio("Bethesda");
        inputGame1.setPrice(new BigDecimal("69.99"));
        inputGame1.setQuantity(6);

        Game inputGame2 = new Game();
        inputGame2.setTitle("Fallout 4");
        inputGame2.setDescription("Epic installation in the Fallout series.");
        inputGame2.setEsrbRating("M");
        inputGame2.setStudio("Bethesda");
        inputGame2.setPrice(new BigDecimal("69.99"));
        inputGame2.setQuantity(11);

        Game inputGame3 = new Game();
        inputGame3.setTitle("Final Fantasy VII Remake");
        inputGame3.setDescription("Epic remake of the classic Playstation 1 game.");
        inputGame3.setEsrbRating("T");
        inputGame3.setStudio("Square Enix");
        inputGame3.setPrice(new BigDecimal("59.99"));
        inputGame3.setQuantity(3);

        String inputJsonStringForTest1 = mapper.writeValueAsString(inputGame1);
        String inputJsonStringForTest2 = mapper.writeValueAsString(inputGame2);
        String inputJsonStringForTest3 = mapper.writeValueAsString(inputGame3);

        //Act and Assert
        mockMvc.perform(
                put("/game")
                        .content(inputJsonStringForTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(
                put("/game")
                        .content(inputJsonStringForTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(
                put("/game")
                        .content(inputJsonStringForTest3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStatusUnprocessableEntityWhenSentInValidGame() throws Exception{
        //Arrange
        Game inputGame1 = new Game();
        inputGame1.setDescription("Epic installation in the Elder Scrolls series.");
        inputGame1.setEsrbRating("T");
        inputGame1.setStudio("Bethesda");
        inputGame1.setPrice(new BigDecimal("69.99"));
        inputGame1.setQuantity(6);

        Game inputGame2 = new Game();
        inputGame2.setTitle("Fallout 4");
        inputGame2.setEsrbRating("M");
        inputGame2.setStudio("Bethesda");
        inputGame2.setPrice(new BigDecimal("69.99"));
        inputGame2.setQuantity(11);

        Game inputGame3 = new Game();
        inputGame3.setTitle("Final Fantasy VII Remake");
        inputGame3.setDescription("Epic remake of the classic Playstation 1 game.");
        inputGame3.setStudio("Square Enix");
        inputGame3.setPrice(new BigDecimal("59.99"));
        inputGame3.setQuantity(3);

        Game inputGame4 = new Game();
        inputGame4.setTitle("Skyrim");
        inputGame4.setDescription("Epic installation in the Elder Scrolls series.");
        inputGame4.setEsrbRating("T");
        inputGame4.setPrice(new BigDecimal("69.99"));
        inputGame4.setQuantity(6);

        Game inputGame5 = new Game();
        inputGame5.setTitle("Fallout 4");
        inputGame5.setDescription("Epic installation in the Fallout series.");
        inputGame5.setEsrbRating("M");
        inputGame5.setStudio("Bethesda");
        inputGame5.setPrice(new BigDecimal("-69.99"));
        inputGame5.setQuantity(11);

        Game inputGame6 = new Game();
        inputGame6.setTitle("Final Fantasy VII Remake");
        inputGame6.setDescription("Epic remake of the classic Playstation 1 game.");
        inputGame6.setEsrbRating("T");
        inputGame6.setStudio("Square Enix");
        inputGame6.setPrice(new BigDecimal("59.99"));
        inputGame6.setQuantity(new Integer("-5"));

        String inputJsonStringForTest1 = mapper.writeValueAsString(inputGame1);
        String inputJsonStringForTest2 = mapper.writeValueAsString(inputGame2);
        String inputJsonStringForTest3 = mapper.writeValueAsString(inputGame3);
        String inputJsonStringForTest4 = mapper.writeValueAsString(inputGame4);
        String inputJsonStringForTest5 = mapper.writeValueAsString(inputGame5);
        String inputJsonStringForTest6 = mapper.writeValueAsString(inputGame6);

        //Act and Assert
        mockMvc.perform(
                put("/game")
                        .content(inputJsonStringForTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                put("/game")
                        .content(inputJsonStringForTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                put("/game")
                        .content(inputJsonStringForTest3)
                        .contentType(MediaType.APPLICATION_JSON)


        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                put("/game")
                        .content(inputJsonStringForTest4)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                put("/game")
                        .content(inputJsonStringForTest5)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                put("/game")
                        .content(inputJsonStringForTest6)
                        .contentType(MediaType.APPLICATION_JSON)


        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturnStatusNoContentWhenPassedAValidIndexToDelete() throws Exception{
        //Arrange
        String test1 = "/game/1";
        String test2 = "/game/2";
        String test3 = "/game/3";

        //Act and Assert
        mockMvc.perform(
                delete(test1)
        )
                .andDo(print())
                .andExpect(status().isNoContent());

        mockMvc.perform(
                delete(test2)
        )
                .andDo(print())
                .andExpect(status().isNoContent());

        mockMvc.perform(
                delete(test3)
        )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    private void setupServiceLayerMock() throws JdbcOperationFailedException {
        Game inputGame1 = new Game();
        inputGame1.setTitle("Skyrim");
        inputGame1.setDescription("Epic installation in the Elder Scrolls series.");
        inputGame1.setEsrbRating("T");
        inputGame1.setStudio("Bethesda");
        inputGame1.setPrice(new BigDecimal("69.99"));
        inputGame1.setQuantity(6);

        Game inputGame2 = new Game();
        inputGame2.setTitle("Fallout 4");
        inputGame2.setDescription("Epic installation in the Fallout series.");
        inputGame2.setEsrbRating("M");
        inputGame2.setStudio("Bethesda");
        inputGame2.setPrice(new BigDecimal("69.99"));
        inputGame2.setQuantity(11);

        Game inputGame3 = new Game();
        inputGame3.setTitle("Final Fantasy VII Remake");
        inputGame3.setDescription("Epic remake of the classic Playstation 1 game.");
        inputGame3.setEsrbRating("T");
        inputGame3.setStudio("Square Enix");
        inputGame3.setPrice(new BigDecimal("59.99"));
        inputGame3.setQuantity(3);

        Game invalidUpdateGame = new Game();
        invalidUpdateGame.setItemId(5);
        invalidUpdateGame.setTitle("Final Fantasy VII Remake");
        invalidUpdateGame.setDescription("Epic remake of the classic Playstation 1 game.");
        invalidUpdateGame.setEsrbRating("T");
        invalidUpdateGame.setStudio("Square Enix");
        invalidUpdateGame.setPrice(new BigDecimal("59.99"));
        invalidUpdateGame.setQuantity(3);

        Game outputGame1 = new Game();
        outputGame1.setItemId(1);
        outputGame1.setTitle("Skyrim");
        outputGame1.setDescription("Epic installation in the Elder Scrolls series.");
        outputGame1.setEsrbRating("T");
        outputGame1.setStudio("Bethesda");
        outputGame1.setPrice(new BigDecimal("69.99"));
        outputGame1.setQuantity(6);

        Game outputGame2 = new Game();
        outputGame2.setItemId(2);
        outputGame2.setTitle("Fallout 4");
        outputGame2.setDescription("Epic installation in the Fallout series.");
        outputGame2.setEsrbRating("M");
        outputGame2.setStudio("Bethesda");
        outputGame2.setPrice(new BigDecimal("69.99"));
        outputGame2.setQuantity(11);

        Game outputGame3 = new Game();
        outputGame3.setItemId(3);
        outputGame3.setTitle("Final Fantasy VII Remake");
        outputGame3.setDescription("Epic remake of the classic Playstation 1 game.");
        outputGame3.setEsrbRating("T");
        outputGame3.setStudio("Square Enix");
        outputGame3.setPrice(new BigDecimal("59.99"));
        outputGame3.setQuantity(3);

        List<Game> allOutputGames = new ArrayList<>();
        allOutputGames.add(outputGame1);
        allOutputGames.add(outputGame2);
        allOutputGames.add(outputGame3);

        List<Game> bethesdaGames = new ArrayList<>();
        bethesdaGames.add(outputGame1);
        bethesdaGames.add(outputGame2);

        List<Game> squareEnixGames = new ArrayList<>();
        squareEnixGames.add(outputGame3);

        List<Game> mRatedGames = new ArrayList<>();
        mRatedGames.add(outputGame2);

        List<Game> tRatedGames = new ArrayList<>();
        tRatedGames.add(outputGame1);
        tRatedGames.add(outputGame3);

        List<Game> skyrimList = new ArrayList<>();
        skyrimList.add(outputGame1);

        List<Game> falloutList = new ArrayList<>();
        falloutList.add(outputGame2);

        List<Game> ffviiList = new ArrayList<>();
        ffviiList.add(outputGame3);

        doReturn(outputGame1).when(service).addGame(inputGame1);
        doReturn(outputGame2).when(service).addGame(inputGame2);
        doReturn(outputGame3).when(service).addGame(inputGame3);
        doReturn(outputGame1).when(service).getGame(1);
        doReturn(outputGame2).when(service).getGame(2);
        doReturn(outputGame3).when(service).getGame(3);
        doThrow(JdbcOperationFailedException.class).when(service).getGame(4);
        doThrow(JdbcOperationFailedException.class).when(service).updateGame(invalidUpdateGame);
        doThrow(JdbcOperationFailedException.class).when(service).deleteGame(6);
        doReturn(allOutputGames).when(service).getAllGames();
        doReturn(bethesdaGames).when(service).getGameByStudio("Bethesda");
        doReturn(squareEnixGames).when(service).getGameByStudio("Square Enix");
        doReturn(tRatedGames).when(service).getGameByEsrbRating("T");
        doReturn(mRatedGames).when(service).getGameByEsrbRating("M");
        doReturn(skyrimList).when(service).getGameByTitle("Skyrim");
        doReturn(falloutList).when(service).getGameByTitle("Fallout 4");
        doReturn(ffviiList).when(service).getGameByTitle("Final Fantasy VII Remake");
    }

}