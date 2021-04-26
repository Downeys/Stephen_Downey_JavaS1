package com.trilogyed.post.controller;

import com.trilogyed.post.exceptions.BadUpdateRequestException;
import com.trilogyed.post.exceptions.JdbcOperationFailedException;
import com.trilogyed.post.model.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
            CustomErrorResponse errorResponse = new CustomErrorResponse(fieldError.getDefaultMessage(), HttpStatus.UNPROCESSABLE_ENTITY.toString());
            errorResponse.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value()); //returns int for status
            errorResponseList.add(errorResponse);
        }
        // Create and return the ResponseEntity
        return new ResponseEntity<>(errorResponseList, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = {JdbcOperationFailedException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomErrorResponse> handleNotFoundException(JdbcOperationFailedException e) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<CustomErrorResponse>(customErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {BadUpdateRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomErrorResponse> handleBadUUpdateRequestException(BadUpdateRequestException e) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<CustomErrorResponse>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

}
