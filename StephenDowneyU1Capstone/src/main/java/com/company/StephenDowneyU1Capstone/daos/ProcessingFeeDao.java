package com.company.StephenDowneyU1Capstone.daos;

import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.ProcessingFee;

public interface ProcessingFeeDao {

    ProcessingFee findProcessingFeeByItemType(String itemType) throws JdbcOperationFailedException;

}
