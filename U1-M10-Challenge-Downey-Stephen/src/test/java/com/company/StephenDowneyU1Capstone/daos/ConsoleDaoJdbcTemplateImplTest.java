package com.company.StephenDowneyU1Capstone.daos;

import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.Console;
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
public class ConsoleDaoJdbcTemplateImplTest {

    @Autowired
    ConsoleDao consoleDao;

    @Before
    public void setUp() throws Exception {
        List<Console> consoles = consoleDao.findAllConsoles();
        for (Console c: consoles) {
            consoleDao.deleteConsole(c.getItemId());
        }
    }

    @Test
    public void shouldAddGetDeleteConsole() throws JdbcOperationFailedException {
        //Arrange
        Console testConsole1 = new Console();
        testConsole1.setModel("Playstation 5");
        testConsole1.setManufacturer("Sony");
        testConsole1.setMemoryAmount("825GB");
        testConsole1.setProcessor("AMD Zen 2");
        testConsole1.setPrice(new BigDecimal("499.99"));
        testConsole1.setQuantity(0);

        //Act
        testConsole1 = consoleDao.saveConsole(testConsole1);
        Console consoleReceivedBackFromDatabase = consoleDao.findConsole(testConsole1.getItemId());
        List<Console> allConsolesInDatabase = consoleDao.findAllConsoles();

        //Assert
        assertEquals(1, allConsolesInDatabase.size());
        assertEquals(testConsole1, consoleReceivedBackFromDatabase);

        //Act
        consoleDao.deleteConsole(testConsole1.getItemId());
        allConsolesInDatabase = consoleDao.findAllConsoles();

        //Assert
        assertEquals(0, allConsolesInDatabase.size());
    }

    @Test
    public void shouldFindConsoleByManufacturer() {
        //Arrange
        Console testConsole1 = new Console();
        testConsole1.setModel("Playstation 5");
        testConsole1.setManufacturer("Sony");
        testConsole1.setMemoryAmount("825GB");
        testConsole1.setProcessor("AMD Zen 2");
        testConsole1.setPrice(new BigDecimal("499.99"));
        testConsole1.setQuantity(0);

        Console testConsole2 = new Console();
        testConsole2.setModel("XBox Series X");
        testConsole2.setManufacturer("Microsoft");
        testConsole2.setMemoryAmount("1TB");
        testConsole2.setProcessor("AMD Zen 2");
        testConsole2.setPrice(new BigDecimal("499.99"));
        testConsole2.setQuantity(5);

        Console testConsole3 = new Console();
        testConsole3.setModel("XBox Series S");
        testConsole3.setManufacturer("Microsoft");
        testConsole3.setMemoryAmount("512GB");
        testConsole3.setProcessor("AMD Zen 2");
        testConsole3.setPrice(new BigDecimal("299.99"));
        testConsole3.setQuantity(11);

        testConsole1 = consoleDao.saveConsole(testConsole1);
        testConsole2 = consoleDao.saveConsole(testConsole2);
        testConsole3 = consoleDao.saveConsole(testConsole3);

        //Act
        List<Console> microsoftConsoles = consoleDao.findConsoleByManufacturer("Microsoft");
        List<Console> sonyConsoles = consoleDao.findConsoleByManufacturer("Sony");

        //Assert
        assertEquals(2, microsoftConsoles.size());
        assertEquals("Microsoft", microsoftConsoles.get(0).getManufacturer());
        assertEquals("Microsoft", microsoftConsoles.get(1).getManufacturer());

        assertEquals(1, sonyConsoles.size());
        assertEquals("Sony", sonyConsoles.get(0).getManufacturer());

    }

    @Test
    public void shouldFindAllConsoles() throws JdbcOperationFailedException{
        //Arrange
        Console testConsole1 = new Console();
        testConsole1.setModel("Playstation 5");
        testConsole1.setManufacturer("Sony");
        testConsole1.setMemoryAmount("825GB");
        testConsole1.setProcessor("AMD Zen 2");
        testConsole1.setPrice(new BigDecimal("499.99"));
        testConsole1.setQuantity(0);

        Console testConsole2 = new Console();
        testConsole2.setModel("XBox Series X");
        testConsole2.setManufacturer("Microsoft");
        testConsole2.setMemoryAmount("1TB");
        testConsole2.setProcessor("AMD Zen 2");
        testConsole2.setPrice(new BigDecimal("499.99"));
        testConsole2.setQuantity(5);

        Console testConsole3 = new Console();
        testConsole3.setModel("XBox Series S");
        testConsole3.setManufacturer("Microsoft");
        testConsole3.setMemoryAmount("512GB");
        testConsole3.setProcessor("AMD Zen 2");
        testConsole3.setPrice(new BigDecimal("299.99"));
        testConsole3.setQuantity(11);

        testConsole1 = consoleDao.saveConsole(testConsole1);

        //Act
        List<Console> allConsolesInDatabase = consoleDao.findAllConsoles();

        //Assert
        assertEquals(1, allConsolesInDatabase.size());

        testConsole2 = consoleDao.saveConsole(testConsole2);

        //Act
        allConsolesInDatabase = consoleDao.findAllConsoles();

        //Assert
        assertEquals(2, allConsolesInDatabase.size());

        testConsole3 = consoleDao.saveConsole(testConsole3);

        //Act
        allConsolesInDatabase = consoleDao.findAllConsoles();

        //Assert
        assertEquals(3, allConsolesInDatabase.size());

        consoleDao.deleteConsole(testConsole1.getItemId());
        consoleDao.deleteConsole(testConsole3.getItemId());

        //Act
        allConsolesInDatabase = consoleDao.findAllConsoles();

        //Assert
        assertEquals(1, allConsolesInDatabase.size());
        assertEquals(testConsole2, allConsolesInDatabase.get(0));
    }

    @Test
    public void shouldUpdateConsole() throws JdbcOperationFailedException {
        //Arrange
        Console testConsole1 = new Console();
        testConsole1.setModel("Playstation 5");
        testConsole1.setManufacturer("Sony");
        testConsole1.setMemoryAmount("825GB");
        testConsole1.setProcessor("AMD Zen 2");
        testConsole1.setPrice(new BigDecimal("499.99"));
        testConsole1.setQuantity(0);

        testConsole1 = consoleDao.saveConsole(testConsole1);
        Console updatedTestConsole = consoleDao.findConsole(testConsole1.getItemId());
        updatedTestConsole.setMemoryAmount("1TB");
        updatedTestConsole.setQuantity(5);
        updatedTestConsole.setPrice(new BigDecimal("449.99"));

        //Act
        consoleDao.updateConsole(updatedTestConsole);

        Console consoleReturnedFromDatabase = consoleDao.findConsole(testConsole1.getItemId());

        //Assert
        assertEquals(updatedTestConsole, consoleReturnedFromDatabase);
        assertNotEquals(testConsole1, consoleReturnedFromDatabase);
    }

    @Test
    public void shouldDeleteConsole() throws JdbcOperationFailedException{
        //Arrange
        Console testConsole1 = new Console();
        testConsole1.setModel("Playstation 5");
        testConsole1.setManufacturer("Sony");
        testConsole1.setMemoryAmount("825GB");
        testConsole1.setProcessor("AMD Zen 2");
        testConsole1.setPrice(new BigDecimal("499.99"));
        testConsole1.setQuantity(0);

        Console testConsole2 = new Console();
        testConsole2.setModel("XBox Series X");
        testConsole2.setManufacturer("Microsoft");
        testConsole2.setMemoryAmount("1TB");
        testConsole2.setProcessor("AMD Zen 2");
        testConsole2.setPrice(new BigDecimal("499.99"));
        testConsole2.setQuantity(5);

        Console testConsole3 = new Console();
        testConsole3.setModel("XBox Series S");
        testConsole3.setManufacturer("Microsoft");
        testConsole3.setMemoryAmount("512GB");
        testConsole3.setProcessor("AMD Zen 2");
        testConsole3.setPrice(new BigDecimal("299.99"));
        testConsole3.setQuantity(11);

        testConsole1 = consoleDao.saveConsole(testConsole1);
        testConsole2 = consoleDao.saveConsole(testConsole2);
        testConsole3 = consoleDao.saveConsole(testConsole3);

        //the purpose of these two lines is to establish that their are 3 consoles in the database before deleting
        List<Console> allConsolesInDatabase = consoleDao.findAllConsoles();
        assertEquals(3, allConsolesInDatabase.size());

        //Act
        consoleDao.deleteConsole(testConsole2.getItemId());

        allConsolesInDatabase = consoleDao.findAllConsoles();

        //Assert
        assertEquals(2, allConsolesInDatabase.size());

        //Act
        consoleDao.deleteConsole(testConsole1.getItemId());

        allConsolesInDatabase = consoleDao.findAllConsoles();

        //Assert
        assertEquals(1, allConsolesInDatabase.size());
        assertEquals(testConsole3, allConsolesInDatabase.get(0));

        //Act
        consoleDao.deleteConsole(testConsole3.getItemId());

        allConsolesInDatabase = consoleDao.findAllConsoles();

        //Assert
        assertEquals(0, allConsolesInDatabase.size());
    }

    @Test
    public void shouldThrowJdbcOperationFailedExceptionWhenAnInvalidIdIsPassedToDeleteOrFindByIdOrInvalidConsoleIsPassedToUpdate(){
        //Arrange
        Console testConsole1 = new Console();
        testConsole1.setModel("Playstation 5");
        testConsole1.setManufacturer("Sony");
        testConsole1.setMemoryAmount("825GB");
        testConsole1.setProcessor("AMD Zen 2");
        testConsole1.setPrice(new BigDecimal("499.99"));
        testConsole1.setQuantity(0);

        Console testConsole2 = new Console();
        testConsole2.setModel("XBox Series X");
        testConsole2.setManufacturer("Microsoft");
        testConsole2.setMemoryAmount("1TB");
        testConsole2.setProcessor("AMD Zen 2");
        testConsole2.setPrice(new BigDecimal("499.99"));
        testConsole2.setQuantity(5);

        Console testConsole3 = new Console();
        testConsole3.setModel("XBox Series S");
        testConsole3.setManufacturer("Microsoft");
        testConsole3.setMemoryAmount("512GB");
        testConsole3.setProcessor("AMD Zen 2");
        testConsole3.setPrice(new BigDecimal("299.99"));
        testConsole3.setQuantity(11);

        testConsole1 = consoleDao.saveConsole(testConsole1);
        testConsole2 = consoleDao.saveConsole(testConsole2);

        assertThrows(JdbcOperationFailedException.class, () -> {
            consoleDao.findConsole(35);
        });

        assertThrows(JdbcOperationFailedException.class, () -> {
            consoleDao.deleteConsole(35);
        });

        assertThrows(JdbcOperationFailedException.class, () -> {
            consoleDao.updateConsole(testConsole3);
        });
    }
}