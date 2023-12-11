package com.unifal.library.exception;


public class AuthorDontExists extends RuntimeException {
    public AuthorDontExists(String message) {
        super(message);
    }
}
