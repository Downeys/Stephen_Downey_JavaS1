package com.trilogyed.post.exceptions;

public class JdbcOperationFailedException extends Exception{
    public JdbcOperationFailedException(String message){
        super(message);
    }
}
