package com.cristiancid.library.repository;

import com.cristiancid.library.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepository {

    private final List<Book> books = new ArrayList<>();

    public void saveBook(Book book) {
        books.add(book);
    }

    public Optional<Book> findByIsbn(String isbn) {
        for (Book book:books) {
            if (book.getIsbn().equals(isbn)){
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    public Optional<Book> updateBook(String isbn, Book book) {
        if (findByIsbn(isbn).isEmpty()) {
            return Optional.empty();
        }
        int index = books.indexOf(findByIsbn(isbn).get());
        Book updatedBook = books.set(index, book);
        return Optional.of(updatedBook);
    }

    public void deleteBook(String isbn) {
        books.remove(findByIsbn(isbn).get());
    }

}
