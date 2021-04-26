package com.trilogyed.comment.exceptions;

public class JdbcOperationFailedException extends Exception{
    public JdbcOperationFailedException(String message){
        super(message);
    }
}
