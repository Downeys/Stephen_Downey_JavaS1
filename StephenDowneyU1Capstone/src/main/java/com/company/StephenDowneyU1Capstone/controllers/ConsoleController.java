package com.company.StephenDowneyU1Capstone.controllers;

import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.Console;
import com.company.StephenDowneyU1Capstone.service.ServiceLayer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ConsoleController {

    private ServiceLayer service;

    public ConsoleController(ServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/console", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Console addConsole(@RequestBody @Valid Console console) {
        return service.addConsole(console);
    }

    @RequestMapping(value = "/console/{consoleId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Console getConsole(@PathVariable Integer consoleId) throws JdbcOperationFailedException {
        return service.getConsole(consoleId);
    }

    @RequestMapping(value = "/console", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Console> getListOfConsoles(@RequestParam(required = false) String manufacturer) {
        if (manufacturer != null) {
            return service.getConsoleByManufacturer(manufacturer);
        }
        return service.getAllConsoles();
    }

    @RequestMapping(value = "/console", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateConsole(@RequestBody @Valid Console console) throws JdbcOperationFailedException {
        service.updateConsole(console);
    }

    @RequestMapping(value = "/console/{consoleId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable Integer consoleId) throws JdbcOperationFailedException {
        service.deleteConsole(consoleId);
    }
}
