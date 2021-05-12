package com.company.StephenDowneyU1Capstone.controllers;

import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.TShirt;
import com.company.StephenDowneyU1Capstone.service.ServiceLayer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TShirtController {

    private ServiceLayer service;

    public TShirtController(ServiceLayer service){
        this.service = service;
    }

    @RequestMapping(value = "/tshirt", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public TShirt addTShirt(@RequestBody @Valid TShirt tShirt){
        return service.addTShirt(tShirt);
    }

    @RequestMapping(value = "/tshirt/{tshirtId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public TShirt getTShirt(@PathVariable Integer tshirtId) throws JdbcOperationFailedException {
        return service.getTShirt(tshirtId);
    }

    @RequestMapping(value = "/tshirt", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<TShirt> getListOfTShirts(@RequestParam(required = false) String color,
                                     @RequestParam(required = false) String size){
        if(color != null){
            return service.getTShirtByColor(color);
        }

        if(size != null){
            return service.getTShirtBySize(size);
        }

        return service.getAllTShirt();
    }

    @RequestMapping(value = "/tshirt", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateTShirt(@RequestBody @Valid TShirt tShirt) throws JdbcOperationFailedException{
        service.updateTShirt(tShirt);
    }

    @RequestMapping(value = "/tshirt/{tShirtId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTShirt(@PathVariable Integer tShirtId) throws JdbcOperationFailedException{
        service.deleteTShirt(tShirtId);
    }
}
