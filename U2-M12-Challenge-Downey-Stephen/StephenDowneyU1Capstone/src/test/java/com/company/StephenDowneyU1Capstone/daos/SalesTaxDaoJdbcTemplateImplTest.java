package com.company.StephenDowneyU1Capstone.daos;

import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.SalesTaxRate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SalesTaxDaoJdbcTemplateImplTest {

    @Autowired
    SalesTaxDao salesTaxDao;

    @Test
    public void findSalesTaxByState() throws JdbcOperationFailedException {
        //Arrange
        Map<String, BigDecimal> expectedTaxRates = new HashMap<>();
        expectedTaxRates.put("AL", new BigDecimal(".05"));
        expectedTaxRates.put("AK", new BigDecimal(".06"));
        expectedTaxRates.put("AZ", new BigDecimal(".04"));
        expectedTaxRates.put("AR", new BigDecimal(".06"));
        expectedTaxRates.put("CA", new BigDecimal(".06"));
        expectedTaxRates.put("CO", new BigDecimal(".04"));
        expectedTaxRates.put("CT", new BigDecimal(".03"));
        expectedTaxRates.put("DE", new BigDecimal(".05"));
        expectedTaxRates.put("FL", new BigDecimal(".06"));
        expectedTaxRates.put("GA", new BigDecimal(".07"));
        expectedTaxRates.put("HI", new BigDecimal(".05"));
        expectedTaxRates.put("ID", new BigDecimal(".03"));
        expectedTaxRates.put("IL", new BigDecimal(".05"));
        expectedTaxRates.put("IN", new BigDecimal(".05"));
        expectedTaxRates.put("IA", new BigDecimal(".04"));
        expectedTaxRates.put("KS", new BigDecimal(".06"));
        expectedTaxRates.put("KY", new BigDecimal(".04"));
        expectedTaxRates.put("LA", new BigDecimal(".05"));
        expectedTaxRates.put("ME", new BigDecimal(".03"));
        expectedTaxRates.put("MD", new BigDecimal(".07"));
        expectedTaxRates.put("MA", new BigDecimal(".05"));
        expectedTaxRates.put("MI", new BigDecimal(".06"));
        expectedTaxRates.put("MN", new BigDecimal(".06"));
        expectedTaxRates.put("MS", new BigDecimal(".05"));
        expectedTaxRates.put("MO", new BigDecimal(".05"));
        expectedTaxRates.put("MT", new BigDecimal(".03"));
        expectedTaxRates.put("NE", new BigDecimal(".04"));
        expectedTaxRates.put("NV", new BigDecimal(".04"));
        expectedTaxRates.put("NH", new BigDecimal(".06"));
        expectedTaxRates.put("NJ", new BigDecimal(".05"));
        expectedTaxRates.put("NM", new BigDecimal(".05"));
        expectedTaxRates.put("NY", new BigDecimal(".06"));
        expectedTaxRates.put("NC", new BigDecimal(".05"));
        expectedTaxRates.put("ND", new BigDecimal(".05"));
        expectedTaxRates.put("OH", new BigDecimal(".04"));
        expectedTaxRates.put("OK", new BigDecimal(".04"));
        expectedTaxRates.put("OR", new BigDecimal(".07"));
        expectedTaxRates.put("PA", new BigDecimal(".06"));
        expectedTaxRates.put("RI", new BigDecimal(".06"));
        expectedTaxRates.put("SC", new BigDecimal(".06"));
        expectedTaxRates.put("SD", new BigDecimal(".06"));
        expectedTaxRates.put("TN", new BigDecimal(".05"));
        expectedTaxRates.put("TX", new BigDecimal(".03"));
        expectedTaxRates.put("UT", new BigDecimal(".04"));
        expectedTaxRates.put("VT", new BigDecimal(".07"));
        expectedTaxRates.put("VA", new BigDecimal(".06"));
        expectedTaxRates.put("WA", new BigDecimal(".05"));
        expectedTaxRates.put("WV", new BigDecimal(".05"));
        expectedTaxRates.put("WI", new BigDecimal(".03"));
        expectedTaxRates.put("WY", new BigDecimal(".04"));

        Set<String> states = expectedTaxRates.keySet();
        Map<String, BigDecimal> actualRatesReturnedFromDB = new HashMap<>();

        //Act
        for (String state: states) {
            SalesTaxRate salesTaxRate = salesTaxDao.findSalesTaxByState(state);
            actualRatesReturnedFromDB.put(salesTaxRate.getState(), salesTaxRate.getRate());
        }

        //Assert
        for (String state: states) {
            assertEquals(expectedTaxRates.get(state), actualRatesReturnedFromDB.get(state));
        }
    }

    @Test
    public void shouldThrowJdbcOperationFailedExceptionWhenAnInvalidIdIsPassedToFindById(){
        //Arrange
        String test1 = "AX";
        String test2 = "GO";
        String test3 = "DO";

        //Act and Assert
        assertThrows(JdbcOperationFailedException.class, () -> {
            salesTaxDao.findSalesTaxByState(test1);
        });

        assertThrows(JdbcOperationFailedException.class, () -> {
            salesTaxDao.findSalesTaxByState(test2);
        });

        assertThrows(JdbcOperationFailedException.class, () -> {
            salesTaxDao.findSalesTaxByState(test3);
        });
    }
}