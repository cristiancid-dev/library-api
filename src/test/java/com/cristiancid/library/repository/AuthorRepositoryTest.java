package com.cristiancid.library.repository;


import com.cristiancid.library.model.Author;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorRepositoryTest {

    AuthorRepository authorRepository = new AuthorRepository();

    // saveAuthor()

    @Test
    void givenAuthor_whenSaveAuthor_thenAuthorSaved() {

        Author author = new Author(1, "J.K Rowling" , LocalDate.of(1965, 7,31));

        authorRepository.saveAuthor(author);

        Optional<Author> expected = Optional.of(author);
        Optional<Author> result = authorRepository.findById(1);

        assertEquals(expected, result);
    }

    // findById()

    @Test
    void givenExistingAuthor_whenFindById_thenOptionalOfAuthorReturned() {

        Author author = new Author(1, "J.K Rowling" , LocalDate.of(1965, 7,31));

        authorRepository.saveAuthor(author);

        Optional<Author> expected = Optional.of(author);
        Optional<Author> result = authorRepository.findById(1);

        assertEquals(expected, result);
    }

    @Test
    void givenNonExistingAuthor_whenFindById_thenOptionalEmptyReturned() {

        Optional<Author> expected = Optional.empty();
        Optional<Author> result = authorRepository.findById(1);

        assertEquals(expected, result);
    }

    // updateAuthor()

    @Test
    void givenExistingAuthor_whenUpdateAuthor_thenOptionalOfUpdatedAuthorReturned() {
        Author author = new Author(1, "J.K Rowling" , LocalDate.of(1965, 7,31));
        authorRepository.saveAuthor(author);
        Author updatedAuthor = new Author(1, "J.R.R Tolkien", LocalDate.of(1892, 1, 3));

        Optional<Author> expected = Optional.of(updatedAuthor);
        Optional<Author> result = authorRepository.updateAuthor(1, updatedAuthor);

        assertEquals(expected, result);
        assertEquals(expected, authorRepository.findById(1));
    }

    @Test
    void givenNonExistingAuthor_whenUpdateAuthor_thenOptionalEmptyReturned() {

        Author author = new Author(2, "J.K Rowling" , LocalDate.of(1965, 7,31));

        Optional<Author> expected = Optional.empty();
        Optional<Author> result = authorRepository.updateAuthor(2, author);

        assertEquals(expected, result);
    }

    @Test
    void givenAuthor_whenDeleteAuthor_thenAuthorDeleted() {

        Author author = new Author(1, "J.K Rowling" , LocalDate.of(1965, 7,31));
        authorRepository.saveAuthor(author);

        authorRepository.deleteAuthor(1);

        Optional<Author> expected = Optional.empty();
        Optional<Author> result = authorRepository.findById(1);

        assertEquals(expected, result);
    }
}
