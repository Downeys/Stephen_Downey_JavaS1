package com.company.StephenDowneyU1Capstone.controllers;

import com.company.StephenDowneyU1Capstone.exceptions.OutOfStockException;
import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.Invoice;
import com.company.StephenDowneyU1Capstone.service.ServiceLayer;
import com.company.StephenDowneyU1Capstone.viewModels.InvoiceViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class InvoiceController {

    private ServiceLayer service;

    public InvoiceController(ServiceLayer service){
        this.service = service;
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public InvoiceViewModel placeOrder(@RequestBody @Valid Invoice invoice) throws OutOfStockException, JdbcOperationFailedException {
        return service.placeOrderAndAddInvoice(invoice);
    }

}
