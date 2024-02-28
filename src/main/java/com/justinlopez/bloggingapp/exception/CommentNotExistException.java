package com.justinlopez.bloggingapp.exception;

public class CommentNotExistException extends RuntimeException {

    public CommentNotExistException(String message) {
        super("Comment with id " + message + " does not exist");
    }

}
