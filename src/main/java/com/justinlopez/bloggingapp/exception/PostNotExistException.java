package com.justinlopez.bloggingapp.exception;

public class PostNotExistException extends RuntimeException {

    public PostNotExistException(String id) {
        super("Post with id " + id + " does not exist");
    }

}
