package com.company.StephenDowneyU1Capstone.controllers;

import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.Console;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceLayer service;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        setupServiceLayerMock();
    }

    @Test
    public void shouldAddConsoleAndReturnStatusCreated() throws Exception {
        //Arrange
        Console inputConsole1 = new Console();
        inputConsole1.setModel("Playstation 5");
        inputConsole1.setManufacturer("Sony");
        inputConsole1.setMemoryAmount("825GB");
        inputConsole1.setProcessor("AMD Zen 2");
        inputConsole1.setPrice(new BigDecimal("499.99"));
        inputConsole1.setQuantity(0);

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
        outputConsole1.setQuantity(0);

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

        String inputJsonStringForTest1 = mapper.writeValueAsString(inputConsole1);
        String inputJsonStringForTest2 = mapper.writeValueAsString(inputConsole2);
        String inputJsonStringForTest3 = mapper.writeValueAsString(inputConsole3);

        String expectedOutPutStringFromTest1 = mapper.writeValueAsString(outputConsole1);
        String expectedOutPutStringFromTest2 = mapper.writeValueAsString(outputConsole2);
        String expectedOutPutStringFromTest3 = mapper.writeValueAsString(outputConsole3);

        //Act and Assert
        mockMvc.perform(
                post("/console")
                        .content(inputJsonStringForTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedOutPutStringFromTest1));

        mockMvc.perform(
                post("/console")
                        .content(inputJsonStringForTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedOutPutStringFromTest2));

        mockMvc.perform(
                post("/console")
                        .content(inputJsonStringForTest3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedOutPutStringFromTest3));
    }

    @Test
    public void shouldGetConsoleByIdAndReturnStatusOk() throws Exception {
        //Arrange
        String test1 = "/console/1";
        String test2 = "/console/2";
        String test3 = "/console/3";

        Console outputConsole1 = new Console();
        outputConsole1.setItemId(1);
        outputConsole1.setModel("Playstation 5");
        outputConsole1.setManufacturer("Sony");
        outputConsole1.setMemoryAmount("825GB");
        outputConsole1.setProcessor("AMD Zen 2");
        outputConsole1.setPrice(new BigDecimal("499.99"));
        outputConsole1.setQuantity(0);

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

        String expectedOutputForTest1 = mapper.writeValueAsString(outputConsole1);
        String expectedOutputForTest2 = mapper.writeValueAsString(outputConsole2);
        String expectedOutputForTest3 = mapper.writeValueAsString(outputConsole3);

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
    public void shouldReturnNotFoundStatusWhenInvalidConsoleIdIsPassedToUpdateDeleteOrGetByIdEndpoints() throws Exception {
        //Arrange
        String test1 = "/console/4";
        String test2 = "/console/6";

        Console invalidUpdateCustomer = new Console();
        invalidUpdateCustomer.setItemId(5);
        invalidUpdateCustomer.setModel("XBox Series S");
        invalidUpdateCustomer.setManufacturer("Microsoft");
        invalidUpdateCustomer.setMemoryAmount("512GB");
        invalidUpdateCustomer.setProcessor("AMD Zen 2");
        invalidUpdateCustomer.setPrice(new BigDecimal("299.99"));
        invalidUpdateCustomer.setQuantity(11);

        String test3 = mapper.writeValueAsString(invalidUpdateCustomer);

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
                put("/console")
                        .content(test3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    public void shouldGetConsoleByManufacturerAndReturnStatusOk() throws Exception {
        //Arrange
        String test1 = "/console?manufacturer=Microsoft";
        String test2 = "/console?manufacturer=Sony";

        Console outputConsole1 = new Console();
        outputConsole1.setItemId(1);
        outputConsole1.setModel("Playstation 5");
        outputConsole1.setManufacturer("Sony");
        outputConsole1.setMemoryAmount("825GB");
        outputConsole1.setProcessor("AMD Zen 2");
        outputConsole1.setPrice(new BigDecimal("499.99"));
        outputConsole1.setQuantity(0);

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

        List<Console> microsoftConsoles = new ArrayList<>();
        microsoftConsoles.add(outputConsole2);
        microsoftConsoles.add(outputConsole3);

        List<Console> sonyConsoles = new ArrayList<>();
        sonyConsoles.add(outputConsole1);

        String expectedOutputForTest1 = mapper.writeValueAsString(microsoftConsoles);
        String expectedOutputForTest2 = mapper.writeValueAsString(sonyConsoles);

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
    public void shouldGetAllConsolesAndReturnStatusOk() throws Exception {
        //Arrange
        String testUri = "/console";

        Console outputConsole1 = new Console();
        outputConsole1.setItemId(1);
        outputConsole1.setModel("Playstation 5");
        outputConsole1.setManufacturer("Sony");
        outputConsole1.setMemoryAmount("825GB");
        outputConsole1.setProcessor("AMD Zen 2");
        outputConsole1.setPrice(new BigDecimal("499.99"));
        outputConsole1.setQuantity(0);

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

        List<Console> consoleList = new ArrayList<>();
        consoleList.add(outputConsole1);
        consoleList.add(outputConsole2);
        consoleList.add(outputConsole3);

        String expectedOutputForTest = mapper.writeValueAsString(consoleList);

        //Act and Assert
        mockMvc.perform(
                get(testUri)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputForTest));

    }

    @Test
    public void shouldReturnStatusOkWhenSentValidConsole() throws Exception {
        //Arrange
        Console inputConsole1 = new Console();
        inputConsole1.setModel("Playstation 5");
        inputConsole1.setManufacturer("Sony");
        inputConsole1.setMemoryAmount("825GB");
        inputConsole1.setProcessor("AMD Zen 2");
        inputConsole1.setPrice(new BigDecimal("499.99"));
        inputConsole1.setQuantity(0);

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

        String inputJsonStringForTest1 = mapper.writeValueAsString(inputConsole1);
        String inputJsonStringForTest2 = mapper.writeValueAsString(inputConsole2);
        String inputJsonStringForTest3 = mapper.writeValueAsString(inputConsole3);

        //Act and Assert
        mockMvc.perform(
                put("/console")
                        .content(inputJsonStringForTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(
                put("/console")
                        .content(inputJsonStringForTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(
                put("/console")
                        .content(inputJsonStringForTest3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStatusUnprocessableEntityWhenSentInValidConsoleToAddOrUpdateEndpoints() throws Exception {
        //Arrange
        Console inputConsole1 = new Console();
        inputConsole1.setManufacturer("Sony");
        inputConsole1.setMemoryAmount("825GB");
        inputConsole1.setProcessor("AMD Zen 2");
        inputConsole1.setPrice(new BigDecimal("499.99"));
        inputConsole1.setQuantity(0);

        Console inputConsole2 = new Console();
        inputConsole2.setModel("XBox Series X");
        inputConsole2.setMemoryAmount("1TB");
        inputConsole2.setProcessor("AMD Zen 2");
        inputConsole2.setPrice(new BigDecimal("499.99"));
        inputConsole2.setQuantity(5);

        Console inputConsole3 = new Console();
        inputConsole3.setModel("XBox Series S");
        inputConsole3.setManufacturer("Microsoft");
        inputConsole3.setProcessor("AMD Zen 2");
        inputConsole3.setPrice(new BigDecimal("299.99"));
        inputConsole3.setQuantity(11);

        Console inputConsole4 = new Console();
        inputConsole4.setModel("Playstation 5");
        inputConsole4.setManufacturer("Sony");
        inputConsole4.setMemoryAmount("825GB");
        inputConsole4.setPrice(new BigDecimal("499.99"));
        inputConsole4.setQuantity(0);

        Console inputConsole5 = new Console();
        inputConsole5.setModel("XBox Series X");
        inputConsole5.setManufacturer("Microsoft");
        inputConsole5.setMemoryAmount("1TB");
        inputConsole5.setProcessor("AMD Zen 2");
        inputConsole5.setPrice(new BigDecimal("-499.99"));
        inputConsole5.setQuantity(5);

        Console inputConsole6 = new Console();
        inputConsole6.setModel("XBox Series S");
        inputConsole6.setManufacturer("Microsoft");
        inputConsole6.setMemoryAmount("512GB");
        inputConsole6.setProcessor("AMD Zen 2");
        inputConsole6.setPrice(new BigDecimal("299.99"));
        inputConsole6.setQuantity(new Integer("-5"));

        String inputJsonStringForTest1 = mapper.writeValueAsString(inputConsole1);
        String inputJsonStringForTest2 = mapper.writeValueAsString(inputConsole2);
        String inputJsonStringForTest3 = mapper.writeValueAsString(inputConsole3);
        String inputJsonStringForTest4 = mapper.writeValueAsString(inputConsole4);
        String inputJsonStringForTest5 = mapper.writeValueAsString(inputConsole5);
        String inputJsonStringForTest6 = mapper.writeValueAsString(inputConsole6);

        //Act and Assert
        //update endpoint
        mockMvc.perform(
                put("/console")
                        .content(inputJsonStringForTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                put("/console")
                        .content(inputJsonStringForTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                put("/console")
                        .content(inputJsonStringForTest3)
                        .contentType(MediaType.APPLICATION_JSON)


        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                put("/console")
                        .content(inputJsonStringForTest4)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                put("/console")
                        .content(inputJsonStringForTest5)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                put("/console")
                        .content(inputJsonStringForTest6)
                        .contentType(MediaType.APPLICATION_JSON)


        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        //Add endpoint
        mockMvc.perform(
                post("/console")
                        .content(inputJsonStringForTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                post("/console")
                        .content(inputJsonStringForTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                post("/console")
                        .content(inputJsonStringForTest3)
                        .contentType(MediaType.APPLICATION_JSON)


        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                post("/console")
                        .content(inputJsonStringForTest4)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                post("/console")
                        .content(inputJsonStringForTest5)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                post("/console")
                        .content(inputJsonStringForTest6)
                        .contentType(MediaType.APPLICATION_JSON)


        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturnStatusNoContentWhenPassedAValidIndexToDelete() throws Exception {
        //Arrange
        String test1 = "/console/1";
        String test2 = "/console/2";
        String test3 = "/console/3";

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
        Console inputConsole1 = new Console();
        inputConsole1.setModel("Playstation 5");
        inputConsole1.setManufacturer("Sony");
        inputConsole1.setMemoryAmount("825GB");
        inputConsole1.setProcessor("AMD Zen 2");
        inputConsole1.setPrice(new BigDecimal("499.99"));
        inputConsole1.setQuantity(0);

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

        Console invalidUpdateCustomer = new Console();
        invalidUpdateCustomer.setItemId(5);
        invalidUpdateCustomer.setModel("XBox Series S");
        invalidUpdateCustomer.setManufacturer("Microsoft");
        invalidUpdateCustomer.setMemoryAmount("512GB");
        invalidUpdateCustomer.setProcessor("AMD Zen 2");
        invalidUpdateCustomer.setPrice(new BigDecimal("299.99"));
        invalidUpdateCustomer.setQuantity(11);

        Console outputConsole1 = new Console();
        outputConsole1.setItemId(1);
        outputConsole1.setModel("Playstation 5");
        outputConsole1.setManufacturer("Sony");
        outputConsole1.setMemoryAmount("825GB");
        outputConsole1.setProcessor("AMD Zen 2");
        outputConsole1.setPrice(new BigDecimal("499.99"));
        outputConsole1.setQuantity(0);

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

        doReturn(outputConsole1).when(service).addConsole(inputConsole1);
        doReturn(outputConsole2).when(service).addConsole(inputConsole2);
        doReturn(outputConsole3).when(service).addConsole(inputConsole3);
        doReturn(outputConsole1).when(service).getConsole(1);
        doReturn(outputConsole2).when(service).getConsole(2);
        doReturn(outputConsole3).when(service).getConsole(3);
        doThrow(JdbcOperationFailedException.class).when(service).getConsole(4);
        doThrow(JdbcOperationFailedException.class).when(service).updateConsole(invalidUpdateCustomer);
        doThrow(JdbcOperationFailedException.class).when(service).deleteConsole(6);
        doReturn(outputAllConsolesList).when(service).getAllConsoles();
        doReturn(microsoftConsoles).when(service).getConsoleByManufacturer("Microsoft");
        doReturn(sonyConsoles).when(service).getConsoleByManufacturer("Sony");

    }

}