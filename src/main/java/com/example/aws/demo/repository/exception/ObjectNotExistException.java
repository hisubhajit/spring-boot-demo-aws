package com.example.aws.demo.repository.exception;

public class ObjectNotExistException extends Exception {

    private final String message;
    public ObjectNotExistException(String message) {
        this.message = message;
    }
}
