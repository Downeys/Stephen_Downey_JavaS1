package com.company.StephenDowneyU1Capstone.daos;

import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.TShirt;
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
public class TShirtDaoJdbcTemplateImplTest {

    @Autowired
    TShirtDao tShirtDao;

    @Before
    public void setUp() throws Exception {
        List<TShirt> tShirts = tShirtDao.findAllTShirts();
        for (TShirt t: tShirts) {
            tShirtDao.deleteTShirt(t.getItemId());
        }
    }

    @Test
    public void shouldAddGetDeleteTShirt() throws JdbcOperationFailedException {
        //Arrange
        TShirt testShirt1 = new TShirt();
        testShirt1.setSize("M");
        testShirt1.setColor("Green");
        testShirt1.setDescription("Minecraft Shirt");
        testShirt1.setPrice(new BigDecimal("24.99"));
        testShirt1.setQuantity(6);


        //Act
        testShirt1 = tShirtDao.saveTShirt(testShirt1);
        TShirt tShirtReceivedBackFromDatabase = tShirtDao.findTShirt(testShirt1.getItemId());
        List<TShirt> allTShirtsInDatabase = tShirtDao.findAllTShirts();

        //Assert
        assertEquals(1, allTShirtsInDatabase.size());
        assertEquals(testShirt1, tShirtReceivedBackFromDatabase);

        //Act
        tShirtDao.deleteTShirt(testShirt1.getItemId());
        allTShirtsInDatabase = tShirtDao.findAllTShirts();

        //Assert
        assertEquals(0, allTShirtsInDatabase.size());
    }

    @Test
    public void shouldFindTShirtByColor() {
        //Arrange
        TShirt testShirt1 = new TShirt();
        testShirt1.setSize("M");
        testShirt1.setColor("Green");
        testShirt1.setDescription("Minecraft Shirt");
        testShirt1.setPrice(new BigDecimal("24.99"));
        testShirt1.setQuantity(6);

        TShirt testShirt2 = new TShirt();
        testShirt2.setSize("XL");
        testShirt2.setColor("Black");
        testShirt2.setDescription("Minecraft Shirt");
        testShirt2.setPrice(new BigDecimal("29.99"));
        testShirt2.setQuantity(12);

        TShirt testShirt3 = new TShirt();
        testShirt3.setSize("M");
        testShirt3.setColor("Black");
        testShirt3.setDescription("COD Shirt");
        testShirt3.setPrice(new BigDecimal("24.99"));
        testShirt3.setQuantity(1);

        testShirt1 = tShirtDao.saveTShirt(testShirt1);
        testShirt2 = tShirtDao.saveTShirt(testShirt2);
        testShirt3 = tShirtDao.saveTShirt(testShirt3);

        //Act
        List<TShirt> blackTShirts = tShirtDao.findTShirtByColor("Black");
        List<TShirt> greenTShirts = tShirtDao.findTShirtByColor("Green");

        //Assert
        assertEquals(2, blackTShirts.size());
        assertEquals("Black", blackTShirts.get(0).getColor());
        assertEquals("Black", blackTShirts.get(1).getColor());

        assertEquals(1, greenTShirts.size());
        assertEquals("Green", greenTShirts.get(0).getColor());
    }

    @Test
    public void shouldFindTShirtBySize() {
        //Arrange
        TShirt testShirt1 = new TShirt();
        testShirt1.setSize("M");
        testShirt1.setColor("Green");
        testShirt1.setDescription("Minecraft Shirt");
        testShirt1.setPrice(new BigDecimal("24.99"));
        testShirt1.setQuantity(6);

        TShirt testShirt2 = new TShirt();
        testShirt2.setSize("XL");
        testShirt2.setColor("Black");
        testShirt2.setDescription("Minecraft Shirt");
        testShirt2.setPrice(new BigDecimal("29.99"));
        testShirt2.setQuantity(12);

        TShirt testShirt3 = new TShirt();
        testShirt3.setSize("M");
        testShirt3.setColor("Black");
        testShirt3.setDescription("COD Shirt");
        testShirt3.setPrice(new BigDecimal("24.99"));
        testShirt3.setQuantity(1);

        testShirt1 = tShirtDao.saveTShirt(testShirt1);
        testShirt2 = tShirtDao.saveTShirt(testShirt2);
        testShirt3 = tShirtDao.saveTShirt(testShirt3);

        //Act
        List<TShirt> mediumTShirts = tShirtDao.findTShirtBySize("M");
        List<TShirt> xlargeTShirts = tShirtDao.findTShirtBySize("XL");

        //Assert
        assertEquals(2, mediumTShirts.size());
        assertEquals("M", mediumTShirts.get(0).getSize());
        assertEquals("M", mediumTShirts.get(1).getSize());

        assertEquals(1, xlargeTShirts.size());
        assertEquals("XL", xlargeTShirts.get(0).getSize());
    }

    @Test
    public void shouldFindAllTShirts() throws JdbcOperationFailedException{
        //Arrange
        TShirt testShirt1 = new TShirt();
        testShirt1.setSize("M");
        testShirt1.setColor("Green");
        testShirt1.setDescription("Minecraft Shirt");
        testShirt1.setPrice(new BigDecimal("24.99"));
        testShirt1.setQuantity(6);

        TShirt testShirt2 = new TShirt();
        testShirt2.setSize("XL");
        testShirt2.setColor("Black");
        testShirt2.setDescription("Minecraft Shirt");
        testShirt2.setPrice(new BigDecimal("29.99"));
        testShirt2.setQuantity(12);

        TShirt testShirt3 = new TShirt();
        testShirt3.setSize("M");
        testShirt3.setColor("Black");
        testShirt3.setDescription("COD Shirt");
        testShirt3.setPrice(new BigDecimal("24.99"));
        testShirt3.setQuantity(1);

        testShirt1 = tShirtDao.saveTShirt(testShirt1);

        //Act
        List<TShirt> allTShirtsInDatabase = tShirtDao.findAllTShirts();

        //Assert
        assertEquals(1, allTShirtsInDatabase.size());

        testShirt2 = tShirtDao.saveTShirt(testShirt2);

        //Act
        allTShirtsInDatabase = tShirtDao.findAllTShirts();

        //Assert
        assertEquals(2, allTShirtsInDatabase.size());

        testShirt3 = tShirtDao.saveTShirt(testShirt3);

        //Act
        allTShirtsInDatabase = tShirtDao.findAllTShirts();

        //Assert
        assertEquals(3, allTShirtsInDatabase.size());

        tShirtDao.deleteTShirt(testShirt1.getItemId());
        tShirtDao.deleteTShirt(testShirt3.getItemId());

        //Act
        allTShirtsInDatabase = tShirtDao.findAllTShirts();

        //Assert
        assertEquals(1, allTShirtsInDatabase.size());
        assertEquals(testShirt2, allTShirtsInDatabase.get(0));
    }

    @Test
    public void shouldUpdateTShirt() throws JdbcOperationFailedException {
        //Arrange
        TShirt testShirt1 = new TShirt();
        testShirt1.setSize("M");
        testShirt1.setColor("Green");
        testShirt1.setDescription("Minecraft Shirt");
        testShirt1.setPrice(new BigDecimal("24.99"));
        testShirt1.setQuantity(6);
        testShirt1 = tShirtDao.saveTShirt(testShirt1);

        TShirt updatedTShirt = tShirtDao.findTShirt(testShirt1.getItemId());
        updatedTShirt.setSize("L");
        updatedTShirt.setPrice(new BigDecimal("27.99"));
        updatedTShirt.setQuantity(5);

        //Act
        tShirtDao.updateTShirt(updatedTShirt);

        TShirt tShirtReturnedFromDatabase = tShirtDao.findTShirt(testShirt1.getItemId());

        //Assert
        assertEquals(updatedTShirt, tShirtReturnedFromDatabase);
        assertNotEquals(testShirt1, tShirtReturnedFromDatabase);
    }

    @Test
    public void shouldDeleteTShirt() throws JdbcOperationFailedException{
        //Arrange
        TShirt testShirt1 = new TShirt();
        testShirt1.setSize("M");
        testShirt1.setColor("Green");
        testShirt1.setDescription("Minecraft Shirt");
        testShirt1.setPrice(new BigDecimal("24.99"));
        testShirt1.setQuantity(6);

        TShirt testShirt2 = new TShirt();
        testShirt2.setSize("XL");
        testShirt2.setColor("Black");
        testShirt2.setDescription("Minecraft Shirt");
        testShirt2.setPrice(new BigDecimal("29.99"));
        testShirt2.setQuantity(12);

        TShirt testShirt3 = new TShirt();
        testShirt3.setSize("M");
        testShirt3.setColor("Black");
        testShirt3.setDescription("COD Shirt");
        testShirt3.setPrice(new BigDecimal("24.99"));
        testShirt3.setQuantity(1);

        testShirt1 = tShirtDao.saveTShirt(testShirt1);
        testShirt2 = tShirtDao.saveTShirt(testShirt2);
        testShirt3 = tShirtDao.saveTShirt(testShirt3);

        //the purpose of these two lines is to establish that their are 3 TShirts in the database before deleting
        List<TShirt> allTShirtsInDatabase = tShirtDao.findAllTShirts();
        assertEquals(3, allTShirtsInDatabase.size());

        //Act
        tShirtDao.deleteTShirt(testShirt2.getItemId());

        allTShirtsInDatabase = tShirtDao.findAllTShirts();

        //Assert
        assertEquals(2, allTShirtsInDatabase.size());

        //Act
        tShirtDao.deleteTShirt(testShirt1.getItemId());

        allTShirtsInDatabase = tShirtDao.findAllTShirts();

        //Assert
        assertEquals(1, allTShirtsInDatabase.size());
        assertEquals(testShirt3, allTShirtsInDatabase.get(0));

        //Act
        tShirtDao.deleteTShirt(testShirt3.getItemId());

        allTShirtsInDatabase = tShirtDao.findAllTShirts();

        //Assert
        assertEquals(0, allTShirtsInDatabase.size());
    }

    @Test
    public void shouldThrowJdbcOperationFailedExceptionWhenAnInvalidItemTypeIsPassedToFindByItemType(){
        //Arrange
        TShirt testShirt1 = new TShirt();
        testShirt1.setSize("M");
        testShirt1.setColor("Green");
        testShirt1.setDescription("Minecraft Shirt");
        testShirt1.setPrice(new BigDecimal("24.99"));
        testShirt1.setQuantity(6);

        TShirt testShirt2 = new TShirt();
        testShirt2.setSize("XL");
        testShirt2.setColor("Black");
        testShirt2.setDescription("Minecraft Shirt");
        testShirt2.setPrice(new BigDecimal("29.99"));
        testShirt2.setQuantity(12);

        TShirt testShirt3 = new TShirt();
        testShirt3.setSize("M");
        testShirt3.setColor("Black");
        testShirt3.setDescription("COD Shirt");
        testShirt3.setPrice(new BigDecimal("24.99"));
        testShirt3.setQuantity(1);

        testShirt1 = tShirtDao.saveTShirt(testShirt1);
        testShirt2 = tShirtDao.saveTShirt(testShirt2);

        //Act and Assert
        assertThrows(JdbcOperationFailedException.class, () -> {
            tShirtDao.findTShirt(35);
        });

        assertThrows(JdbcOperationFailedException.class, () -> {
            tShirtDao.deleteTShirt(35);
        });

        assertThrows(JdbcOperationFailedException.class, () -> {
            tShirtDao.updateTShirt(testShirt3);
        });
    }
}