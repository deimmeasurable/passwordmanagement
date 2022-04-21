package com.example.passwordproject.exception;

public class UserAlreadyExistException extends InvalidPassWordException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
