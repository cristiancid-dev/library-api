package com.cristiancid.library.repository;

import com.cristiancid.library.model.Author;
import com.cristiancid.library.model.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookRepositoryTest {

    BookRepository bookRepository = new BookRepository();

    // saveBook()

    @Test
    void givenBook_whenSaveBook_thenBookSaved() {

        Author author = new Author(1, "Cristian", LocalDate.of(2000, 1, 6));
        Book book = new Book(
                "0123456789123",
                "Test Book",
                LocalDate.of(2026,9,18),
                author,
                "Test"
        );

        bookRepository.saveBook(book);
        Optional<Book> expected = Optional.of(book);
        Optional<Book> result = bookRepository.findByIsbn("0123456789123");

        assertEquals(expected, result);
    }

    // findByIsbn()

    @Test
    void givenExistingBook_whenFindByIsbn_thenOptionalOfBookReturned() {

        Author author = new Author(1, "Cristian", LocalDate.of(2000, 1, 6));
        Book book = new Book(
                "0123456789123",
                "Test Book",
                LocalDate.of(2026,9,18),
                author,
                "Test"
        );
        bookRepository.saveBook(book);

        Optional<Book> expected = Optional.of(book);
        Optional<Book> result = bookRepository.findByIsbn("0123456789123");

        assertEquals(expected, result);

    }

    @Test
    void givenNonExistingBook_whenFindByIsbn_thenOptionalEmptyReturned() {

        Optional<Book> expected = Optional.empty();
        Optional<Book> result = bookRepository.findByIsbn("0123456789123");
        assertEquals(expected, result);
    }

    // updateBook()

    @Test
    void givenExistingBook_whenUpdateBook_thenOptionalOfUpdatedBookReturned() {

        Author author = new Author(1, "Cristian", LocalDate.of(2000, 1, 6));
        Book book = new Book(
                "0123456789123",
                "Test Book",
                LocalDate.of(2026,9,18),
                author,
                "Test"
        );
        bookRepository.saveBook(book);

        Book updatedBook = new Book(
                "0123456789123",
                "Updated Test Book",
                LocalDate.of(2026,9,18),
                author,
                "Test"
                );

        Optional<Book> expected = Optional.of(updatedBook);
        Optional<Book> result = bookRepository.updateBook("0123456789123", updatedBook);

        assertEquals(expected, result);
        assertEquals(expected, bookRepository.findByIsbn("0123456789123"));
    }

    @Test
    void givenNonExistingBook_whenUpdateBook_OptionalEmptyReturned() {

        Author author = new Author(1, "Cristian", LocalDate.of(2000, 1, 6));
        Book book = new Book(
                "0123456789123",
                "Test Book",
                LocalDate.of(2026,9,18),
                author,
                "Test"
        );
        bookRepository.saveBook(book);

        Optional<Book> expected = Optional.empty();
        Optional<Book> result = bookRepository.updateBook("9999999999999", book);
        assertEquals(expected, result);
    }

    // deleteBook()

    @Test
    void givenBook_whenDeleteBook_thenBookDeleted() {
        Author author = new Author(1, "Cristian", LocalDate.of(2000, 1, 6));
        Book book = new Book(
                "0123456789123",
                "Test Book",
                LocalDate.of(2026,9,18),
                author,
                "Test"
        );
        bookRepository.saveBook(book);

        bookRepository.deleteBook("0123456789123");
        Optional<Book> expected = Optional.empty();
        Optional<Book> result = bookRepository.findByIsbn("0123456789123");
        assertEquals(expected, result);
    }
}
