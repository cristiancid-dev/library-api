package main.java.repository;

import main.java.model.Book;
import main.java.model.exception.BookAlreadyExistsException;
import main.java.model.exception.BookNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepository {

    private final List<Book> books = new ArrayList<>();

    public void saveBook(Book book) {
        for (Book book1 : books) {
            if (book.getIsbn().equals(book1.getIsbn())) {
                throw new BookAlreadyExistsException("Book with ISBN '" + book.getIsbn() + "'already exists");
            }
        }
        books.add(book);
    }

    public Optional<Book> findByIsbn(String isbn) {
        for (Book book:books) {
            if (book.getIsbn().equals(isbn)){
                return Optional.of(book);
            }
        }
        throw new BookNotFoundException("Book with ISBN '" + isbn + "' not found");
    }
}
