package com.cristiancid.library.exception;

public class BookAlreadyLoanedException extends  RuntimeException {
    public BookAlreadyLoanedException(String message) {
        super(message);
    }
}
