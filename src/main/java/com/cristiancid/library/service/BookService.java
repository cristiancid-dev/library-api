package com.cristiancid.library.service;

import com.cristiancid.library.exception.AuthorNotFoundException;
import com.cristiancid.library.exception.BookAlreadyExistsException;
import com.cristiancid.library.exception.BookNotFoundException;
import com.cristiancid.library.model.Author;
import com.cristiancid.library.model.Book;
import com.cristiancid.library.repository.AuthorRepository;
import com.cristiancid.library.repository.BookRepository;

import java.time.LocalDate;

public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Book createBook(String isbn, String title, LocalDate publicationDate, int authorId, String category) {
        if (bookRepository.findByIsbn(isbn).isPresent()) {
            throw new BookAlreadyExistsException("Book with ISBN '" + isbn + "'already exists");
        }
        Author author =authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException("Author with id '" + authorId + "' not found"));

        Book book = new Book(isbn, title, publicationDate, author, category);
        bookRepository.saveBook(book);
        return bookRepository.findByIsbn(isbn).get();
    }

    public Book getBookByIsbn(String isbn) {
        if (bookRepository.findByIsbn(isbn).isEmpty()) {
            throw new BookNotFoundException("Book with ISBN '" + isbn + "' does not exist");
        }
        return bookRepository.findByIsbn(isbn).get();
    }

    public Book updateBook(String isbn, String title, LocalDate publicationDate, int authorId, String category) {
        Author author =authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException("Author with id '" + authorId + "' not found"));
        Book book = new Book(isbn, title, publicationDate, author, category);

        return bookRepository.updateBook(isbn, book)
                .orElseThrow(() -> new BookNotFoundException("Book with isbn '" + isbn + "' not found"));
    }

    public void deleteBook(String isbn) {
       bookRepository.findByIsbn(isbn)
               .orElseThrow(() -> new BookNotFoundException("Book with isbn '" + isbn + "' not found"));
       bookRepository.deleteBook(isbn);
    }
}
