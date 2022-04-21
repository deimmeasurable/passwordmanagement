package com.example.passwordproject.exception;

public class UserNotFoundException extends InvalidPassWordException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
