package com.justinlopez.bloggingapp.exception;

public class UserNotExistException extends RuntimeException {
    public UserNotExistException(String message) {
        super("User not found with id: " + message);
    }
}
