package com.unifal.library.exception;

public class BookAlreadyExists extends RuntimeException{
    public BookAlreadyExists(String message) {
        super(message);
    }
}
