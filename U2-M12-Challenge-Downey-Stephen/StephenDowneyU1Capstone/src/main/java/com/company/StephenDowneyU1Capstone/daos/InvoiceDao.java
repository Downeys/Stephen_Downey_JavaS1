package com.company.StephenDowneyU1Capstone.daos;

import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.Invoice;

import java.util.List;

public interface InvoiceDao {

    Invoice saveInvoice(Invoice invoice);
    //Adding following methods to better to test the save method above.
    List<Invoice> findAllInvoices();
    void deleteInvoice(int id) throws JdbcOperationFailedException;

}
