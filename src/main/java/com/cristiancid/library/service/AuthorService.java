package com.cristiancid.library.service;

import com.cristiancid.library.exception.AuthorNotFoundException;
import com.cristiancid.library.model.Author;
import com.cristiancid.library.repository.AuthorRepository;

import java.time.LocalDate;

public class AuthorService {

    private final AuthorRepository authorRepository;
    private int nextAuthorId = 1;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author createAuthor(String name, LocalDate birthDate) {
        Author author = new Author(nextAuthorId, name, birthDate);
        nextAuthorId++;
        authorRepository.saveAuthor(author);
        return authorRepository.findById(author.getId()).get();
    }

    public Author getAuthorById(int id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author with id '" + id + "' not found"));
    }

    public Author updateAuthor(int id, String name, LocalDate BirthDate) {
        Author author = new Author(id,name, BirthDate);

        return authorRepository.updateAuthor(id, author)
                .orElseThrow(() -> new AuthorNotFoundException("Author with id '" + id + "' not found"));
    }

    public void deleteAuthor(int id) {
        authorRepository.findById(id)
                        .orElseThrow(() -> new AuthorNotFoundException("Author with id '" + id + "' not found"));
        authorRepository.deleteAuthor(id);
    }
}
