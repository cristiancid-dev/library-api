package com.cristiancid.library.service;

import com.cristiancid.library.exception.AuthorNotFoundException;
import com.cristiancid.library.exception.BookAlreadyExistsException;
import com.cristiancid.library.exception.BookNotFoundException;
import com.cristiancid.library.model.Author;
import com.cristiancid.library.model.Book;
import com.cristiancid.library.repository.AuthorRepository;
import com.cristiancid.library.repository.BookRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    BookRepository bookRepository = new BookRepository();
    AuthorRepository authorRepository = new AuthorRepository();
    BookService bookService = new BookService(bookRepository, authorRepository);

    // createBook()

    @Test
    void givenCorrectParams_whenCreateBook_thenBookCreated() {

        String isbn = "0123456789123";
        String title = "Test Book";
        LocalDate publicationDate = LocalDate.of(2026, 9, 16);
        int authorId = 1;
        String category = "Test";
        Author author = new Author(1, "Cristian", LocalDate.of(2000,1,6));
        authorRepository.saveAuthor(author);

        Book expected = new Book(isbn, title, publicationDate, author, category);
        Book result = bookService.createBook(isbn, title, publicationDate, authorId, category);

        assertEquals(expected.getIsbn(), result.getIsbn());
        assertEquals(expected.getTitle(), result.getTitle());
        assertEquals(expected.getPublicationDate(), result.getPublicationDate());
        assertEquals(expected.getAuthor(), result.getAuthor());
        assertEquals(expected.getCategory(), result.getCategory());
    }

    @Test
    void givenExistingBook_whenCreateBook_thenBookAlreadyExistsExceptionThrown(){

        String isbn = "0123456789123";
        String title = "Test Book";
        LocalDate publicationDate = LocalDate.of(2026, 9, 16);
        int authorId = 1;
        String category = "Test";
        Author author = new Author(1, "Cristian", LocalDate.of(2000,1,6));
        authorRepository.saveAuthor(author);
        bookService.createBook(isbn, title, publicationDate, authorId, category);

        assertThrows(BookAlreadyExistsException.class, () -> {
            bookService.createBook(isbn, title, publicationDate, authorId, category);
        });
    }

    @Test
    void givenNonExistingAuthor_whenCreateBook_thenAuthorNotFoundExceptionThrown() {

        String isbn = "0123456789123";
        String title = "Test Book";
        LocalDate publicationDate = LocalDate.of(2026, 9, 16);
        int authorId = 1;
        String category = "Test";

        assertThrows(AuthorNotFoundException.class, () -> {
            bookService.createBook(isbn,title,publicationDate,authorId,category);
        });
    }

    // getBookByIsbn()

    @Test
    void givenExistingBook_whenGetBookByIsbn_thenBookReturned() {

        String isbn = "0123456789123";
        String title = "Test Book";
        LocalDate publicationDate = LocalDate.of(2026, 9, 16);
        int authorId = 1;
        String category = "Test";
        Author author = new Author(1, "Cristian", LocalDate.of(2000,1,6));
        authorRepository.saveAuthor(author);
        bookService.createBook(isbn, title, publicationDate, authorId, category);

        Book expected = new Book(isbn, title, publicationDate, author, category);
        Book result = bookService.getBookByIsbn(isbn);

        assertEquals(expected.getIsbn(), result.getIsbn());
        assertEquals(expected.getTitle(), result.getTitle());
        assertEquals(expected.getPublicationDate(), result.getPublicationDate());
        assertEquals(expected.getAuthor(), result.getAuthor());
        assertEquals(expected.getCategory(), result.getCategory());
    }

    @Test
    void givenNonExistingBook_whenGetBookByIsbn_ThenBookNotFoundExceptionThrown() {

        String isbn = "0123456789123";
        assertThrows(BookNotFoundException.class, () -> {
            bookService.getBookByIsbn(isbn);
        });
    }

    // updateBook

    @Test
    void givenExistingBookAndCorrectParams_whenUpdateBook_thenBookUpdated() {

        Author author = new Author(1, "Cristian", LocalDate.of(2000,1,6));
        authorRepository.saveAuthor(author);
        String isbn = "0123456789123";
        bookService.createBook(isbn, "Test Book",
                LocalDate.of(2026, 9, 16), 1, "Test");

        String updatedTitle = "Updated Test Book";
        LocalDate updatedPublicationDate = LocalDate.of(2025, 2, 8);
        int updatedAuthorId = 2;
        String updatedCategory = "UpdatedTest";
        Author updatedAuthor = new Author(2, "Marta", LocalDate.of(2000,3,30));
        authorRepository.saveAuthor(updatedAuthor);

        Book expected = new Book(isbn, updatedTitle, updatedPublicationDate, updatedAuthor ,updatedCategory);
        Book result = bookService.updateBook(isbn, updatedTitle, updatedPublicationDate
                ,updatedAuthorId, updatedCategory);

        assertEquals(expected.getIsbn(), result.getIsbn());
        assertEquals(expected.getTitle(), result.getTitle());
        assertEquals(expected.getPublicationDate(), result.getPublicationDate());
        assertEquals(expected.getAuthor(), result.getAuthor());
        assertEquals(expected.getCategory(), result.getCategory());
    }

    @Test
    void givenNonExistingBookAndExistingAuthor_whenUpdateBook_thenBookNotFoundExceptionThrown() {

        Author author = new Author(1, "Cristian", LocalDate.of(2000,1,6));
        authorRepository.saveAuthor(author);

        assertThrows(BookNotFoundException.class, () -> {

            bookService.updateBook("0123456789123", "Test Book",
                    LocalDate.of(2026, 9, 16), 1, "Test");
        });
    }

    @Test
    void givenNonExistingAuthor_whenUpdateBook_thenAuthorNotFoundException() {

        String isbn = "0123456789123";
        String title = "Test Book";
        LocalDate publicationDate = LocalDate.of(2026, 9, 16);
        int authorId = 1;
        String category = "Test";
        Author author = new Author(1, "Cristian", LocalDate.of(2000,1,6));
        authorRepository.saveAuthor(author);
        bookService.createBook(isbn, title, publicationDate, authorId, category);

        assertThrows(AuthorNotFoundException.class, () -> {
            bookService.updateBook(isbn, title, publicationDate, 2, category);
        });
    }

    // deleteBook()

    @Test
    void givenExistingBook_whenDeleteBook_thenBookDeleted() {

        String isbn = "0123456789123";
        String title = "Test Book";
        LocalDate publicationDate = LocalDate.of(2026, 9, 16);
        int authorId = 1;
        String category = "Test";
        Author author = new Author(1, "Cristian", LocalDate.of(2000,1,6));
        authorRepository.saveAuthor(author);
        bookService.createBook(isbn, title, publicationDate, authorId, category);

        assertDoesNotThrow(() -> {
            bookService.deleteBook(isbn);
        });

        assertThrows(BookNotFoundException.class, () -> {
            bookService.getBookByIsbn(isbn);
        });
    }

    @Test
    void givenNonExistingBook_whenDeleteBook_thenBookNotFoundExceptionThrown() {

        assertThrows(BookNotFoundException.class, () -> {
            bookService.deleteBook("0123456789123");
        });
    }
}