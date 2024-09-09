package com.example.springtask.exception;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String msg) {
        super(msg);
    }
}
