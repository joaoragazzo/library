package com.unifal.library.exception;

public class AuthorAlreadyExists extends RuntimeException {
    public AuthorAlreadyExists(String message) {
        super(message);
    }

}
