package com.justinlopez.bloggingapp.exception;

public class MissingRequiredFieldsException extends RuntimeException{

    public MissingRequiredFieldsException() {
        super("All required fields must be provided");
    }

}
