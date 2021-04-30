package com.company.StephenDowneyU1Capstone.daos;

import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.Console;

import java.util.List;

public interface ConsoleDao {

    Console saveConsole(Console console);
    Console findConsole(Integer consoleId) throws JdbcOperationFailedException;
    List<Console> findConsoleByManufacturer(String manufacturer);
    List<Console> findAllConsoles();
    void updateConsole(Console console) throws JdbcOperationFailedException;
    void deleteConsole(Integer consoleId) throws JdbcOperationFailedException;

}
