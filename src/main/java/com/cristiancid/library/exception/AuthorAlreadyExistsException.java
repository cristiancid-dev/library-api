package main.java.exception;

public class AuthorAlreadyExistsException extends RuntimeException{
    public AuthorAlreadyExistsException(String message) {
        super(message);
    }
}
