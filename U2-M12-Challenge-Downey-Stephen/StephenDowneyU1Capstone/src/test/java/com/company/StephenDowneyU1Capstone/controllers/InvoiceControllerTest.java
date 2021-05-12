package com.company.StephenDowneyU1Capstone.controllers;

import com.company.StephenDowneyU1Capstone.exceptions.OutOfStockException;
import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.*;
import com.company.StephenDowneyU1Capstone.service.ServiceLayer;
import com.company.StephenDowneyU1Capstone.viewModels.InvoiceViewModel;
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
import java.math.RoundingMode;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {

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
    public void shouldAddInvoiceReturnInvoiceViewModelAndStatusCreated() throws Exception{
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
        inputInvoice2.setItemType("TShirts");
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

        Invoice inputInvoice4 = new Invoice(); //This input should be used to test what happens when the user orders more than is in stock
        inputInvoice4.setName("Diane");
        inputInvoice4.setStreet("456 Another Rd.");
        inputInvoice4.setCity("Another City");
        inputInvoice4.setState("AR");
        inputInvoice4.setZipcode("46525");
        inputInvoice4.setItemType("Consoles");
        inputInvoice4.setItemId(1);
        inputInvoice4.setQuantity(26);

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

        InvoiceViewModel outputInvoiceViewModel1 = new InvoiceViewModel();
        outputInvoiceViewModel1.setInvoiceId(1);
        outputInvoiceViewModel1.setName("Jack");
        outputInvoiceViewModel1.setStreet("123 Main St.");
        outputInvoiceViewModel1.setCity("Main City");
        outputInvoiceViewModel1.setState("ME");
        outputInvoiceViewModel1.setZipcode("12345");
        outputInvoiceViewModel1.setItem(testGame1);
        outputInvoiceViewModel1.setSubtotal(subtotal);
        outputInvoiceViewModel1.setTax(totalTax);
        outputInvoiceViewModel1.setProcessingFee(processingFee);
        outputInvoiceViewModel1.setTotal(total);

        quantity = new BigDecimal("2");
        unitPrice = testShirt1.getPrice();
        subtotal = unitPrice.multiply(quantity);
        taxRate = new BigDecimal(".07");
        totalTax = subtotal.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
        processingFee = new BigDecimal("1.98");
        total = subtotal.add(totalTax.add(processingFee));

        InvoiceViewModel outputInvoiceViewModel2 = new InvoiceViewModel();
        outputInvoiceViewModel2.setInvoiceId(2);
        outputInvoiceViewModel2.setName("Jill");
        outputInvoiceViewModel2.setStreet("321 Other Dr.");
        outputInvoiceViewModel2.setCity("Other City");
        outputInvoiceViewModel2.setState("OR");
        outputInvoiceViewModel2.setZipcode("98765");
        outputInvoiceViewModel2.setItem(testShirt1);
        outputInvoiceViewModel2.setSubtotal(subtotal);
        outputInvoiceViewModel2.setTax(totalTax);
        outputInvoiceViewModel2.setProcessingFee(processingFee);
        outputInvoiceViewModel2.setTotal(total);

        quantity = new BigDecimal("15");
        unitPrice = testConsole1.getPrice();
        subtotal = unitPrice.multiply(quantity);
        taxRate = new BigDecimal(".06");
        totalTax = subtotal.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
        processingFee = new BigDecimal("30.48");
        total = subtotal.add(totalTax.add(processingFee));

        InvoiceViewModel outputInvoiceViewModel3 = new InvoiceViewModel();
        outputInvoiceViewModel3.setInvoiceId(3);
        outputInvoiceViewModel3.setName("Diane");
        outputInvoiceViewModel3.setStreet("456 Another Rd.");
        outputInvoiceViewModel3.setCity("Another City");
        outputInvoiceViewModel3.setState("AR");
        outputInvoiceViewModel3.setZipcode("46525");
        outputInvoiceViewModel3.setItem(testConsole1);
        outputInvoiceViewModel3.setSubtotal(subtotal);
        outputInvoiceViewModel3.setTax(totalTax);
        outputInvoiceViewModel3.setProcessingFee(processingFee);
        outputInvoiceViewModel3.setTotal(total);

        String inputJsonString1 = mapper.writeValueAsString(inputInvoice1);
        String inputJsonString2 = mapper.writeValueAsString(inputInvoice2);
        String inputJsonString3 = mapper.writeValueAsString(inputInvoice3);

        String expectedOutputString1 = mapper.writeValueAsString(outputInvoiceViewModel1);
        String expectedOutputString2 = mapper.writeValueAsString(outputInvoiceViewModel2);
        String expectedOutputString3 = mapper.writeValueAsString(outputInvoiceViewModel3);

        //Act and Assert
        mockMvc.perform(
                post("/invoice")
                .content(inputJsonString1)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedOutputString1));

        mockMvc.perform(
                post("/invoice")
                        .content(inputJsonString2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedOutputString2));

        mockMvc.perform(
                post("/invoice")
                        .content(inputJsonString3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedOutputString3));
    }

    @Test
    public void shouldReturnStatusUnprocessableEntityWhenPassedAnInvalidOrder() throws Exception{
        //Arrange
        Invoice inputInvoice1 = new Invoice();
        inputInvoice1.setStreet("123 Main St.");
        inputInvoice1.setCity("Main City");
        inputInvoice1.setState("ME");
        inputInvoice1.setZipcode("12345");
        inputInvoice1.setItemType("Games");
        inputInvoice1.setItemId(1);
        inputInvoice1.setQuantity(1);

        Invoice inputInvoice2 = new Invoice();
        inputInvoice2.setName("Jill");
        inputInvoice2.setCity("Other City");
        inputInvoice2.setState("OR");
        inputInvoice2.setZipcode("98765");
        inputInvoice2.setItemType("TShirts");
        inputInvoice2.setItemId(1);
        inputInvoice2.setQuantity(2);

        Invoice inputInvoice3 = new Invoice();
        inputInvoice3.setName("Diane");
        inputInvoice3.setStreet("456 Another Rd.");
        inputInvoice3.setState("AR");
        inputInvoice3.setZipcode("46525");
        inputInvoice3.setItemType("Consoles");
        inputInvoice3.setItemId(1);
        inputInvoice3.setQuantity(15);

        Invoice inputInvoice4 = new Invoice();
        inputInvoice4.setName("Jack");
        inputInvoice4.setStreet("123 Main St.");
        inputInvoice4.setCity("Main City");
        inputInvoice4.setZipcode("12345");
        inputInvoice4.setItemType("Games");
        inputInvoice4.setItemId(1);
        inputInvoice4.setQuantity(1);

        Invoice inputInvoice5 = new Invoice();
        inputInvoice5.setName("Jill");
        inputInvoice5.setStreet("321 Other Dr.");
        inputInvoice5.setCity("Other City");
        inputInvoice5.setState("OR");
        inputInvoice5.setItemType("TShirts");
        inputInvoice5.setItemId(1);
        inputInvoice5.setQuantity(2);

        Invoice inputInvoice6 = new Invoice();
        inputInvoice6.setName("Diane");
        inputInvoice6.setStreet("456 Another Rd.");
        inputInvoice6.setCity("Another City");
        inputInvoice6.setState("AR");
        inputInvoice6.setZipcode("46525");
        inputInvoice6.setItemId(1);
        inputInvoice6.setQuantity(15);

        Invoice inputInvoice7 = new Invoice();
        inputInvoice7.setName("Jack");
        inputInvoice7.setStreet("123 Main St.");
        inputInvoice7.setCity("Main City");
        inputInvoice7.setState("ME");
        inputInvoice7.setZipcode("12345");
        inputInvoice7.setItemType("Games");
        inputInvoice7.setItemId(1);
        inputInvoice7.setQuantity(0);

        Invoice inputInvoice8 = new Invoice();
        inputInvoice8.setName("Jack");
        inputInvoice8.setStreet("123 Main St.");
        inputInvoice8.setCity("Main City");
        inputInvoice8.setState("ME");
        inputInvoice8.setZipcode("12345");
        inputInvoice8.setItemType("Games");
        inputInvoice8.setItemId(1);
        inputInvoice8.setQuantity(new Integer("-1"));

        String inputJsonString1 = mapper.writeValueAsString(inputInvoice1);
        String inputJsonString2 = mapper.writeValueAsString(inputInvoice2);
        String inputJsonString3 = mapper.writeValueAsString(inputInvoice3);
        String inputJsonString4 = mapper.writeValueAsString(inputInvoice4);
        String inputJsonString5 = mapper.writeValueAsString(inputInvoice5);
        String inputJsonString6 = mapper.writeValueAsString(inputInvoice6);
        String inputJsonString7 = mapper.writeValueAsString(inputInvoice7);
        String inputJsonString8 = mapper.writeValueAsString(inputInvoice8);

        //Act and Assert
        mockMvc.perform(
                post("/invoice")
                        .content(inputJsonString1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                post("/invoice")
                        .content(inputJsonString2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                post("/invoice")
                        .content(inputJsonString3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                post("/invoice")
                        .content(inputJsonString4)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                post("/invoice")
                        .content(inputJsonString5)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                post("/invoice")
                        .content(inputJsonString6)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                post("/invoice")
                        .content(inputJsonString7)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                post("/invoice")
                        .content(inputJsonString8)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturnConflictStatusWhenPassedAnOrderQuantityThatIsHigherThanInventoryQuantity() throws Exception{
        //Arrange
        Invoice inputInvoice4 = new Invoice(); //This input should be used to test what happens when the user orders more than is in stock
        inputInvoice4.setName("Diane");
        inputInvoice4.setStreet("456 Another Rd.");
        inputInvoice4.setCity("Another City");
        inputInvoice4.setState("AR");
        inputInvoice4.setZipcode("46525");
        inputInvoice4.setItemType("Consoles");
        inputInvoice4.setItemId(1);
        inputInvoice4.setQuantity(26);

        String inputJsonString = mapper.writeValueAsString(inputInvoice4);

        //Act and Assert
        mockMvc.perform(
                post("/invoice")
                .content(inputJsonString)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void shouldReturnNotFoundStatusWhenPassedAnInvalidStateCodeOrItemType() throws Exception{
        //Arrange
        Invoice inputInvoice5 = new Invoice(); //This input should be used to test what happens when the user inputs an invalid state code
        inputInvoice5.setName("Diane");
        inputInvoice5.setStreet("456 Another Rd.");
        inputInvoice5.setCity("Another City");
        inputInvoice5.setState("AX");
        inputInvoice5.setZipcode("46525");
        inputInvoice5.setItemType("Consoles");
        inputInvoice5.setItemId(1);
        inputInvoice5.setQuantity(2);

        Invoice inputInvoice6 = new Invoice(); //This input should be used to test what happens when the user inputs an invalid item type
        inputInvoice6.setName("Diane");
        inputInvoice6.setStreet("456 Another Rd.");
        inputInvoice6.setCity("Another City");
        inputInvoice6.setState("AX");
        inputInvoice6.setZipcode("46525");
        inputInvoice6.setItemType("Guitars");
        inputInvoice6.setItemId(1);
        inputInvoice6.setQuantity(1);

        String inputJsonString1 = mapper.writeValueAsString(inputInvoice5);
        String inputJsonString2 = mapper.writeValueAsString(inputInvoice6);

        //Act and Assert
        mockMvc.perform(
                post("/invoice")
                        .content(inputJsonString1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNotFound());

        mockMvc.perform(
                post("/invoice")
                        .content(inputJsonString2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    private void setupServiceLayerMock() throws OutOfStockException, JdbcOperationFailedException {
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
        inputInvoice2.setItemType("TShirts");
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

        Invoice inputInvoice4 = new Invoice(); //This input should be used to test what happens when the user orders more than is in stock
        inputInvoice4.setName("Diane");
        inputInvoice4.setStreet("456 Another Rd.");
        inputInvoice4.setCity("Another City");
        inputInvoice4.setState("AR");
        inputInvoice4.setZipcode("46525");
        inputInvoice4.setItemType("Consoles");
        inputInvoice4.setItemId(1);
        inputInvoice4.setQuantity(26);

        Invoice inputInvoice5 = new Invoice(); //This input should be used to test what happens when the user inputs an invalid state code
        inputInvoice5.setName("Diane");
        inputInvoice5.setStreet("456 Another Rd.");
        inputInvoice5.setCity("Another City");
        inputInvoice5.setState("AX");
        inputInvoice5.setZipcode("46525");
        inputInvoice5.setItemType("Consoles");
        inputInvoice5.setItemId(1);
        inputInvoice5.setQuantity(2);

        Invoice inputInvoice6 = new Invoice(); //This input should be used to test what happens when the user inputs an invalid item type
        inputInvoice6.setName("Diane");
        inputInvoice6.setStreet("456 Another Rd.");
        inputInvoice6.setCity("Another City");
        inputInvoice6.setState("AX");
        inputInvoice6.setZipcode("46525");
        inputInvoice6.setItemType("Guitars");
        inputInvoice6.setItemId(1);
        inputInvoice6.setQuantity(1);

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

        InvoiceViewModel outputInvoiceViewModel1 = new InvoiceViewModel();
        outputInvoiceViewModel1.setInvoiceId(1);
        outputInvoiceViewModel1.setName("Jack");
        outputInvoiceViewModel1.setStreet("123 Main St.");
        outputInvoiceViewModel1.setCity("Main City");
        outputInvoiceViewModel1.setState("ME");
        outputInvoiceViewModel1.setZipcode("12345");
        outputInvoiceViewModel1.setItem(testGame1);
        outputInvoiceViewModel1.setSubtotal(subtotal);
        outputInvoiceViewModel1.setTax(totalTax);
        outputInvoiceViewModel1.setProcessingFee(processingFee);
        outputInvoiceViewModel1.setTotal(total);

        quantity = new BigDecimal("2");
        unitPrice = testShirt1.getPrice();
        subtotal = unitPrice.multiply(quantity);
        taxRate = new BigDecimal(".07");
        totalTax = subtotal.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
        processingFee = new BigDecimal("1.98");
        total = subtotal.add(totalTax.add(processingFee));

        InvoiceViewModel outputInvoiceViewModel2 = new InvoiceViewModel();
        outputInvoiceViewModel2.setInvoiceId(2);
        outputInvoiceViewModel2.setName("Jill");
        outputInvoiceViewModel2.setStreet("321 Other Dr.");
        outputInvoiceViewModel2.setCity("Other City");
        outputInvoiceViewModel2.setState("OR");
        outputInvoiceViewModel2.setZipcode("98765");
        outputInvoiceViewModel2.setItem(testShirt1);
        outputInvoiceViewModel2.setSubtotal(subtotal);
        outputInvoiceViewModel2.setTax(totalTax);
        outputInvoiceViewModel2.setProcessingFee(processingFee);
        outputInvoiceViewModel2.setTotal(total);

        quantity = new BigDecimal("15");
        unitPrice = testConsole1.getPrice();
        subtotal = unitPrice.multiply(quantity);
        taxRate = new BigDecimal(".06");
        totalTax = subtotal.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
        processingFee = new BigDecimal("30.48");
        total = subtotal.add(totalTax.add(processingFee));

        InvoiceViewModel outputInvoiceViewModel3 = new InvoiceViewModel();
        outputInvoiceViewModel3.setInvoiceId(3);
        outputInvoiceViewModel3.setName("Diane");
        outputInvoiceViewModel3.setStreet("456 Another Rd.");
        outputInvoiceViewModel3.setCity("Another City");
        outputInvoiceViewModel3.setState("AR");
        outputInvoiceViewModel3.setZipcode("46525");
        outputInvoiceViewModel3.setItem(testConsole1);
        outputInvoiceViewModel3.setSubtotal(subtotal);
        outputInvoiceViewModel3.setTax(totalTax);
        outputInvoiceViewModel3.setProcessingFee(processingFee);
        outputInvoiceViewModel3.setTotal(total);

        doReturn(outputInvoiceViewModel1).when(service).placeOrderAndAddInvoice(inputInvoice1);
        doReturn(outputInvoiceViewModel2).when(service).placeOrderAndAddInvoice(inputInvoice2);
        doReturn(outputInvoiceViewModel3).when(service).placeOrderAndAddInvoice(inputInvoice3);
        doThrow(OutOfStockException.class).when(service).placeOrderAndAddInvoice(inputInvoice4);
        doThrow(JdbcOperationFailedException.class).when(service).placeOrderAndAddInvoice(inputInvoice5);
        doThrow(JdbcOperationFailedException.class).when(service).placeOrderAndAddInvoice(inputInvoice6);

    }
}