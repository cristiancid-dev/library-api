package main.java.model.exception;

public class BookAlreadyLoanedException extends  RuntimeException {
    public BookAlreadyLoanedException(String message) {
        super(message);
    }
}
