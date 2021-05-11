package com.company.StephenDowneyU1Capstone.controllers;

import com.company.StephenDowneyU1Capstone.exceptions.OutOfStockException;
import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    //copied from RecordStore project at Desktop/WorkingFolderJavaS1/20210312/InClass
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<List<CustomErrorResponse>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // BindingResult holds the validation errors
        BindingResult result = e.getBindingResult();
        // Validation errors are stored in FieldError objects
        List<FieldError> fieldErrors = result.getFieldErrors();
        // Translate the FieldErrors to CustomErrorResponse
        List<CustomErrorResponse> errorResponseList = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.toString(), fieldError.getDefaultMessage());
            errorResponse.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value()); //returns int for status
            errorResponseList.add(errorResponse);
        }
        // Create and return the ResponseEntity
        return new ResponseEntity<>(errorResponseList, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomErrorResponse> handleNotFoundException(NotFoundException e) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage());
        return new ResponseEntity<CustomErrorResponse>(customErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {OutOfStockException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<CustomErrorResponse> handleOutOfStockException(OutOfStockException e) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(HttpStatus.CONFLICT.toString(), e.getMessage());
        return new ResponseEntity<CustomErrorResponse>(customErrorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {JdbcOperationFailedException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomErrorResponse> handleEmptyResultDataAccessException(JdbcOperationFailedException e) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage());
        return new ResponseEntity<CustomErrorResponse>(customErrorResponse, HttpStatus.NOT_FOUND);
    }
}
