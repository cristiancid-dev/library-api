package com.cristiancid.library.service;

import com.cristiancid.library.exception.AuthorNotFoundException;
import com.cristiancid.library.model.Author;
import com.cristiancid.library.repository.AuthorRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorServiceTest {

    AuthorRepository authorRepository = new AuthorRepository();
    AuthorService authorService = new AuthorService(authorRepository);

    // createAuthor()

    @Test
    void givenCorrectParams_whenCreateAuthor_thenAuthorCreated() {

        Author expected = new Author(1, "J.K Rowling", LocalDate.of(1965,7,31));
        Author result = authorService.createAuthor("J.K Rowling" , LocalDate.of(1965, 7,31));

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getBirthDate(), result.getBirthDate());

    }

   // getAuthorById()

    @Test
    void givenExistingAuthor_whenGetAuthorById_thenAuthorReturned() {

        Author expected = authorService.createAuthor("J.K Rowling" , LocalDate.of(1965, 7,31));
        Author result = authorService.getAuthorById(1);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getBirthDate(), result.getBirthDate());

    }

    @Test
    void givenNonExistingAuthor_whenGetAuthorById_thenAuthorNotFoundExceptionThrown(){

        assertThrows(AuthorNotFoundException.class, () -> {
                authorService.getAuthorById(1);
        });
    }

    // updateAuthor()

    @Test
    void givenExistingAuthor_whenUpdateAuthor_thenAuthorUpdated() {
        authorService.createAuthor("J.K Rowling" , LocalDate.of(1965, 7,31));

        Author result = authorService.updateAuthor(1, "J.R.R Tolkien", LocalDate.of(1892, 1, 3));

        assertEquals(1, result.getId());
        assertEquals("J.R.R Tolkien", result.getName());
        assertEquals(LocalDate.of(1892, 1, 3), result.getBirthDate());
    }

    @Test
    void givenNonExistingAuthor_whenUpdateAuthor_thenAuthorNotFoundExceptionThrown() {

        assertThrows(AuthorNotFoundException.class, () -> {
            authorService.updateAuthor(1, "J.R.R Tolkien", LocalDate.of(1892, 1, 3));
        });
    }

    // deleteAuthor()

    @Test
    void givenExistingAuthor_whenDeleteAuthor_thenAuthorDeleted() {

        authorService.createAuthor("J.K Rowling" , LocalDate.of(1965, 7,31));

        assertDoesNotThrow(() -> {
            authorService.deleteAuthor(1);
        });

        assertThrows(AuthorNotFoundException.class, () -> {
            authorService.getAuthorById(1);
        });
    }

    @Test
    void givenNonExistingAuthor_whenDeleteAuthor_thenAuthorNotFoundExceptionThrown() {

        assertThrows(AuthorNotFoundException.class, () -> {
            authorService.deleteAuthor(1);
        });
    }
}
