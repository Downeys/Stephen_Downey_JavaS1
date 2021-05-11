package com.company.StephenDowneyU1Capstone.service;

import com.company.StephenDowneyU1Capstone.daos.*;
import com.company.StephenDowneyU1Capstone.exceptions.OutOfStockException;
import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.*;
import com.company.StephenDowneyU1Capstone.viewModels.InvoiceViewModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;

public class ServiceLayerTest {

    private ServiceLayer service;
    private ConsoleDao consoleDao;
    private GameDao gameDao;
    private TShirtDao tShirtDao;
    private InvoiceDao invoiceDao;
    private SalesTaxDao salesTaxDao;
    private ProcessingFeeDao processingFeeDao;

    @Before
    public void setUp() throws Exception{
        setupMockConsoleDao();
        setupMockGameDao();
        setupMockTShirtDao();
        setupMockInvoiceDao();
        setupMockSalesTaxDao();
        setupMockProcessingFeeDao();

        service = new ServiceLayer(consoleDao, gameDao, invoiceDao, processingFeeDao, salesTaxDao, tShirtDao);
    }

    //Tests associated with /game endpoints
    @Test
    public void shouldSaveGame(){
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

        Game expectedOutputGame1 = new Game();
        expectedOutputGame1.setItemId(1);
        expectedOutputGame1.setTitle("Skyrim");
        expectedOutputGame1.setDescription("Epic installation in the Elder Scrolls series.");
        expectedOutputGame1.setEsrbRating("T");
        expectedOutputGame1.setStudio("Bethesda");
        expectedOutputGame1.setPrice(new BigDecimal("69.99"));
        expectedOutputGame1.setQuantity(6);

        Game expectedOutputGame2 = new Game();
        expectedOutputGame2.setItemId(2);
        expectedOutputGame2.setTitle("Fallout 4");
        expectedOutputGame2.setDescription("Epic installation in the Fallout series.");
        expectedOutputGame2.setEsrbRating("M");
        expectedOutputGame2.setStudio("Bethesda");
        expectedOutputGame2.setPrice(new BigDecimal("69.99"));
        expectedOutputGame2.setQuantity(11);

        Game expectedOutputGame3 = new Game();
        expectedOutputGame3.setItemId(3);
        expectedOutputGame3.setTitle("Final Fantasy VII Remake");
        expectedOutputGame3.setDescription("Epic remake of the classic Playstation 1 game.");
        expectedOutputGame3.setEsrbRating("T");
        expectedOutputGame3.setStudio("Square Enix");
        expectedOutputGame3.setPrice(new BigDecimal("59.99"));
        expectedOutputGame3.setQuantity(3);

        //Act
        Game actualGameOutput1 = service.addGame(inputGame1);
        Game actualGameOutput2 = service.addGame(inputGame2);
        Game actualGameOutput3 = service.addGame(inputGame3);

        //Assert
        assertEquals(expectedOutputGame1, actualGameOutput1);
        assertEquals(expectedOutputGame2, actualGameOutput2);
        assertEquals(expectedOutputGame3, actualGameOutput3);
    }

    @Test
    public void shouldFindGameById() throws JdbcOperationFailedException {
        //Arrange
        Game expectedOutputGame1 = new Game();
        expectedOutputGame1.setItemId(1);
        expectedOutputGame1.setTitle("Skyrim");
        expectedOutputGame1.setDescription("Epic installation in the Elder Scrolls series.");
        expectedOutputGame1.setEsrbRating("T");
        expectedOutputGame1.setStudio("Bethesda");
        expectedOutputGame1.setPrice(new BigDecimal("69.99"));
        expectedOutputGame1.setQuantity(6);

        Game expectedOutputGame2 = new Game();
        expectedOutputGame2.setItemId(2);
        expectedOutputGame2.setTitle("Fallout 4");
        expectedOutputGame2.setDescription("Epic installation in the Fallout series.");
        expectedOutputGame2.setEsrbRating("M");
        expectedOutputGame2.setStudio("Bethesda");
        expectedOutputGame2.setPrice(new BigDecimal("69.99"));
        expectedOutputGame2.setQuantity(11);

        Game expectedOutputGame3 = new Game();
        expectedOutputGame3.setItemId(3);
        expectedOutputGame3.setTitle("Final Fantasy VII Remake");
        expectedOutputGame3.setDescription("Epic remake of the classic Playstation 1 game.");
        expectedOutputGame3.setEsrbRating("T");
        expectedOutputGame3.setStudio("Square Enix");
        expectedOutputGame3.setPrice(new BigDecimal("59.99"));
        expectedOutputGame3.setQuantity(3);

        //Act
        Game actualOutputGame1 = service.getGame(1);
        Game actualOutputGame2 = service.getGame(2);
        Game actualOutputGame3 = service.getGame(3);

        //Asssert
        assertEquals(expectedOutputGame1, actualOutputGame1);
        assertEquals(expectedOutputGame2, actualOutputGame2);
        assertEquals(expectedOutputGame3, actualOutputGame3);
    }

    @Test
    public void shouldFindGamesByStudio() throws JdbcOperationFailedException {
        //Arrange
        Game expectedOutputGame1 = new Game();
        expectedOutputGame1.setItemId(1);
        expectedOutputGame1.setTitle("Skyrim");
        expectedOutputGame1.setDescription("Epic installation in the Elder Scrolls series.");
        expectedOutputGame1.setEsrbRating("T");
        expectedOutputGame1.setStudio("Bethesda");
        expectedOutputGame1.setPrice(new BigDecimal("69.99"));
        expectedOutputGame1.setQuantity(6);

        Game expectedOutputGame2 = new Game();
        expectedOutputGame2.setItemId(2);
        expectedOutputGame2.setTitle("Fallout 4");
        expectedOutputGame2.setDescription("Epic installation in the Fallout series.");
        expectedOutputGame2.setEsrbRating("M");
        expectedOutputGame2.setStudio("Bethesda");
        expectedOutputGame2.setPrice(new BigDecimal("69.99"));
        expectedOutputGame2.setQuantity(11);

        Game expectedOutputGame3 = new Game();
        expectedOutputGame3.setItemId(3);
        expectedOutputGame3.setTitle("Final Fantasy VII Remake");
        expectedOutputGame3.setDescription("Epic remake of the classic Playstation 1 game.");
        expectedOutputGame3.setEsrbRating("T");
        expectedOutputGame3.setStudio("Square Enix");
        expectedOutputGame3.setPrice(new BigDecimal("59.99"));
        expectedOutputGame3.setQuantity(3);

        List<Game> expectedBethesdaGames = new ArrayList<>();
        expectedBethesdaGames.add(expectedOutputGame1);
        expectedBethesdaGames.add(expectedOutputGame2);

        List<Game> expectedSquareEnixGames = new ArrayList<>();
        expectedSquareEnixGames.add(expectedOutputGame3);

        //Act
        List<Game> actualBethesdaGames = service.getGameByStudio("Bethesda");
        List<Game> actualSquareEnixGames = service.getGameByStudio("Square Enix");

        //Assert
        assertEquals(expectedBethesdaGames, actualBethesdaGames);
        assertEquals(expectedSquareEnixGames, actualSquareEnixGames);

    }

    @Test
    public void shouldFindGamesByEsrbRating(){
        //Arrange
        Game expectedOutputGame1 = new Game();
        expectedOutputGame1.setItemId(1);
        expectedOutputGame1.setTitle("Skyrim");
        expectedOutputGame1.setDescription("Epic installation in the Elder Scrolls series.");
        expectedOutputGame1.setEsrbRating("T");
        expectedOutputGame1.setStudio("Bethesda");
        expectedOutputGame1.setPrice(new BigDecimal("69.99"));
        expectedOutputGame1.setQuantity(6);

        Game expectedOutputGame2 = new Game();
        expectedOutputGame2.setItemId(2);
        expectedOutputGame2.setTitle("Fallout 4");
        expectedOutputGame2.setDescription("Epic installation in the Fallout series.");
        expectedOutputGame2.setEsrbRating("M");
        expectedOutputGame2.setStudio("Bethesda");
        expectedOutputGame2.setPrice(new BigDecimal("69.99"));
        expectedOutputGame2.setQuantity(11);

        Game expectedOutputGame3 = new Game();
        expectedOutputGame3.setItemId(3);
        expectedOutputGame3.setTitle("Final Fantasy VII Remake");
        expectedOutputGame3.setDescription("Epic remake of the classic Playstation 1 game.");
        expectedOutputGame3.setEsrbRating("T");
        expectedOutputGame3.setStudio("Square Enix");
        expectedOutputGame3.setPrice(new BigDecimal("59.99"));
        expectedOutputGame3.setQuantity(3);

        List<Game> expectedTRatedGames = new ArrayList<>();
        expectedTRatedGames.add(expectedOutputGame1);
        expectedTRatedGames.add(expectedOutputGame3);

        List<Game> expectedMRatedGames = new ArrayList<>();
        expectedMRatedGames.add(expectedOutputGame2);

        //Act
        List<Game> actualTRatedGames = service.getGameByEsrbRating("T");
        List<Game> actualMRatedGames = service.getGameByEsrbRating("M");

        //Assert
        assertEquals(expectedTRatedGames, actualTRatedGames);
        assertEquals(expectedMRatedGames, actualMRatedGames);
    }

    @Test
    public void shouldFindGamesByTitle(){
        //Arrange
        Game expectedOutputGame1 = new Game();
        expectedOutputGame1.setItemId(1);
        expectedOutputGame1.setTitle("Skyrim");
        expectedOutputGame1.setDescription("Epic installation in the Elder Scrolls series.");
        expectedOutputGame1.setEsrbRating("T");
        expectedOutputGame1.setStudio("Bethesda");
        expectedOutputGame1.setPrice(new BigDecimal("69.99"));
        expectedOutputGame1.setQuantity(6);

        Game expectedOutputGame2 = new Game();
        expectedOutputGame2.setItemId(2);
        expectedOutputGame2.setTitle("Fallout 4");
        expectedOutputGame2.setDescription("Epic installation in the Fallout series.");
        expectedOutputGame2.setEsrbRating("M");
        expectedOutputGame2.setStudio("Bethesda");
        expectedOutputGame2.setPrice(new BigDecimal("69.99"));
        expectedOutputGame2.setQuantity(11);

        Game expectedOutputGame3 = new Game();
        expectedOutputGame3.setItemId(3);
        expectedOutputGame3.setTitle("Final Fantasy VII Remake");
        expectedOutputGame3.setDescription("Epic remake of the classic Playstation 1 game.");
        expectedOutputGame3.setEsrbRating("T");
        expectedOutputGame3.setStudio("Square Enix");
        expectedOutputGame3.setPrice(new BigDecimal("59.99"));
        expectedOutputGame3.setQuantity(3);

        List<Game> expectedSkyrimGames = new ArrayList<>();
        expectedSkyrimGames.add(expectedOutputGame1);

        List<Game> expectedFalloutGames = new ArrayList<>();
        expectedFalloutGames.add(expectedOutputGame2);

        List<Game> expectedFFVIIGames = new ArrayList<>();
        expectedFFVIIGames.add(expectedOutputGame3);

        //Act
        List<Game> actualSkyrimGames = service.getGameByTitle("Skyrim");
        List<Game> actualFalloutGames = service.getGameByTitle("Fallout 4");
        List<Game> acutalFFVIIGames = service.getGameByTitle("Final Fantasy VII Remake");

        //Assert
        assertEquals(expectedSkyrimGames, actualSkyrimGames);
        assertEquals(expectedFalloutGames, actualFalloutGames);
        assertEquals(expectedFFVIIGames, acutalFFVIIGames);

    }

    @Test
    public void shouldFindAllGames(){
        //Arrange
        Game expectedOutputGame1 = new Game();
        expectedOutputGame1.setItemId(1);
        expectedOutputGame1.setTitle("Skyrim");
        expectedOutputGame1.setDescription("Epic installation in the Elder Scrolls series.");
        expectedOutputGame1.setEsrbRating("T");
        expectedOutputGame1.setStudio("Bethesda");
        expectedOutputGame1.setPrice(new BigDecimal("69.99"));
        expectedOutputGame1.setQuantity(6);

        Game expectedOutputGame2 = new Game();
        expectedOutputGame2.setItemId(2);
        expectedOutputGame2.setTitle("Fallout 4");
        expectedOutputGame2.setDescription("Epic installation in the Fallout series.");
        expectedOutputGame2.setEsrbRating("M");
        expectedOutputGame2.setStudio("Bethesda");
        expectedOutputGame2.setPrice(new BigDecimal("69.99"));
        expectedOutputGame2.setQuantity(11);

        Game expectedOutputGame3 = new Game();
        expectedOutputGame3.setItemId(3);
        expectedOutputGame3.setTitle("Final Fantasy VII Remake");
        expectedOutputGame3.setDescription("Epic remake of the classic Playstation 1 game.");
        expectedOutputGame3.setEsrbRating("T");
        expectedOutputGame3.setStudio("Square Enix");
        expectedOutputGame3.setPrice(new BigDecimal("59.99"));
        expectedOutputGame3.setQuantity(3);

        List<Game> expectedGameOutputList = new ArrayList<>();
        expectedGameOutputList.add(expectedOutputGame1);
        expectedGameOutputList.add(expectedOutputGame2);
        expectedGameOutputList.add(expectedOutputGame3);

        //Act
        List<Game> actualGameOutputList = service.getAllGames();

        //Assert
        assertEquals(expectedGameOutputList, actualGameOutputList);

    }

    //Tests associated with /console endpoints
    @Test
    public void shouldSaveConsole(){
        //Arrange
        Console inputConsole1 = new Console();
        inputConsole1.setModel("Playstation 5");
        inputConsole1.setManufacturer("Sony");
        inputConsole1.setMemoryAmount("825GB");
        inputConsole1.setProcessor("AMD Zen 2");
        inputConsole1.setPrice(new BigDecimal("499.99"));
        inputConsole1.setQuantity(25);

        Console inputConsole2 = new Console();
        inputConsole2.setModel("XBox Series X");
        inputConsole2.setManufacturer("Microsoft");
        inputConsole2.setMemoryAmount("1TB");
        inputConsole2.setProcessor("AMD Zen 2");
        inputConsole2.setPrice(new BigDecimal("499.99"));
        inputConsole2.setQuantity(5);

        Console inputConsole3 = new Console();
        inputConsole3.setModel("XBox Series S");
        inputConsole3.setManufacturer("Microsoft");
        inputConsole3.setMemoryAmount("512GB");
        inputConsole3.setProcessor("AMD Zen 2");
        inputConsole3.setPrice(new BigDecimal("299.99"));
        inputConsole3.setQuantity(11);

        Console expectedOutputConsole1 = new Console();
        expectedOutputConsole1.setItemId(1);
        expectedOutputConsole1.setModel("Playstation 5");
        expectedOutputConsole1.setManufacturer("Sony");
        expectedOutputConsole1.setMemoryAmount("825GB");
        expectedOutputConsole1.setProcessor("AMD Zen 2");
        expectedOutputConsole1.setPrice(new BigDecimal("499.99"));
        expectedOutputConsole1.setQuantity(25);

        Console expectedOutputConsole2 = new Console();
        expectedOutputConsole2.setItemId(2);
        expectedOutputConsole2.setModel("XBox Series X");
        expectedOutputConsole2.setManufacturer("Microsoft");
        expectedOutputConsole2.setMemoryAmount("1TB");
        expectedOutputConsole2.setProcessor("AMD Zen 2");
        expectedOutputConsole2.setPrice(new BigDecimal("499.99"));
        expectedOutputConsole2.setQuantity(5);

        Console expectedOutputConsole3 = new Console();
        expectedOutputConsole3.setItemId(3);
        expectedOutputConsole3.setModel("XBox Series S");
        expectedOutputConsole3.setManufacturer("Microsoft");
        expectedOutputConsole3.setMemoryAmount("512GB");
        expectedOutputConsole3.setProcessor("AMD Zen 2");
        expectedOutputConsole3.setPrice(new BigDecimal("299.99"));
        expectedOutputConsole3.setQuantity(11);

        //Act
        Console actualConsoleOutput1 = service.addConsole(inputConsole1);
        Console actualConsoleOutput2 = service.addConsole(inputConsole2);
        Console actualConsoleOutput3 = service.addConsole(inputConsole3);

        //Assert
        assertEquals(expectedOutputConsole1, actualConsoleOutput1);
        assertEquals(expectedOutputConsole2, actualConsoleOutput2);
        assertEquals(expectedOutputConsole3, actualConsoleOutput3);
    }

    @Test
    public void shouldFindConsoleById() throws JdbcOperationFailedException {
        //Arrange
        Console expectedOutputConsole1 = new Console();
        expectedOutputConsole1.setItemId(1);
        expectedOutputConsole1.setModel("Playstation 5");
        expectedOutputConsole1.setManufacturer("Sony");
        expectedOutputConsole1.setMemoryAmount("825GB");
        expectedOutputConsole1.setProcessor("AMD Zen 2");
        expectedOutputConsole1.setPrice(new BigDecimal("499.99"));
        expectedOutputConsole1.setQuantity(25);

        Console expectedOutputConsole2 = new Console();
        expectedOutputConsole2.setItemId(2);
        expectedOutputConsole2.setModel("XBox Series X");
        expectedOutputConsole2.setManufacturer("Microsoft");
        expectedOutputConsole2.setMemoryAmount("1TB");
        expectedOutputConsole2.setProcessor("AMD Zen 2");
        expectedOutputConsole2.setPrice(new BigDecimal("499.99"));
        expectedOutputConsole2.setQuantity(5);

        Console expectedOutputConsole3 = new Console();
        expectedOutputConsole3.setItemId(3);
        expectedOutputConsole3.setModel("XBox Series S");
        expectedOutputConsole3.setManufacturer("Microsoft");
        expectedOutputConsole3.setMemoryAmount("512GB");
        expectedOutputConsole3.setProcessor("AMD Zen 2");
        expectedOutputConsole3.setPrice(new BigDecimal("299.99"));
        expectedOutputConsole3.setQuantity(11);

        //Act
        Console actualOutputGame1 = service.getConsole(1);
        Console actualOutputGame2 = service.getConsole(2);
        Console actualOutputGame3 = service.getConsole(3);

        //Assert
        assertEquals(expectedOutputConsole1, actualOutputGame1);
        assertEquals(expectedOutputConsole2, actualOutputGame2);
        assertEquals(expectedOutputConsole3, actualOutputGame3);
    }

    @Test
    public void shouldFindConsoleByManufacturer(){
        //Arrange
        Console expectedOutputConsole1 = new Console();
        expectedOutputConsole1.setItemId(1);
        expectedOutputConsole1.setModel("Playstation 5");
        expectedOutputConsole1.setManufacturer("Sony");
        expectedOutputConsole1.setMemoryAmount("825GB");
        expectedOutputConsole1.setProcessor("AMD Zen 2");
        expectedOutputConsole1.setPrice(new BigDecimal("499.99"));
        expectedOutputConsole1.setQuantity(25);

        Console expectedOutputConsole2 = new Console();
        expectedOutputConsole2.setItemId(2);
        expectedOutputConsole2.setModel("XBox Series X");
        expectedOutputConsole2.setManufacturer("Microsoft");
        expectedOutputConsole2.setMemoryAmount("1TB");
        expectedOutputConsole2.setProcessor("AMD Zen 2");
        expectedOutputConsole2.setPrice(new BigDecimal("499.99"));
        expectedOutputConsole2.setQuantity(5);

        Console expectedOutputConsole3 = new Console();
        expectedOutputConsole3.setItemId(3);
        expectedOutputConsole3.setModel("XBox Series S");
        expectedOutputConsole3.setManufacturer("Microsoft");
        expectedOutputConsole3.setMemoryAmount("512GB");
        expectedOutputConsole3.setProcessor("AMD Zen 2");
        expectedOutputConsole3.setPrice(new BigDecimal("299.99"));
        expectedOutputConsole3.setQuantity(11);

        List<Console> expectedMicrosoftConsoles = new ArrayList<>();
        expectedMicrosoftConsoles.add(expectedOutputConsole2);
        expectedMicrosoftConsoles.add(expectedOutputConsole3);

        List<Console> expectedSonyConsoles = new ArrayList<>();
        expectedSonyConsoles.add(expectedOutputConsole1);

        //Act
        List<Console> actualMicrosoftGames = service.getConsoleByManufacturer("Microsoft");
        List<Console> actualSonyGames = service.getConsoleByManufacturer("Sony");

        //Assert
        assertEquals(expectedMicrosoftConsoles, actualMicrosoftGames);
        assertEquals(expectedSonyConsoles, actualSonyGames);

    }

    @Test
    public void shouldFindAllConsoles(){
        //Arrange
        Console expectedOutputConsole1 = new Console();
        expectedOutputConsole1.setItemId(1);
        expectedOutputConsole1.setModel("Playstation 5");
        expectedOutputConsole1.setManufacturer("Sony");
        expectedOutputConsole1.setMemoryAmount("825GB");
        expectedOutputConsole1.setProcessor("AMD Zen 2");
        expectedOutputConsole1.setPrice(new BigDecimal("499.99"));
        expectedOutputConsole1.setQuantity(25);

        Console expectedOutputConsole2 = new Console();
        expectedOutputConsole2.setItemId(2);
        expectedOutputConsole2.setModel("XBox Series X");
        expectedOutputConsole2.setManufacturer("Microsoft");
        expectedOutputConsole2.setMemoryAmount("1TB");
        expectedOutputConsole2.setProcessor("AMD Zen 2");
        expectedOutputConsole2.setPrice(new BigDecimal("499.99"));
        expectedOutputConsole2.setQuantity(5);

        Console expectedOutputConsole3 = new Console();
        expectedOutputConsole3.setItemId(3);
        expectedOutputConsole3.setModel("XBox Series S");
        expectedOutputConsole3.setManufacturer("Microsoft");
        expectedOutputConsole3.setMemoryAmount("512GB");
        expectedOutputConsole3.setProcessor("AMD Zen 2");
        expectedOutputConsole3.setPrice(new BigDecimal("299.99"));
        expectedOutputConsole3.setQuantity(11);

        List<Console> expectedConsoleOutputList = new ArrayList<>();
        expectedConsoleOutputList.add(expectedOutputConsole1);
        expectedConsoleOutputList.add(expectedOutputConsole2);
        expectedConsoleOutputList.add(expectedOutputConsole3);

        //Act
        List<Console> actualConsoleOutputList = service.getAllConsoles();

        //Assert
        assertEquals(expectedConsoleOutputList, actualConsoleOutputList);

    }

    //Tests associated with /tshirt endpoints
    @Test
    public void shouldSaveTShirt(){
        //Arrange
        TShirt inputTShirt1 = new TShirt();
        inputTShirt1.setSize("M");
        inputTShirt1.setColor("Green");
        inputTShirt1.setDescription("Minecraft Shirt");
        inputTShirt1.setPrice(new BigDecimal("24.99"));
        inputTShirt1.setQuantity(6);

        TShirt inputTShirt2 = new TShirt();
        inputTShirt2.setSize("XL");
        inputTShirt2.setColor("Black");
        inputTShirt2.setDescription("Minecraft Shirt");
        inputTShirt2.setPrice(new BigDecimal("29.99"));
        inputTShirt2.setQuantity(12);

        TShirt inputTShirt3 = new TShirt();
        inputTShirt3.setSize("M");
        inputTShirt3.setColor("Black");
        inputTShirt3.setDescription("COD Shirt");
        inputTShirt3.setPrice(new BigDecimal("24.99"));
        inputTShirt3.setQuantity(1);

        TShirt expectedOutputTShirt1 = new TShirt();
        expectedOutputTShirt1.setItemId(1);
        expectedOutputTShirt1.setSize("M");
        expectedOutputTShirt1.setColor("Green");
        expectedOutputTShirt1.setDescription("Minecraft Shirt");
        expectedOutputTShirt1.setPrice(new BigDecimal("24.99"));
        expectedOutputTShirt1.setQuantity(6);

        TShirt expectedOutputTShirt2 = new TShirt();
        expectedOutputTShirt2.setItemId(2);
        expectedOutputTShirt2.setSize("XL");
        expectedOutputTShirt2.setColor("Black");
        expectedOutputTShirt2.setDescription("Minecraft Shirt");
        expectedOutputTShirt2.setPrice(new BigDecimal("29.99"));
        expectedOutputTShirt2.setQuantity(12);

        TShirt expectedOutputTShirt3 = new TShirt();
        expectedOutputTShirt3.setItemId(3);
        expectedOutputTShirt3.setSize("M");
        expectedOutputTShirt3.setColor("Black");
        expectedOutputTShirt3.setDescription("COD Shirt");
        expectedOutputTShirt3.setPrice(new BigDecimal("24.99"));
        expectedOutputTShirt3.setQuantity(1);

        //Act
        TShirt actualTShirtOutput1 = service.addTShirt(inputTShirt1);
        TShirt actualTShirtOutput2 = service.addTShirt(inputTShirt2);
        TShirt actualTShirtOutput3 = service.addTShirt(inputTShirt3);

        //Assert
        assertEquals(expectedOutputTShirt1, actualTShirtOutput1);
        assertEquals(expectedOutputTShirt2, actualTShirtOutput2);
        assertEquals(expectedOutputTShirt3, actualTShirtOutput3);
    }

    @Test
    public void shouldFindTShirtById() throws JdbcOperationFailedException {
        //Arrange
        TShirt expectedOutputTShirt1 = new TShirt();
        expectedOutputTShirt1.setItemId(1);
        expectedOutputTShirt1.setSize("M");
        expectedOutputTShirt1.setColor("Green");
        expectedOutputTShirt1.setDescription("Minecraft Shirt");
        expectedOutputTShirt1.setPrice(new BigDecimal("24.99"));
        expectedOutputTShirt1.setQuantity(6);

        TShirt expectedOutputTShirt2 = new TShirt();
        expectedOutputTShirt2.setItemId(2);
        expectedOutputTShirt2.setSize("XL");
        expectedOutputTShirt2.setColor("Black");
        expectedOutputTShirt2.setDescription("Minecraft Shirt");
        expectedOutputTShirt2.setPrice(new BigDecimal("29.99"));
        expectedOutputTShirt2.setQuantity(12);

        TShirt expectedOutputTShirt3 = new TShirt();
        expectedOutputTShirt3.setItemId(3);
        expectedOutputTShirt3.setSize("M");
        expectedOutputTShirt3.setColor("Black");
        expectedOutputTShirt3.setDescription("COD Shirt");
        expectedOutputTShirt3.setPrice(new BigDecimal("24.99"));
        expectedOutputTShirt3.setQuantity(1);

        //Act
        TShirt actualOutputTShirt1 = service.getTShirt(1);
        TShirt actualOutputTShirt2 = service.getTShirt(2);
        TShirt actualOutputTShirt3 = service.getTShirt(3);

        //Assert
        assertEquals(expectedOutputTShirt1, actualOutputTShirt1);
        assertEquals(expectedOutputTShirt2, actualOutputTShirt2);
        assertEquals(expectedOutputTShirt3, actualOutputTShirt3);
    }

    @Test
    public void shouldFindTShirtByColor(){
        //Arrange
        TShirt expectedOutputTShirt1 = new TShirt();
        expectedOutputTShirt1.setItemId(1);
        expectedOutputTShirt1.setSize("M");
        expectedOutputTShirt1.setColor("Green");
        expectedOutputTShirt1.setDescription("Minecraft Shirt");
        expectedOutputTShirt1.setPrice(new BigDecimal("24.99"));
        expectedOutputTShirt1.setQuantity(6);

        TShirt expectedOutputTShirt2 = new TShirt();
        expectedOutputTShirt2.setItemId(2);
        expectedOutputTShirt2.setSize("XL");
        expectedOutputTShirt2.setColor("Black");
        expectedOutputTShirt2.setDescription("Minecraft Shirt");
        expectedOutputTShirt2.setPrice(new BigDecimal("29.99"));
        expectedOutputTShirt2.setQuantity(12);

        TShirt expectedOutputTShirt3 = new TShirt();
        expectedOutputTShirt3.setItemId(3);
        expectedOutputTShirt3.setSize("M");
        expectedOutputTShirt3.setColor("Black");
        expectedOutputTShirt3.setDescription("COD Shirt");
        expectedOutputTShirt3.setPrice(new BigDecimal("24.99"));
        expectedOutputTShirt3.setQuantity(1);

        List<TShirt> expectedBlackShirts = new ArrayList<>();
        expectedBlackShirts.add(expectedOutputTShirt2);
        expectedBlackShirts.add(expectedOutputTShirt3);

        List<TShirt> expectedGreenShirts = new ArrayList<>();
        expectedGreenShirts.add(expectedOutputTShirt1);

        //Act
        List<TShirt> actualBlackShirts = service.getTShirtByColor("Black");
        List<TShirt> actualGreenShirts = service.getTShirtByColor("Green");

        //Assert
        assertEquals(expectedBlackShirts, actualBlackShirts);
        assertEquals(expectedGreenShirts, actualGreenShirts);

    }

    @Test
    public void shouldFindTShirtBySize(){
        //Arrange
        TShirt expectedOutputTShirt1 = new TShirt();
        expectedOutputTShirt1.setItemId(1);
        expectedOutputTShirt1.setSize("M");
        expectedOutputTShirt1.setColor("Green");
        expectedOutputTShirt1.setDescription("Minecraft Shirt");
        expectedOutputTShirt1.setPrice(new BigDecimal("24.99"));
        expectedOutputTShirt1.setQuantity(6);

        TShirt expectedOutputTShirt2 = new TShirt();
        expectedOutputTShirt2.setItemId(2);
        expectedOutputTShirt2.setSize("XL");
        expectedOutputTShirt2.setColor("Black");
        expectedOutputTShirt2.setDescription("Minecraft Shirt");
        expectedOutputTShirt2.setPrice(new BigDecimal("29.99"));
        expectedOutputTShirt2.setQuantity(12);

        TShirt expectedOutputTShirt3 = new TShirt();
        expectedOutputTShirt3.setItemId(3);
        expectedOutputTShirt3.setSize("M");
        expectedOutputTShirt3.setColor("Black");
        expectedOutputTShirt3.setDescription("COD Shirt");
        expectedOutputTShirt3.setPrice(new BigDecimal("24.99"));
        expectedOutputTShirt3.setQuantity(1);

        List<TShirt> expectedMSizeShirts = new ArrayList<>();
        expectedMSizeShirts.add(expectedOutputTShirt1);
        expectedMSizeShirts.add(expectedOutputTShirt3);

        List<TShirt> expectedXLSizeShirts = new ArrayList<>();
        expectedXLSizeShirts.add(expectedOutputTShirt2);

        //Act
        List<TShirt> actualMSizeShirts = service.getTShirtBySize("M");
        List<TShirt> actualXLSizeShirts = service.getTShirtBySize("XL");

        //Assert
        assertEquals(expectedMSizeShirts, actualMSizeShirts);
        assertEquals(expectedXLSizeShirts, actualXLSizeShirts);

    }

    @Test
    public void shouldFindAllTShirts(){
        //Arrange
        TShirt expectedOutputTShirt1 = new TShirt();
        expectedOutputTShirt1.setItemId(1);
        expectedOutputTShirt1.setSize("M");
        expectedOutputTShirt1.setColor("Green");
        expectedOutputTShirt1.setDescription("Minecraft Shirt");
        expectedOutputTShirt1.setPrice(new BigDecimal("24.99"));
        expectedOutputTShirt1.setQuantity(6);

        TShirt expectedOutputTShirt2 = new TShirt();
        expectedOutputTShirt2.setItemId(2);
        expectedOutputTShirt2.setSize("XL");
        expectedOutputTShirt2.setColor("Black");
        expectedOutputTShirt2.setDescription("Minecraft Shirt");
        expectedOutputTShirt2.setPrice(new BigDecimal("29.99"));
        expectedOutputTShirt2.setQuantity(12);

        TShirt expectedOutputTShirt3 = new TShirt();
        expectedOutputTShirt3.setItemId(3);
        expectedOutputTShirt3.setSize("M");
        expectedOutputTShirt3.setColor("Black");
        expectedOutputTShirt3.setDescription("COD Shirt");
        expectedOutputTShirt3.setPrice(new BigDecimal("24.99"));
        expectedOutputTShirt3.setQuantity(1);

        List<TShirt> expectedOutputTShirtList = new ArrayList<>();
        expectedOutputTShirtList.add(expectedOutputTShirt1);
        expectedOutputTShirtList.add(expectedOutputTShirt2);
        expectedOutputTShirtList.add(expectedOutputTShirt3);

        //Act
        List<TShirt> actualTShirtOutputList = service.getAllTShirt();

        //Assert
        assertEquals(expectedOutputTShirtList, actualTShirtOutputList);

    }

    //Tests Associated with /invoice Endpoint
    @Test
    public void shouldAssembleCompleteInvoiceViewModelGivenAValidOrderInvoice() throws OutOfStockException, JdbcOperationFailedException {
        //Arrange
        Invoice inputInvoice1 = new Invoice();
        inputInvoice1.setName("Jack");
        inputInvoice1.setStreet("123 Main St.");
        inputInvoice1.setCity("Main City");
        inputInvoice1.setState("ME");
        inputInvoice1.setZipcode("12345");
        inputInvoice1.setItemType("Games");
        inputInvoice1.setItemId(1);
        inputInvoice1.setQuantity(1);

        Invoice inputInvoice2 = new Invoice();
        inputInvoice2.setName("Jill");
        inputInvoice2.setStreet("321 Other Dr.");
        inputInvoice2.setCity("Other City");
        inputInvoice2.setState("OR");
        inputInvoice2.setZipcode("98765");
        inputInvoice2.setItemType("T-Shirts");
        inputInvoice2.setItemId(1);
        inputInvoice2.setQuantity(2);

        Invoice inputInvoice3 = new Invoice();
        inputInvoice3.setName("Diane");
        inputInvoice3.setStreet("456 Another Rd.");
        inputInvoice3.setCity("Another City");
        inputInvoice3.setState("AR");
        inputInvoice3.setZipcode("46525");
        inputInvoice3.setItemType("Consoles");
        inputInvoice3.setItemId(1);
        inputInvoice3.setQuantity(15);

        Game testGame1 = new Game();
        testGame1.setItemId(1);
        testGame1.setTitle("Skyrim");
        testGame1.setDescription("Epic installation in the Elder Scrolls series.");
        testGame1.setEsrbRating("T");
        testGame1.setStudio("Bethesda");
        testGame1.setPrice(new BigDecimal("69.99"));
        testGame1.setQuantity(1); //this should be the same as the invoice quantity

        TShirt testShirt1 = new TShirt();
        testShirt1.setItemId(1);
        testShirt1.setSize("M");
        testShirt1.setColor("Green");
        testShirt1.setDescription("Minecraft Shirt");
        testShirt1.setPrice(new BigDecimal("24.99"));
        testShirt1.setQuantity(2); //this should be the same as the invoice quantity

        Console testConsole1 = new Console();
        testConsole1.setItemId(1);
        testConsole1.setModel("Playstation 5");
        testConsole1.setManufacturer("Sony");
        testConsole1.setMemoryAmount("825GB");
        testConsole1.setProcessor("AMD Zen 2");
        testConsole1.setPrice(new BigDecimal("499.99"));
        testConsole1.setQuantity(15); //this should be the same as the invoice quantity

        BigDecimal quantity = new BigDecimal("1");
        BigDecimal unitPrice = testGame1.getPrice();
        BigDecimal subtotal = unitPrice.multiply(quantity);
        BigDecimal taxRate = new BigDecimal(".03");
        BigDecimal totalTax = subtotal.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
        BigDecimal processingFee = new BigDecimal("1.49");
        BigDecimal total = subtotal.add(totalTax.add(processingFee));

        InvoiceViewModel expectedOutputInvoiceViewModel1 = new InvoiceViewModel();
        expectedOutputInvoiceViewModel1.setInvoiceId(1);
        expectedOutputInvoiceViewModel1.setName("Jack");
        expectedOutputInvoiceViewModel1.setStreet("123 Main St.");
        expectedOutputInvoiceViewModel1.setCity("Main City");
        expectedOutputInvoiceViewModel1.setState("ME");
        expectedOutputInvoiceViewModel1.setZipcode("12345");
        expectedOutputInvoiceViewModel1.setItem(testGame1);
        expectedOutputInvoiceViewModel1.setSubtotal(subtotal);
        expectedOutputInvoiceViewModel1.setSalesTaxRate(new BigDecimal(".03"));
        expectedOutputInvoiceViewModel1.setTax(totalTax);
        expectedOutputInvoiceViewModel1.setProcessingFee(processingFee);
        expectedOutputInvoiceViewModel1.setTotal(total);

        quantity = new BigDecimal("2");
        unitPrice = testShirt1.getPrice();
        subtotal = unitPrice.multiply(quantity);
        taxRate = new BigDecimal(".07");
        totalTax = subtotal.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
        processingFee = new BigDecimal("1.98");
        total = subtotal.add(totalTax.add(processingFee));


        InvoiceViewModel expectedOutputInvoiceViewModel2 = new InvoiceViewModel();
        expectedOutputInvoiceViewModel2.setInvoiceId(2);
        expectedOutputInvoiceViewModel2.setName("Jill");
        expectedOutputInvoiceViewModel2.setStreet("321 Other Dr.");
        expectedOutputInvoiceViewModel2.setCity("Other City");
        expectedOutputInvoiceViewModel2.setState("OR");
        expectedOutputInvoiceViewModel2.setZipcode("98765");
        expectedOutputInvoiceViewModel2.setItem(testShirt1);
        expectedOutputInvoiceViewModel2.setSalesTaxRate(new BigDecimal(".07"));
        expectedOutputInvoiceViewModel2.setSubtotal(subtotal);
        expectedOutputInvoiceViewModel2.setTax(totalTax);
        expectedOutputInvoiceViewModel2.setProcessingFee(processingFee);
        expectedOutputInvoiceViewModel2.setTotal(total);

        quantity = new BigDecimal("15");
        unitPrice = testConsole1.getPrice();
        subtotal = unitPrice.multiply(quantity);
        taxRate = new BigDecimal(".06");
        totalTax = subtotal.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
        processingFee = new BigDecimal("30.48");
        total = subtotal.add(totalTax.add(processingFee));

        InvoiceViewModel expectedOutputInvoiceViewModel3 = new InvoiceViewModel();
        expectedOutputInvoiceViewModel3.setInvoiceId(3);
        expectedOutputInvoiceViewModel3.setName("Diane");
        expectedOutputInvoiceViewModel3.setStreet("456 Another Rd.");
        expectedOutputInvoiceViewModel3.setCity("Another City");
        expectedOutputInvoiceViewModel3.setState("AR");
        expectedOutputInvoiceViewModel3.setZipcode("46525");
        expectedOutputInvoiceViewModel3.setItem(testConsole1);
        expectedOutputInvoiceViewModel3.setSubtotal(subtotal);
        expectedOutputInvoiceViewModel3.setSalesTaxRate(new BigDecimal(".06"));
        expectedOutputInvoiceViewModel3.setTax(totalTax);
        expectedOutputInvoiceViewModel3.setProcessingFee(processingFee);
        expectedOutputInvoiceViewModel3.setTotal(total);

        //Act
        InvoiceViewModel actualOutputInvoice1 = service.placeOrderAndAddInvoice(inputInvoice1);
        InvoiceViewModel actualOutputInvoice2 = service.placeOrderAndAddInvoice(inputInvoice2);
        InvoiceViewModel actualOutputInvoice3 = service.placeOrderAndAddInvoice(inputInvoice3);

        //Assert
        assertEquals(expectedOutputInvoiceViewModel1, actualOutputInvoice1);
        assertEquals(expectedOutputInvoiceViewModel2, actualOutputInvoice2);
        assertEquals(expectedOutputInvoiceViewModel3, actualOutputInvoice3);
    }

    @Test
    public void shouldThrowOutOfStockExceptionWhenPassedAnInvoiceWithQuantityHigherThanItemInventoryLevel() throws OutOfStockException, JdbcOperationFailedException {
        //Arrange
        Invoice inputInvoice1 = new Invoice();
        inputInvoice1.setName("Jack");
        inputInvoice1.setStreet("123 Main St.");
        inputInvoice1.setCity("Main City");
        inputInvoice1.setState("ME");
        inputInvoice1.setZipcode("12345");
        inputInvoice1.setItemType("Games");
        inputInvoice1.setItemId(1);
        inputInvoice1.setQuantity(125);

        Invoice inputInvoice2 = new Invoice();
        inputInvoice2.setName("Jill");
        inputInvoice2.setStreet("321 Other Dr.");
        inputInvoice2.setCity("Other City");
        inputInvoice2.setState("OR");
        inputInvoice2.setZipcode("98765");
        inputInvoice2.setItemType("T-Shirts");
        inputInvoice2.setItemId(1);
        inputInvoice2.setQuantity(23);

        Invoice inputInvoice3 = new Invoice();
        inputInvoice3.setName("Diane");
        inputInvoice3.setStreet("456 Another Rd.");
        inputInvoice3.setCity("Another City");
        inputInvoice3.setState("AR");
        inputInvoice3.setZipcode("46525");
        inputInvoice3.setItemType("Consoles");
        inputInvoice3.setItemId(1);
        inputInvoice3.setQuantity(26);

        //Act and Assert
        assertThrows(OutOfStockException.class, () -> {
            service.placeOrderAndAddInvoice(inputInvoice1);
        });

        assertThrows(OutOfStockException.class, () -> {
            service.placeOrderAndAddInvoice(inputInvoice2);
        });

        assertThrows(OutOfStockException.class, () -> {
            service.placeOrderAndAddInvoice(inputInvoice3);
        });
    }

    @Test
    public void shouldThrowJdbcOperationFailedExeptionWhenPassedAnInvoiceWithInvalidItemType() throws OutOfStockException, JdbcOperationFailedException {
        //Arrange
        Invoice inputInvoice1 = new Invoice();
        inputInvoice1.setName("Jack");
        inputInvoice1.setStreet("123 Main St.");
        inputInvoice1.setCity("Main City");
        inputInvoice1.setState("ME");
        inputInvoice1.setZipcode("12345");
        inputInvoice1.setItemType("Guitars");
        inputInvoice1.setItemId(1);
        inputInvoice1.setQuantity(1);

        //Act and Assert
        assertThrows(JdbcOperationFailedException.class, () -> {
            service.placeOrderAndAddInvoice(inputInvoice1);
        });
    }

    public void setupMockProcessingFeeDao() throws JdbcOperationFailedException {
        processingFeeDao = mock(ProcessingFeeDaoJdbcTemplateImpl.class);

        when((processingFeeDao.findProcessingFeeByItemType("Games"))).thenReturn(new ProcessingFee("Games", new BigDecimal("1.49")));
        when((processingFeeDao.findProcessingFeeByItemType("T-Shirts"))).thenReturn(new ProcessingFee("T-Shirts", new BigDecimal("1.98")));
        when((processingFeeDao.findProcessingFeeByItemType("Consoles"))).thenReturn(new ProcessingFee("Consoles", new BigDecimal("14.99")));
        doThrow(JdbcOperationFailedException.class).when(processingFeeDao).findProcessingFeeByItemType("Guitars");
    }

    public void setupMockSalesTaxDao() throws JdbcOperationFailedException {
        salesTaxDao = mock(SalesTaxDaoJdbcTemplateImpl.class);

        when(salesTaxDao.findSalesTaxByState("AR")).thenReturn(new SalesTaxRate("AR", new BigDecimal(".06")));
        when(salesTaxDao.findSalesTaxByState("OR")).thenReturn(new SalesTaxRate("OR", new BigDecimal(".07")));
        when(salesTaxDao.findSalesTaxByState("ME")).thenReturn(new SalesTaxRate("ME", new BigDecimal(".03")));
    }

    public void setupMockInvoiceDao(){
        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);

        Game testGame1 = new Game();
        testGame1.setItemId(1);
        testGame1.setTitle("Skyrim");
        testGame1.setDescription("Epic installation in the Elder Scrolls series.");
        testGame1.setEsrbRating("T");
        testGame1.setStudio("Bethesda");
        testGame1.setPrice(new BigDecimal("69.99"));
        testGame1.setQuantity(6);

        TShirt testShirt1 = new TShirt();
        testShirt1.setItemId(1);
        testShirt1.setSize("M");
        testShirt1.setColor("Green");
        testShirt1.setDescription("Minecraft Shirt");
        testShirt1.setPrice(new BigDecimal("24.99"));
        testShirt1.setQuantity(6);

        Console testConsole1 = new Console();
        testConsole1.setItemId(1);
        testConsole1.setModel("Playstation 5");
        testConsole1.setManufacturer("Sony");
        testConsole1.setMemoryAmount("825GB");
        testConsole1.setProcessor("AMD Zen 2");
        testConsole1.setPrice(new BigDecimal("499.99"));
        testConsole1.setQuantity(25);

        BigDecimal quantity = new BigDecimal("1");
        BigDecimal unitPrice = testGame1.getPrice();
        BigDecimal subtotal = unitPrice.multiply(quantity);
        BigDecimal taxRate = new BigDecimal(".03");
        BigDecimal totalTax = subtotal.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
        BigDecimal processingFee = new BigDecimal("1.49");
        BigDecimal total = subtotal.add(totalTax.add(processingFee));

        Invoice inputInvoice1 = new Invoice();
        inputInvoice1.setName("Jack");
        inputInvoice1.setStreet("123 Main St.");
        inputInvoice1.setCity("Main City");
        inputInvoice1.setState("ME");
        inputInvoice1.setZipcode("12345");
        inputInvoice1.setItemType("Games");
        inputInvoice1.setItemId(testGame1.getItemId());
        inputInvoice1.setUnitPrice(unitPrice);
        inputInvoice1.setQuantity(quantity.intValue());
        inputInvoice1.setSubtotal(subtotal);
        inputInvoice1.setTax(totalTax);
        inputInvoice1.setProcessingFee(processingFee);
        inputInvoice1.setTotal(total);

        quantity = new BigDecimal("2");
        unitPrice = testShirt1.getPrice();
        subtotal = unitPrice.multiply(quantity);
        taxRate = new BigDecimal(".07");
        totalTax = subtotal.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
        processingFee = new BigDecimal("1.98");
        total = subtotal.add(totalTax.add(processingFee));

        Invoice inputInvoice2 = new Invoice();
        inputInvoice2.setName("Jill");
        inputInvoice2.setStreet("321 Other Dr.");
        inputInvoice2.setCity("Other City");
        inputInvoice2.setState("OR");
        inputInvoice2.setZipcode("98765");
        inputInvoice2.setItemType("T-Shirts");
        inputInvoice2.setItemId(testShirt1.getItemId());
        inputInvoice2.setUnitPrice(unitPrice);
        inputInvoice2.setQuantity(quantity.intValue());
        inputInvoice2.setSubtotal(subtotal);
        inputInvoice2.setTax(totalTax);
        inputInvoice2.setProcessingFee(processingFee);
        inputInvoice2.setTotal(total);

        quantity = new BigDecimal("15");
        unitPrice = testConsole1.getPrice();
        subtotal = unitPrice.multiply(quantity);
        taxRate = new BigDecimal(".06");
        totalTax = subtotal.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
        processingFee = new BigDecimal("30.48");
        total = subtotal.add(totalTax.add(processingFee));

        Invoice inputInvoice3 = new Invoice();
        inputInvoice3.setName("Diane");
        inputInvoice3.setStreet("456 Another Rd.");
        inputInvoice3.setCity("Another City");
        inputInvoice3.setState("AR");
        inputInvoice3.setZipcode("46525");
        inputInvoice3.setItemType("Consoles");
        inputInvoice3.setItemId(testConsole1.getItemId());
        inputInvoice3.setUnitPrice(unitPrice);
        inputInvoice3.setQuantity(quantity.intValue());
        inputInvoice3.setSubtotal(subtotal);
        inputInvoice3.setTax(totalTax);
        inputInvoice3.setProcessingFee(processingFee);
        inputInvoice3.setTotal(total);

        quantity = new BigDecimal("1");
        unitPrice = testGame1.getPrice();
        subtotal = unitPrice.multiply(quantity);
        taxRate = new BigDecimal(".03");
        totalTax = subtotal.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
        processingFee = new BigDecimal("1.49");
        total = subtotal.add(totalTax.add(processingFee));

        Invoice outputInvoice1 = new Invoice();
        outputInvoice1.setInvoiceId(1);
        outputInvoice1.setName("Jack");
        outputInvoice1.setStreet("123 Main St.");
        outputInvoice1.setCity("Main City");
        outputInvoice1.setState("ME");
        outputInvoice1.setZipcode("12345");
        outputInvoice1.setItemType("Games");
        outputInvoice1.setItemId(testGame1.getItemId());
        outputInvoice1.setUnitPrice(unitPrice);
        outputInvoice1.setQuantity(quantity.intValue());
        outputInvoice1.setSubtotal(subtotal);
        outputInvoice1.setTax(totalTax);
        outputInvoice1.setProcessingFee(processingFee);
        outputInvoice1.setTotal(total);

        quantity = new BigDecimal("2");
        unitPrice = testShirt1.getPrice();
        subtotal = unitPrice.multiply(quantity);
        taxRate = new BigDecimal(".07");
        totalTax = subtotal.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
        processingFee = new BigDecimal("1.98");
        total = subtotal.add(totalTax.add(processingFee));

        Invoice outputInvoice2 = new Invoice();
        outputInvoice2.setInvoiceId(2);
        outputInvoice2.setName("Jill");
        outputInvoice2.setStreet("321 Other Dr.");
        outputInvoice2.setCity("Other City");
        outputInvoice2.setState("OR");
        outputInvoice2.setZipcode("98765");
        outputInvoice2.setItemType("T-Shirts");
        outputInvoice2.setItemId(testShirt1.getItemId());
        outputInvoice2.setUnitPrice(unitPrice);
        outputInvoice2.setQuantity(quantity.intValue());
        outputInvoice2.setSubtotal(subtotal);
        outputInvoice2.setTax(totalTax);
        outputInvoice2.setProcessingFee(processingFee);
        outputInvoice2.setTotal(total);

        quantity = new BigDecimal("15");
        unitPrice = testConsole1.getPrice();
        subtotal = unitPrice.multiply(quantity);
        taxRate = new BigDecimal(".06");
        totalTax = subtotal.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
        processingFee = new BigDecimal("30.48");
        total = subtotal.add(totalTax.add(processingFee));

        Invoice outputInvoice3 = new Invoice();
        outputInvoice3.setInvoiceId(3);
        outputInvoice3.setName("Diane");
        outputInvoice3.setStreet("456 Another Rd.");
        outputInvoice3.setCity("Another City");
        outputInvoice3.setState("AR");
        outputInvoice3.setZipcode("46525");
        outputInvoice3.setItemType("Consoles");
        outputInvoice3.setItemId(testConsole1.getItemId());
        outputInvoice3.setUnitPrice(unitPrice);
        outputInvoice3.setQuantity(quantity.intValue());
        outputInvoice3.setSubtotal(subtotal);
        outputInvoice3.setTax(totalTax);
        outputInvoice3.setProcessingFee(processingFee);
        outputInvoice3.setTotal(total);

        doReturn(outputInvoice1).when(invoiceDao).saveInvoice(inputInvoice1);
        doReturn(outputInvoice2).when(invoiceDao).saveInvoice(inputInvoice2);
        doReturn(outputInvoice3).when(invoiceDao).saveInvoice(inputInvoice3);
    }

    public void setupMockTShirtDao() throws JdbcOperationFailedException {
        tShirtDao = mock(TShirtDaoJdbcTemplateImpl.class);

        TShirt inputTShirt1 = new TShirt();
        inputTShirt1.setSize("M");
        inputTShirt1.setColor("Green");
        inputTShirt1.setDescription("Minecraft Shirt");
        inputTShirt1.setPrice(new BigDecimal("24.99"));
        inputTShirt1.setQuantity(6);

        TShirt inputTShirt2 = new TShirt();
        inputTShirt2.setSize("XL");
        inputTShirt2.setColor("Black");
        inputTShirt2.setDescription("Minecraft Shirt");
        inputTShirt2.setPrice(new BigDecimal("29.99"));
        inputTShirt2.setQuantity(12);

        TShirt inputTShirt3 = new TShirt();
        inputTShirt3.setSize("M");
        inputTShirt3.setColor("Black");
        inputTShirt3.setDescription("COD Shirt");
        inputTShirt3.setPrice(new BigDecimal("24.99"));
        inputTShirt3.setQuantity(1);

        TShirt outputTShirt1 = new TShirt();
        outputTShirt1.setItemId(1);
        outputTShirt1.setSize("M");
        outputTShirt1.setColor("Green");
        outputTShirt1.setDescription("Minecraft Shirt");
        outputTShirt1.setPrice(new BigDecimal("24.99"));
        outputTShirt1.setQuantity(6);

        TShirt outputTShirt2 = new TShirt();
        outputTShirt2.setItemId(2);
        outputTShirt2.setSize("XL");
        outputTShirt2.setColor("Black");
        outputTShirt2.setDescription("Minecraft Shirt");
        outputTShirt2.setPrice(new BigDecimal("29.99"));
        outputTShirt2.setQuantity(12);

        TShirt outputTShirt3 = new TShirt();
        outputTShirt3.setItemId(3);
        outputTShirt3.setSize("M");
        outputTShirt3.setColor("Black");
        outputTShirt3.setDescription("COD Shirt");
        outputTShirt3.setPrice(new BigDecimal("24.99"));
        outputTShirt3.setQuantity(1);

        List<TShirt> allOutputTShirtList = new ArrayList<>();
        allOutputTShirtList.add(outputTShirt1);
        allOutputTShirtList.add(outputTShirt2);
        allOutputTShirtList.add(outputTShirt3);

        List<TShirt> blackShirts = new ArrayList<>();
        blackShirts.add(outputTShirt2);
        blackShirts.add(outputTShirt3);

        List<TShirt> greenShirts = new ArrayList<>();
        greenShirts.add(outputTShirt1);

        List<TShirt> mSizeShirts = new ArrayList<>();
        mSizeShirts.add(outputTShirt1);
        mSizeShirts.add(outputTShirt3);

        List<TShirt> xlSizeShirts = new ArrayList<>();
        xlSizeShirts.add(outputTShirt2);

        doReturn(outputTShirt1).when(tShirtDao).saveTShirt(inputTShirt1);
        doReturn(outputTShirt2).when(tShirtDao).saveTShirt(inputTShirt2);
        doReturn(outputTShirt3).when(tShirtDao).saveTShirt(inputTShirt3);
        doReturn(outputTShirt1).when(tShirtDao).findTShirt(1);
        doReturn(outputTShirt2).when(tShirtDao).findTShirt(2);
        doReturn(outputTShirt3).when(tShirtDao).findTShirt(3);
        doReturn(allOutputTShirtList).when(tShirtDao).findAllTShirts();
        doReturn(blackShirts).when(tShirtDao).findTShirtByColor("Black");
        doReturn(greenShirts).when(tShirtDao).findTShirtByColor("Green");
        doReturn(xlSizeShirts).when(tShirtDao).findTShirtBySize("XL");
        doReturn(mSizeShirts).when(tShirtDao).findTShirtBySize("M");
    }

    public void setupMockGameDao() throws JdbcOperationFailedException {
        gameDao = mock(GameDaoJdbcTemplateImpl.class);

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

        doReturn(outputGame1).when(gameDao).saveGame(inputGame1);
        doReturn(outputGame2).when(gameDao).saveGame(inputGame2);
        doReturn(outputGame3).when(gameDao).saveGame(inputGame3);
        doReturn(outputGame1).when(gameDao).findGame(1);
        doReturn(outputGame2).when(gameDao).findGame(2);
        doReturn(outputGame3).when(gameDao).findGame(3);
        doReturn(allOutputGames).when(gameDao).findAllGames();
        doReturn(bethesdaGames).when(gameDao).findGameByStudio("Bethesda");
        doReturn(squareEnixGames).when(gameDao).findGameByStudio("Square Enix");
        doReturn(tRatedGames).when(gameDao).findGameByEsrbRating("T");
        doReturn(mRatedGames).when(gameDao).findGameByEsrbRating("M");
        doReturn(skyrimList).when(gameDao).findGameByTitle("Skyrim");
        doReturn(falloutList).when(gameDao).findGameByTitle("Fallout 4");
        doReturn(ffviiList).when(gameDao).findGameByTitle("Final Fantasy VII Remake");
    }

    public void setupMockConsoleDao() throws JdbcOperationFailedException {
        consoleDao = mock(ConsoleDaoJdbcTemplateImpl.class);

        Console inputConsole1 = new Console();
        inputConsole1.setModel("Playstation 5");
        inputConsole1.setManufacturer("Sony");
        inputConsole1.setMemoryAmount("825GB");
        inputConsole1.setProcessor("AMD Zen 2");
        inputConsole1.setPrice(new BigDecimal("499.99"));
        inputConsole1.setQuantity(25);

        Console inputConsole2 = new Console();
        inputConsole2.setModel("XBox Series X");
        inputConsole2.setManufacturer("Microsoft");
        inputConsole2.setMemoryAmount("1TB");
        inputConsole2.setProcessor("AMD Zen 2");
        inputConsole2.setPrice(new BigDecimal("499.99"));
        inputConsole2.setQuantity(5);

        Console inputConsole3 = new Console();
        inputConsole3.setModel("XBox Series S");
        inputConsole3.setManufacturer("Microsoft");
        inputConsole3.setMemoryAmount("512GB");
        inputConsole3.setProcessor("AMD Zen 2");
        inputConsole3.setPrice(new BigDecimal("299.99"));
        inputConsole3.setQuantity(11);

        Console outputConsole1 = new Console();
        outputConsole1.setItemId(1);
        outputConsole1.setModel("Playstation 5");
        outputConsole1.setManufacturer("Sony");
        outputConsole1.setMemoryAmount("825GB");
        outputConsole1.setProcessor("AMD Zen 2");
        outputConsole1.setPrice(new BigDecimal("499.99"));
        outputConsole1.setQuantity(25);

        Console outputConsole2 = new Console();
        outputConsole2.setItemId(2);
        outputConsole2.setModel("XBox Series X");
        outputConsole2.setManufacturer("Microsoft");
        outputConsole2.setMemoryAmount("1TB");
        outputConsole2.setProcessor("AMD Zen 2");
        outputConsole2.setPrice(new BigDecimal("499.99"));
        outputConsole2.setQuantity(5);

        Console outputConsole3 = new Console();
        outputConsole3.setItemId(3);
        outputConsole3.setModel("XBox Series S");
        outputConsole3.setManufacturer("Microsoft");
        outputConsole3.setMemoryAmount("512GB");
        outputConsole3.setProcessor("AMD Zen 2");
        outputConsole3.setPrice(new BigDecimal("299.99"));
        outputConsole3.setQuantity(11);

        List<Console> outputAllConsolesList = new ArrayList<>();
        outputAllConsolesList.add(outputConsole1);
        outputAllConsolesList.add(outputConsole2);
        outputAllConsolesList.add(outputConsole3);

        List<Console> microsoftConsoles = new ArrayList<>();
        microsoftConsoles.add(outputConsole2);
        microsoftConsoles.add(outputConsole3);

        List<Console> sonyConsoles = new ArrayList<>();
        sonyConsoles.add(outputConsole1);

        doReturn(outputConsole1).when(consoleDao).saveConsole(inputConsole1);
        doReturn(outputConsole2).when(consoleDao).saveConsole(inputConsole2);
        doReturn(outputConsole3).when(consoleDao).saveConsole(inputConsole3);
        doReturn(outputConsole1).when(consoleDao).findConsole(1);
        doReturn(outputConsole2).when(consoleDao).findConsole(2);
        doReturn(outputConsole3).when(consoleDao).findConsole(3);
        doReturn(outputAllConsolesList).when(consoleDao).findAllConsoles();
        doReturn(microsoftConsoles).when(consoleDao).findConsoleByManufacturer("Microsoft");
        doReturn(sonyConsoles).when(consoleDao).findConsoleByManufacturer("Sony");
    }

}