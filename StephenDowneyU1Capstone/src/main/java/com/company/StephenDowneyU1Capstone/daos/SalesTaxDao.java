package com.company.StephenDowneyU1Capstone.daos;

import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.SalesTaxRate;

public interface SalesTaxDao {

    SalesTaxRate findSalesTaxByState(String state) throws JdbcOperationFailedException;

}
