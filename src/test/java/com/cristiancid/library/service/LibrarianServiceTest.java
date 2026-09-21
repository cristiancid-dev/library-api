package com.cristiancid.library.service;

import com.cristiancid.library.exception.LibrarianAlreadyExistsException;
import com.cristiancid.library.exception.LibrarianNotFoundException;
import com.cristiancid.library.model.Librarian;
import com.cristiancid.library.repository.LibrarianRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarianServiceTest {

    LibrarianRepository librarianRepository = new LibrarianRepository();
    LibrarianService librarianService = new LibrarianService(librarianRepository);

    // createLibrarian()

    @Test
    void givenCorrectParams_whenCreateLibrarian_thenLibrarianCreated() {

        Librarian expected = new Librarian(1, "Cristian", "12345678", "ccidbe@library.com");
        Librarian result = librarianService.createLibrarian("Cristian", "12345678", "ccidbe@gmail.com");

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getPassword(), result.getPassword());
        assertEquals(expected.getEmail(), result.getEmail());
    }

    @Test
    void givenTwoLibrarians_whenCreateLibrarian_thenIdIncrements() {

        librarianService.createLibrarian("Cristian", "12345678", "ccidbe@library.com");

        Librarian result = librarianService.createLibrarian("David","12345678", "david@library.com");

        assertEquals(2, result.getId());
    }

    @Test
    void givenExistingEmail_whenCreateLibrarian_thenLibrarianAlreadyExistsExceptionThrown() {

        librarianService.createLibrarian("Cristian", "12345678", "ccidbe@library.com");

        assertThrows(LibrarianAlreadyExistsException.class, () -> {
            librarianService.createLibrarian("David", "12345678", "ccidbe@library.com");
        });
    }

    // getLibrarianById()

    @Test
    void givenExistingLibrarian_whenGetLibrarianById_thenLibrarianReturned() {

        Librarian expected = librarianService.createLibrarian("Cristian", "12345678", "ccidbe@library.com");
        Librarian result = librarianService.getLibrarianById(expected.getId());

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getPassword(), result.getPassword());
        assertEquals(expected.getEmail(), result.getEmail());
    }

    @Test
    void givenNonExistingLibrarian_whenGetLibrarianById_thenLibrarianNotFoundExceptionThrown() {

        assertThrows(LibrarianNotFoundException.class, () ->
                librarianService.getLibrarianById(1));
    };

    // getLibrarianByEmail()

    @Test
    void givenExistingLibrarian_whenGetLibrarianByEmail_thenLibrarianReturned() {

        Librarian expected = librarianService.createLibrarian("Cristian", "12345678", "ccidbe@library.com");
        Librarian result = librarianService.getLibrarianByEmail(expected.getEmail());

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getPassword(), result.getPassword());
        assertEquals(expected.getEmail(), result.getEmail());
    }

    @Test
    void givenNonExistingLibrarian_whenGetLibrarianByEmail_thenLibrarianNotFoundExceptionThrown() {

        assertThrows(LibrarianNotFoundException.class, () -> {
            librarianService.getLibrarianByEmail("ccidbe@library.com");
        });
    }

    // updateLibrarian()

    @Test
    void givenExistingLibrarianAndCorrectParams_whenUpdateLibrarian_thenLibrarianUpdated() {

        Librarian librarian = librarianService.createLibrarian("Cristian", "12345678", "ccidbe@library.com");

        Librarian expected = new Librarian(1, "Cristian", "99999999", "ccidbe@library.com");
        Librarian result = librarianService.updateLibrarian(1, "Cristian", "99999999", "ccidbe@library.com");

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getPassword(),result.getPassword());
        assertEquals(expected.getEmail(), result.getEmail());
    }

    @Test
    void givenExistingEmail_whenUpdateLibrarian_thenLibrarianAlreadyExistsExceptionThrown() {

        Librarian librarian = librarianService.createLibrarian("Cristian", "12345678", "librarian@library.com");
        Librarian librarian2 = librarianService.createLibrarian("David", "12345678", "david@library.com");

        assertThrows(LibrarianAlreadyExistsException.class, () -> {
            librarianService.updateLibrarian(librarian2.getId(), "David", "12345678", "librarian@library.com");
        });
    }

    @Test
    void givenNonExistingLibrarian_whenUpdateLibrarian_thenLibrarianNotFoundExceptionThrown() {

        assertThrows(LibrarianNotFoundException.class, () -> {
            librarianService.updateLibrarian(1, "Cristian", "12345678", "ccidbe@library.com");
        });
    }

    // deleteLibrarian()

    @Test
    void givenExistingLibrarian_whenDeleteLibrarian_thenLibrarianDeleted() {

        Librarian librarian = librarianService.createLibrarian("Cristian" ,"12345678", "ccidbe@library.com");

        assertDoesNotThrow(() -> {
            librarianService.deleteLibrarian(librarian.getId());
        });

        assertThrows(LibrarianNotFoundException.class, () -> {
            librarianService.getLibrarianById(librarian.getId());
        });
    }

    @Test
    void givenNonExistingLibrarian_whenDeleteLibrarian_thenLibrarianNotFoundExceptionThrown() {

        assertThrows(LibrarianNotFoundException.class, () -> {
            librarianService.deleteLibrarian(1);
        });
    }
}
