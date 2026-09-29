package com.cristiancid.library.exception;

public class BookOnActiveLoanException extends RuntimeException {
    public BookOnActiveLoanException(String message) {
        super(message);
    }
}
