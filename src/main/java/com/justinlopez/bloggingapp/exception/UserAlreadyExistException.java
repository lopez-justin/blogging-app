package com.justinlopez.bloggingapp.exception;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException() {
        super("User already exist.");
    }

}
