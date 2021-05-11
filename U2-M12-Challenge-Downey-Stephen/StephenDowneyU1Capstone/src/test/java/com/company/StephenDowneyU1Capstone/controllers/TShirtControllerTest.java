package com.company.StephenDowneyU1Capstone.controllers;

import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.TShirt;
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

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TShirtController.class)
public class TShirtControllerTest {
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
    public void shouldAddTShirtAndReturnStatusCreated() throws Exception {
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

        String inputJsonStringForTest1 = mapper.writeValueAsString(inputTShirt1);
        String inputJsonStringForTest2 = mapper.writeValueAsString(inputTShirt2);
        String inputJsonStringForTest3 = mapper.writeValueAsString(inputTShirt3);

        String expectedOutPutStringFromTest1 = mapper.writeValueAsString(outputTShirt1);
        String expectedOutPutStringFromTest2 = mapper.writeValueAsString(outputTShirt2);
        String expectedOutPutStringFromTest3 = mapper.writeValueAsString(outputTShirt3);

        //Act and Assert
        mockMvc.perform(
                post("/tshirt")
                        .content(inputJsonStringForTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedOutPutStringFromTest1));

        mockMvc.perform(
                post("/tshirt")
                        .content(inputJsonStringForTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedOutPutStringFromTest2));

        mockMvc.perform(
                post("/tshirt")
                        .content(inputJsonStringForTest3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedOutPutStringFromTest3));
    }

    @Test
    public void shouldGetTShirtByIdAndReturnStatusOk() throws Exception {
        //Arrange
        String test1 = "/tshirt/1";
        String test2 = "/tshirt/2";
        String test3 = "/tshirt/3";

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

        String expectedOutputForTest1 = mapper.writeValueAsString(outputTShirt1);
        String expectedOutputForTest2 = mapper.writeValueAsString(outputTShirt2);
        String expectedOutputForTest3 = mapper.writeValueAsString(outputTShirt3);

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
        String test1 = "/tshirt/4";
        String test2 = "/tshirt/6";

        TShirt invalidUpdateTshirt = new TShirt();
        invalidUpdateTshirt.setItemId(5);
        invalidUpdateTshirt.setSize("M");
        invalidUpdateTshirt.setColor("Black");
        invalidUpdateTshirt.setDescription("COD Shirt");
        invalidUpdateTshirt.setPrice(new BigDecimal("24.99"));
        invalidUpdateTshirt.setQuantity(1);

        String test3 = mapper.writeValueAsString(invalidUpdateTshirt);

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
                put("/tshirt")
                        .content(test3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    public void shouldGetTShirtByColorAndReturnStatusOk() throws Exception{
        //Arrange
        String test1 = "/tshirt?color=Green";
        String test2 = "/tshirt?color=Black";

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

        List<TShirt> greenShirts = new ArrayList<>();
        greenShirts.add(outputTShirt1);

        List<TShirt> blackShirts = new ArrayList<>();
        blackShirts.add(outputTShirt2);
        blackShirts.add(outputTShirt3);

        String expectedOutputForTest1 = mapper.writeValueAsString(greenShirts);
        String expectedOutputForTest2 = mapper.writeValueAsString(blackShirts);

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
    public void shouldGetTShirtBySizeAndReturnStatusOk() throws Exception{
        //Arrange
        String test1 = "/tshirt?size=M";
        String test2 = "/tshirt?size=XL";

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

        List<TShirt> mSizeShirts = new ArrayList<>();
        mSizeShirts.add(outputTShirt1);
        mSizeShirts.add(outputTShirt3);

        List<TShirt> xlSizeShirts = new ArrayList<>();
        xlSizeShirts.add(outputTShirt2);

        String expectedOutputForTest1 = mapper.writeValueAsString(mSizeShirts);
        String expectedOutputForTest2 = mapper.writeValueAsString(xlSizeShirts);

        ///Act and Assert
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
    public void shouldGetAllTShirtsAndReturnStatusOk() throws Exception{
        //Arrange
        String testUri = "/tshirt";

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

        List<TShirt> tShirtList = new ArrayList<>();
        tShirtList.add(outputTShirt1);
        tShirtList.add(outputTShirt2);
        tShirtList.add(outputTShirt3);

        String expectedOutputForTest = mapper.writeValueAsString(tShirtList);

        //Act and Assert
        mockMvc.perform(
                get(testUri)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputForTest));

    }

    @Test
    public void shouldReturnStatusOkWhenSentValidTShirt() throws Exception{
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

        String inputJsonStringForTest1 = mapper.writeValueAsString(inputTShirt1);
        String inputJsonStringForTest2 = mapper.writeValueAsString(inputTShirt2);
        String inputJsonStringForTest3 = mapper.writeValueAsString(inputTShirt3);

        //Act and Assert
        mockMvc.perform(
                put("/tshirt")
                        .content(inputJsonStringForTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(
                put("/tshirt")
                        .content(inputJsonStringForTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(
                put("/tshirt")
                        .content(inputJsonStringForTest3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStatusUnprocessableEntityWhenSentInValidTShirtToUpdateOrAddEndpoints() throws Exception{
        //Arrange
        TShirt inputTShirt1 = new TShirt();
        inputTShirt1.setColor("Green");
        inputTShirt1.setDescription("Minecraft Shirt");
        inputTShirt1.setPrice(new BigDecimal("24.99"));
        inputTShirt1.setQuantity(6);

        TShirt inputTShirt2 = new TShirt();
        inputTShirt2.setSize("XL");
        inputTShirt2.setDescription("Minecraft Shirt");
        inputTShirt2.setPrice(new BigDecimal("29.99"));
        inputTShirt2.setQuantity(12);

        TShirt inputTShirt3 = new TShirt();
        inputTShirt3.setSize("M");
        inputTShirt3.setColor("Black");
        inputTShirt3.setPrice(new BigDecimal("24.99"));
        inputTShirt3.setQuantity(1);

        TShirt inputTShirt4 = new TShirt();
        inputTShirt4.setSize("M");
        inputTShirt4.setColor("Green");
        inputTShirt4.setDescription("Minecraft Shirt");
        inputTShirt4.setPrice(new BigDecimal("-24.99"));
        inputTShirt4.setQuantity(6);

        TShirt inputTShirt5 = new TShirt();
        inputTShirt5.setSize("XL");
        inputTShirt5.setColor("Black");
        inputTShirt5.setDescription("Minecraft Shirt");
        inputTShirt5.setPrice(new BigDecimal("29.99"));
        inputTShirt5.setQuantity(new Integer("-5"));

        String inputJsonStringForTest1 = mapper.writeValueAsString(inputTShirt1);
        String inputJsonStringForTest2 = mapper.writeValueAsString(inputTShirt2);
        String inputJsonStringForTest3 = mapper.writeValueAsString(inputTShirt3);
        String inputJsonStringForTest4 = mapper.writeValueAsString(inputTShirt4);
        String inputJsonStringForTest5 = mapper.writeValueAsString(inputTShirt5);

        //Act and Assert
        //update endpoint
        mockMvc.perform(
                put("/tshirt")
                        .content(inputJsonStringForTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                put("/tshirt")
                        .content(inputJsonStringForTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                put("/tshirt")
                        .content(inputJsonStringForTest3)
                        .contentType(MediaType.APPLICATION_JSON)


        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                put("/tshirt")
                        .content(inputJsonStringForTest4)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                put("/tshirt")
                        .content(inputJsonStringForTest5)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        //add endpoint
        mockMvc.perform(
                post("/tshirt")
                        .content(inputJsonStringForTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                post("/tshirt")
                        .content(inputJsonStringForTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                post("/tshirt")
                        .content(inputJsonStringForTest3)
                        .contentType(MediaType.APPLICATION_JSON)


        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                post("/tshirt")
                        .content(inputJsonStringForTest4)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                post("/tshirt")
                        .content(inputJsonStringForTest5)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());


    }

    @Test
    public void shouldReturnStatusNoContentWhenPassedAValidIndexToDelete() throws Exception{
        //Arrange
        String test1 = "/tshirt/1";
        String test2 = "/tshirt/2";
        String test3 = "/tshirt/3";

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

        TShirt invalidUpdateTshirt = new TShirt();
        invalidUpdateTshirt.setItemId(5);
        invalidUpdateTshirt.setSize("M");
        invalidUpdateTshirt.setColor("Black");
        invalidUpdateTshirt.setDescription("COD Shirt");
        invalidUpdateTshirt.setPrice(new BigDecimal("24.99"));
        invalidUpdateTshirt.setQuantity(1);

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

        doReturn(outputTShirt1).when(service).addTShirt(inputTShirt1);
        doReturn(outputTShirt2).when(service).addTShirt(inputTShirt2);
        doReturn(outputTShirt3).when(service).addTShirt(inputTShirt3);
        doReturn(outputTShirt1).when(service).getTShirt(1);
        doReturn(outputTShirt2).when(service).getTShirt(2);
        doReturn(outputTShirt3).when(service).getTShirt(3);
        doThrow(JdbcOperationFailedException.class).when(service).getTShirt(4);
        doThrow(JdbcOperationFailedException.class).when(service).updateTShirt(invalidUpdateTshirt);
        doThrow(JdbcOperationFailedException.class).when(service).deleteTShirt(6);
        doReturn(allOutputTShirtList).when(service).getAllTShirt();
        doReturn(blackShirts).when(service).getTShirtByColor("Black");
        doReturn(greenShirts).when(service).getTShirtByColor("Green");
        doReturn(xlSizeShirts).when(service).getTShirtBySize("XL");
        doReturn(mSizeShirts).when(service).getTShirtBySize("M");
    }
}