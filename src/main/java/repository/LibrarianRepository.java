package main.java.repository;

import main.java.model.Librarian;
import main.java.exception.LibrarianAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;

public class LibrarianRepository {

    private final List<Librarian> librarians = new ArrayList<>();

    public void saveLibrarian(Librarian librarian) {
        for (Librarian librarian1:librarians) {
            if (librarian1.getEmail().equals(librarian.getEmail())) {
                throw new LibrarianAlreadyExistsException("Librarian with email '" + librarian.getEmail() + "' already exists");
            }
        }
        librarians.add(librarian);
    }
}
