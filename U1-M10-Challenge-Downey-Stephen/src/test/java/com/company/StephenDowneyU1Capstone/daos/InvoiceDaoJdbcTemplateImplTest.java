package com.company.StephenDowneyU1Capstone.daos;

import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoJdbcTemplateImplTest {

    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    GameDao gameDao;
    @Autowired
    TShirtDao tShirtDao;
    @Autowired
    ConsoleDao consoleDao;
    @Autowired
    SalesTaxDao salesTaxDao;
    @Autowired
    ProcessingFeeDao processingFeeDao;

    @Before
    public void setUp() throws Exception {
        List<Game> games = gameDao.findAllGames();
        for (Game g: games) {
            gameDao.deleteGame(g.getItemId());
        }

        List<TShirt> tShirts = tShirtDao.findAllTShirts();
        for (TShirt t: tShirts) {
            tShirtDao.deleteTShirt(t.getItemId());
        }

        List<Console> consoles = consoleDao.findAllConsoles();
        for (Console c: consoles) {
            consoleDao.deleteConsole(c.getItemId());
        }

        List<Invoice> invoices = invoiceDao.findAllInvoices();
        for (Invoice i: invoices) {
            invoiceDao.deleteInvoice(i.getInvoiceId());
        }
    }

    //This test tests the add function and the getAll function
    @Test
    public void shouldAddAndFindAllInvoices() throws JdbcOperationFailedException {
        //Arrange
        Game testGame1 = new Game();
        testGame1.setTitle("Skyrim");
        testGame1.setDescription("Epic installation in the Elder Scrolls series.");
        testGame1.setEsrbRating("T");
        testGame1.setStudio("Bethesda");
        testGame1.setPrice(new BigDecimal("69.99"));
        testGame1.setQuantity(6);
        testGame1 = gameDao.saveGame(testGame1);

        TShirt testShirt1 = new TShirt();
        testShirt1.setSize("M");
        testShirt1.setColor("Green");
        testShirt1.setDescription("Minecraft Shirt");
        testShirt1.setPrice(new BigDecimal("24.99"));
        testShirt1.setQuantity(6);
        testShirt1 = tShirtDao.saveTShirt(testShirt1);

        Console testConsole1 = new Console();
        testConsole1.setModel("Playstation 5");
        testConsole1.setManufacturer("Sony");
        testConsole1.setMemoryAmount("825GB");
        testConsole1.setProcessor("AMD Zen 2");
        testConsole1.setPrice(new BigDecimal("499.99"));
        testConsole1.setQuantity(0);
        testConsole1 = consoleDao.saveConsole(testConsole1);

        BigDecimal quantity = new BigDecimal("1");
        BigDecimal unitPrice = testGame1.getPrice();
        BigDecimal subtotal = unitPrice.multiply(quantity);

        Invoice testInvoice1 = new Invoice();
        testInvoice1.setName("Jack");
        testInvoice1.setStreet("123 Main St.");
        testInvoice1.setCity("Main City");
        testInvoice1.setState("ME");
        testInvoice1.setZipcode("12345");
        testInvoice1.setItemType("Games");
        testInvoice1.setItemId(testGame1.getItemId());
        testInvoice1.setUnitPrice(unitPrice);
        testInvoice1.setQuantity(quantity.intValue());
        testInvoice1.setSubtotal(subtotal);
        SalesTaxRate salesTaxRate = salesTaxDao.findSalesTaxByState(testInvoice1.getState());
        BigDecimal totalTax = subtotal.multiply(salesTaxRate.getRate()).setScale(2, RoundingMode.HALF_UP);
        testInvoice1.setTax(totalTax);
        ProcessingFee processingFee = processingFeeDao.findProcessingFeeByItemType(testInvoice1.getItemType());
        testInvoice1.setProcessingFee(processingFee.getFee());
        BigDecimal total = subtotal.add(totalTax.add(processingFee.getFee()));
        testInvoice1.setTotal(total);

        quantity = new BigDecimal("2");
        unitPrice = testShirt1.getPrice();
        subtotal = unitPrice.multiply(quantity);

        Invoice testInvoice2 = new Invoice();
        testInvoice2.setName("Jill");
        testInvoice2.setStreet("321 Other Dr.");
        testInvoice2.setCity("Other City");
        testInvoice2.setState("OR");
        testInvoice2.setZipcode("98765");
        testInvoice2.setItemType("T-Shirts");
        testInvoice2.setItemId(testShirt1.getItemId());
        testInvoice2.setUnitPrice(unitPrice);
        testInvoice2.setQuantity(quantity.intValue());
        testInvoice2.setSubtotal(subtotal);
        salesTaxRate = salesTaxDao.findSalesTaxByState(testInvoice2.getState());
        totalTax = subtotal.multiply(salesTaxRate.getRate()).setScale(2, RoundingMode.HALF_UP);
        testInvoice2.setTax(totalTax);
        processingFee = processingFeeDao.findProcessingFeeByItemType(testInvoice2.getItemType());
        testInvoice2.setProcessingFee(processingFee.getFee());
        total = subtotal.add(totalTax.add(processingFee.getFee()));
        testInvoice2.setTotal(total);

        quantity = new BigDecimal("1");
        unitPrice = testConsole1.getPrice();
        subtotal = unitPrice.multiply(quantity);

        Invoice testInvoice3 = new Invoice();
        testInvoice3.setName("Diane");
        testInvoice3.setStreet("456 Another Rd.");
        testInvoice3.setCity("Another City");
        testInvoice3.setState("AR");
        testInvoice3.setZipcode("46525");
        testInvoice3.setItemType("Consoles");
        testInvoice3.setItemId(testConsole1.getItemId());
        testInvoice3.setUnitPrice(unitPrice);
        testInvoice3.setQuantity(quantity.intValue());
        testInvoice3.setSubtotal(subtotal);
        salesTaxRate = salesTaxDao.findSalesTaxByState(testInvoice3.getState());
        totalTax = subtotal.multiply(salesTaxRate.getRate()).setScale(2, RoundingMode.HALF_UP);
        testInvoice3.setTax(totalTax);
        processingFee = processingFeeDao.findProcessingFeeByItemType(testInvoice3.getItemType());
        testInvoice3.setProcessingFee(processingFee.getFee());
        total = subtotal.add(totalTax.add(processingFee.getFee()));
        testInvoice3.setTotal(total);

        //Act
        testInvoice1 = invoiceDao.saveInvoice(testInvoice1);
        List<Invoice> allInvoicesInDatabase = invoiceDao.findAllInvoices();

        //Assert
        assertEquals(1, allInvoicesInDatabase.size());

        //Act
        testInvoice2 = invoiceDao.saveInvoice(testInvoice2);
        allInvoicesInDatabase = invoiceDao.findAllInvoices();

        //Assert
        assertEquals(2, allInvoicesInDatabase.size());

        //Act
        testInvoice3 = invoiceDao.saveInvoice(testInvoice3);
        allInvoicesInDatabase = invoiceDao.findAllInvoices();

        //Assert
        assertEquals(3, allInvoicesInDatabase.size());

        invoiceDao.deleteInvoice(testInvoice1.getInvoiceId());
        invoiceDao.deleteInvoice(testInvoice3.getInvoiceId());

        //Act
        allInvoicesInDatabase = invoiceDao.findAllInvoices();

        //Assert
        assertEquals(1, allInvoicesInDatabase.size());
    }

    @Test
    public void shouldDeleteInvoice() throws JdbcOperationFailedException {
        //Arrange
        Game testGame1 = new Game();
        testGame1.setTitle("Skyrim");
        testGame1.setDescription("Epic installation in the Elder Scrolls series.");
        testGame1.setEsrbRating("T");
        testGame1.setStudio("Bethesda");
        testGame1.setPrice(new BigDecimal("69.99"));
        testGame1.setQuantity(6);
        testGame1 = gameDao.saveGame(testGame1);

        TShirt testShirt1 = new TShirt();
        testShirt1.setSize("M");
        testShirt1.setColor("Green");
        testShirt1.setDescription("Minecraft Shirt");
        testShirt1.setPrice(new BigDecimal("24.99"));
        testShirt1.setQuantity(6);
        testShirt1 = tShirtDao.saveTShirt(testShirt1);

        Console testConsole1 = new Console();
        testConsole1.setModel("Playstation 5");
        testConsole1.setManufacturer("Sony");
        testConsole1.setMemoryAmount("825GB");
        testConsole1.setProcessor("AMD Zen 2");
        testConsole1.setPrice(new BigDecimal("499.99"));
        testConsole1.setQuantity(0);
        testConsole1 = consoleDao.saveConsole(testConsole1);

        BigDecimal quantity = new BigDecimal("1");
        BigDecimal unitPrice = testGame1.getPrice();
        BigDecimal subtotal = unitPrice.multiply(quantity);

        Invoice testInvoice1 = new Invoice();
        testInvoice1.setName("Jack");
        testInvoice1.setStreet("123 Main St.");
        testInvoice1.setCity("Main City");
        testInvoice1.setState("ME");
        testInvoice1.setZipcode("12345");
        testInvoice1.setItemType("Games");
        testInvoice1.setItemId(testGame1.getItemId());
        testInvoice1.setUnitPrice(unitPrice);
        testInvoice1.setQuantity(quantity.intValue());
        testInvoice1.setSubtotal(subtotal);
        SalesTaxRate salesTaxRate = salesTaxDao.findSalesTaxByState(testInvoice1.getState());
        BigDecimal totalTax = subtotal.multiply(salesTaxRate.getRate()).setScale(2, RoundingMode.HALF_UP);
        testInvoice1.setTax(totalTax);
        ProcessingFee processingFee = processingFeeDao.findProcessingFeeByItemType(testInvoice1.getItemType());
        testInvoice1.setProcessingFee(processingFee.getFee());
        BigDecimal total = subtotal.add(totalTax.add(processingFee.getFee()));
        testInvoice1.setTotal(total);

        quantity = new BigDecimal("2");
        unitPrice = testShirt1.getPrice();
        subtotal = unitPrice.multiply(quantity);

        Invoice testInvoice2 = new Invoice();
        testInvoice2.setName("Jill");
        testInvoice2.setStreet("321 Other Dr.");
        testInvoice2.setCity("Other City");
        testInvoice2.setState("OR");
        testInvoice2.setZipcode("98765");
        testInvoice2.setItemType("T-Shirts");
        testInvoice2.setItemId(testShirt1.getItemId());
        testInvoice2.setUnitPrice(unitPrice);
        testInvoice2.setQuantity(quantity.intValue());
        testInvoice2.setSubtotal(subtotal);
        salesTaxRate = salesTaxDao.findSalesTaxByState(testInvoice2.getState());
        totalTax = subtotal.multiply(salesTaxRate.getRate()).setScale(2, RoundingMode.HALF_UP);
        testInvoice2.setTax(totalTax);
        processingFee = processingFeeDao.findProcessingFeeByItemType(testInvoice2.getItemType());
        testInvoice2.setProcessingFee(processingFee.getFee());
        total = subtotal.add(totalTax.add(processingFee.getFee()));
        testInvoice2.setTotal(total);

        quantity = new BigDecimal("1");
        unitPrice = testConsole1.getPrice();
        subtotal = unitPrice.multiply(quantity);

        Invoice testInvoice3 = new Invoice();
        testInvoice3.setName("Diane");
        testInvoice3.setStreet("456 Another Rd.");
        testInvoice3.setCity("Another City");
        testInvoice3.setState("AR");
        testInvoice3.setZipcode("46525");
        testInvoice3.setItemType("Consoles");
        testInvoice3.setItemId(testConsole1.getItemId());
        testInvoice3.setUnitPrice(unitPrice);
        testInvoice3.setQuantity(quantity.intValue());
        testInvoice3.setSubtotal(subtotal);
        salesTaxRate = salesTaxDao.findSalesTaxByState(testInvoice3.getState());
        totalTax = subtotal.multiply(salesTaxRate.getRate()).setScale(2, RoundingMode.HALF_UP);
        testInvoice3.setTax(totalTax);
        processingFee = processingFeeDao.findProcessingFeeByItemType(testInvoice3.getItemType());
        testInvoice3.setProcessingFee(processingFee.getFee());
        total = subtotal.add(totalTax.add(processingFee.getFee()));
        testInvoice3.setTotal(total);

        testInvoice1 = invoiceDao.saveInvoice(testInvoice1);
        testInvoice2 = invoiceDao.saveInvoice(testInvoice2);
        testInvoice3 = invoiceDao.saveInvoice(testInvoice3);

        //the purpose of these two lines is to establish that their are 3 consoles in the database before deleting
        List<Invoice> allInvoicesInDatabase = invoiceDao.findAllInvoices();
        assertEquals(3, allInvoicesInDatabase.size());


        //Act
        invoiceDao.deleteInvoice(testInvoice1.getInvoiceId());

        allInvoicesInDatabase = invoiceDao.findAllInvoices();

        //Assert
        assertEquals(2, allInvoicesInDatabase.size());

        //Act
        invoiceDao.deleteInvoice(testInvoice3.getInvoiceId());

        allInvoicesInDatabase = invoiceDao.findAllInvoices();

        //Assert
        assertEquals(1, allInvoicesInDatabase.size());
        assertEquals(testInvoice2, allInvoicesInDatabase.get(0));

        //Act
        invoiceDao.deleteInvoice(testInvoice2.getInvoiceId());

        allInvoicesInDatabase = invoiceDao.findAllInvoices();

        //Assert
        assertEquals(0, allInvoicesInDatabase.size());
    }
}