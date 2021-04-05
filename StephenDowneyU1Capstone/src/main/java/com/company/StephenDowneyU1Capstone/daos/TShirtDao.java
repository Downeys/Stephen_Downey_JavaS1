package com.company.StephenDowneyU1Capstone.daos;

import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.TShirt;

import java.util.List;

public interface TShirtDao{

    TShirt saveTShirt(TShirt tShirt);
    TShirt findTShirt(Integer tShirtId) throws JdbcOperationFailedException;
    List<TShirt> findTShirtByColor(String color);
    List<TShirt> findTShirtBySize(String size);
    List<TShirt> findAllTShirts();
    void updateTShirt(TShirt tShirt) throws JdbcOperationFailedException;
    void deleteTShirt(Integer tShirtId) throws JdbcOperationFailedException;

}
