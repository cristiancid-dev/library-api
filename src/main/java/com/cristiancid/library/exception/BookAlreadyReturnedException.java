package com.cristiancid.library.exception;

public class BookAlreadyReturnedException extends RuntimeException{
    public BookAlreadyReturnedException(String message) {
        super(message);
    }
}
