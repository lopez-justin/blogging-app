package com.justinlopez.bloggingapp.exception;

public class CategoryNotExistException extends RuntimeException {

    public CategoryNotExistException(String message) {
        super("Category not found with id: " + message);
    }

}
