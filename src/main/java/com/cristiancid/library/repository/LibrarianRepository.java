package com.cristiancid.library.repository;

import com.cristiancid.library.model.Librarian;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibrarianRepository {

    private final List<Librarian> librarians = new ArrayList<>();

    public void saveLibrarian(Librarian librarian) {
        librarians.add(librarian);
    }

    public Optional<Librarian> findById(int id) {
        for (Librarian librarian:librarians) {
            if (librarian.getId() == id) {
                return Optional.of(librarian);
            }
        }
        return Optional.empty();
    }

    public  Optional<Librarian> findByEmail(String email) {
        for (Librarian librarian:librarians) {
            if (librarian.getEmail().equals(email)) {
                return Optional.of(librarian);
            }
        }
        return Optional.empty();
    }

    public Optional<Librarian> updateLibrarian(int id, Librarian librarian) {
        if (findById(id).isEmpty()) {
            return Optional.empty();
        }
        int index = librarians.indexOf(findById(id).get());
        librarians.set(index,librarian);
        return Optional.of(librarian);
    }

    public void deleteLibrarian(int id) {
        librarians.remove(findById(id).get());
    }
}
