package com.cristiancid.library.service;

import com.cristiancid.library.exception.LibrarianAlreadyExistsException;
import com.cristiancid.library.exception.LibrarianNotFoundException;
import com.cristiancid.library.model.Librarian;
import com.cristiancid.library.repository.LibrarianRepository;

import java.util.Optional;

public class LibrarianService {

    private final LibrarianRepository librarianRepository;
    private int nextLibrarianId = 1;

    public LibrarianService(LibrarianRepository librarianRepository) {
        this.librarianRepository = librarianRepository;
    }

    public Librarian createLibrarian(String name, String password, String email) {
        if (librarianRepository.findByEmail(email).isPresent()) {
            throw new LibrarianAlreadyExistsException("Librarian with email '" + email + "' Already exists");
        }
        Librarian librarian = new Librarian(nextLibrarianId, name, password,email);
        nextLibrarianId++;
        librarianRepository.saveLibrarian(librarian);
        return librarian;
    }

    public Librarian getLibrarianById(int id) {
        return librarianRepository.findById(id)
                .orElseThrow(() -> new LibrarianNotFoundException("Librarian with id '" + id + "' not found"));
    }

    public Librarian getLibrarianByEmail(String email) {
        return librarianRepository.findByEmail(email)
                .orElseThrow(() -> new LibrarianNotFoundException("Librarian with email '" + email + "' not found"));
    }

    public Librarian updateLibrarian(int id, String name, String password, String email) {
        librarianRepository.findById(id)
                .orElseThrow(() -> new LibrarianNotFoundException("Librarian with id '" + id + "' not found"));

        Optional<Librarian> librarianByEmail = librarianRepository.findByEmail(email);
        if (librarianByEmail.isPresent()) {
            Librarian librarian = librarianByEmail.get();
            if (librarian.getId() != id) {
                throw new LibrarianAlreadyExistsException("Librarian with email '" + email + "' already exists");
            }
        }

        Librarian librarian = new Librarian(id,name, password, email);
        return librarianRepository.updateLibrarian(id, librarian)
                .orElseThrow(() -> new LibrarianNotFoundException("Librarian with id '" + id + "' not found"));
    }

    public void deleteLibrarian(int id) {
        librarianRepository.findById(id)
                .orElseThrow(() -> new LibrarianNotFoundException("Librarian with id '" + id + "' not found"));
        librarianRepository.deleteLibrarian(id);
    }
}
