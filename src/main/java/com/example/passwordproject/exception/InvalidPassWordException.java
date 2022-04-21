package com.example.passwordproject.exception;

public class InvalidPassWordException extends RuntimeException {
    public InvalidPassWordException(String message){
        super(message);
    }
}
