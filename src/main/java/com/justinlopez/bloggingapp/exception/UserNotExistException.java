package com.justinlopez.bloggingapp.exception;

public class UserNotExistException extends RuntimeException {
    public UserNotExistException() {
        super("User not found.");
    }
}
