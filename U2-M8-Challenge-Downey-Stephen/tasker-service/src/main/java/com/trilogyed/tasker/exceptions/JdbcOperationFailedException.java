package com.trilogyed.tasker.exceptions;

public class JdbcOperationFailedException extends Exception{

    public JdbcOperationFailedException(String message){
        super(message);
    }
}
