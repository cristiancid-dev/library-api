package com.cristiancid.library.repository;

import com.cristiancid.library.model.Librarian;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibrarianRepositoryTest {

    LibrarianRepository librarianRepository = new LibrarianRepository();

    // saveLibrarian()

    @Test
    void givenLibrarian_whenSaveLibrarian_thenLibrarianSaved() {

        Librarian librarian = new Librarian(1, "Cristian", "12345678", "ccidbe@library.com");

        librarianRepository.saveLibrarian(librarian);

        Optional<Librarian> expected = Optional.of(librarian);
        Optional<Librarian> result = librarianRepository.findById(1);

        assertEquals(expected,result);
    }

    // findById()

    @Test
    void givenExistingLibrarian_whenFindById_thenOptionalOfLibrarianReturned() {

        Librarian librarian = new Librarian(1, "Cristian", "12345678", "ccidbe@library.com");
        librarianRepository.saveLibrarian(librarian);

        Optional<Librarian> expected = Optional.of(librarian);
        Optional<Librarian> result = librarianRepository.findById(1);

        assertEquals(expected, result);
    }

    @Test
    void givenNonExistingLibrarian_whenFindById_thenOptionalEmptyReturned() {

        Optional<Librarian> expected = Optional.empty();
        Optional<Librarian> result = librarianRepository.findById(1);

        assertEquals(expected, result);
    }

    // findByEmail()

    @Test
    void givenExistingEmail_whenFindByEmail_thenOptionalOfLibrarianReturned() {

        Librarian librarian = new Librarian(1, "Cristian", "12345678", "ccidbe@library.com");
        librarianRepository.saveLibrarian(librarian);

        Optional<Librarian> expected = Optional.of(librarian);
        Optional<Librarian> result = librarianRepository.findByEmail("ccidbe@library.com");

        assertEquals(expected, result);
    }

    @Test
    void givenNonExistingEmail_whenFindByEmail_thenOptionalEmptyReturned() {

        Optional<Librarian> expected = Optional.empty();
        Optional<Librarian> result = librarianRepository.findByEmail("ccidbe@library.com");

        assertEquals(expected, result);
    }

    // updateLibrarian()

    @Test
    void givenExistingLibrarian_whenUpdateLibrarian_thenOptionalOfUpdatedLibrarianReturned() {

        Librarian librarian = new Librarian(1, "Cristian", "12345678", "ccidbe@library.com");
        librarianRepository.saveLibrarian(librarian);
        Librarian updatedLibrarian = new Librarian(1, "Cristian", "87654321", "cristian@library.com");

        Optional<Librarian> expected = Optional.of(updatedLibrarian);
        Optional<Librarian> result = librarianRepository.updateLibrarian(1, updatedLibrarian);

        assertEquals(expected, result);


    }

    @Test
    void givenNonExistingLibrarian_whenUpdateLibrarian_thenOptionalEmptyReturned() {

        Librarian librarian = new Librarian(1, "Cristian", "12345678", "ccidbe@library.com");

        Optional<Librarian> expected = Optional.empty();
        Optional<Librarian> result = librarianRepository.updateLibrarian(1, librarian);

        assertEquals(expected,result);
    }

    // deleteLibrarian()

    @Test
    void givenLibrarian_whenDeleteLibrarian_thenLibrarianDeleted() {

        Librarian librarian = new Librarian(1, "Cristian", "12345678", "ccidbe@library.com");
        librarianRepository.saveLibrarian(librarian);

        librarianRepository.deleteLibrarian(1);

        Optional<Librarian> expected = Optional.empty();
        Optional<Librarian> result = librarianRepository.findById(1);

        assertEquals(expected, result);
    }
}
