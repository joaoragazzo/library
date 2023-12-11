package com.unifal.library.exception;


public class BookDontExists extends RuntimeException {
    public BookDontExists(String message) {
        super(message);
    }
}
