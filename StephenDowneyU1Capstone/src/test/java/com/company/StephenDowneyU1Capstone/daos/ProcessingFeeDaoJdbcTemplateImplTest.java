package com.company.StephenDowneyU1Capstone.daos;

import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.ProcessingFee;
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
public class ProcessingFeeDaoJdbcTemplateImplTest {

    @Autowired
    ProcessingFeeDao processingFeeDao;

    @Test
    public void shouldFindProcessingFeeByItemType() throws JdbcOperationFailedException {
        //Arrange
        Map<String, BigDecimal> expectedProcessingFees = new HashMap<>();
        expectedProcessingFees.put("Consoles", new BigDecimal("14.99"));
        expectedProcessingFees.put("T-Shirts", new BigDecimal("1.98"));
        expectedProcessingFees.put("Games", new BigDecimal("1.49"));

        Set<String> types = expectedProcessingFees.keySet();
        Map<String, BigDecimal> actualFeesReturnedFromDB = new HashMap<>();

        //Act
        for (String type: types) {
            ProcessingFee processingFee = processingFeeDao.findProcessingFeeByItemType(type);
            actualFeesReturnedFromDB.put(processingFee.getProductType(), processingFee.getFee());
        }

        //Assert
        for (String type: types) {
            assertEquals(expectedProcessingFees.get(type), actualFeesReturnedFromDB.get(type));
        }
    }

    @Test
    public void shouldThrowJdbcOperationFailedExceptionWhenAnInvalidIdIsPassedToFindById(){
        //Arrange
        String test1 = "Guitars";
        String test2 = "Lava Lamps";
        String test3 = "Hot Dogs";

        //Act and Assert
        assertThrows(JdbcOperationFailedException.class, () -> {
            processingFeeDao.findProcessingFeeByItemType(test1);
        });

        assertThrows(JdbcOperationFailedException.class, () -> {
            processingFeeDao.findProcessingFeeByItemType(test2);
        });

        assertThrows(JdbcOperationFailedException.class, () -> {
            processingFeeDao.findProcessingFeeByItemType(test3);
        });
    }
}