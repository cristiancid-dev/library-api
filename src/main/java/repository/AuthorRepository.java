package main.java.repository;

import main.java.model.Author;
import main.java.model.exception.AuthorAlreadyExistsException;
import main.java.model.exception.AuthorNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorRepository {

    private final List<Author> authors = new ArrayList<>();

    public void saveAuthor(Author author) {
        for (Author author1 : authors) {
            if (author1.equals(author)) {
                throw new AuthorAlreadyExistsException("Author already exists");
            }
        }
        authors.add(author);
    }

    public Optional<Author> findById(int id) {
        for (Author author:authors) {
            if (author.getId() == id) {
                return Optional.of(author);
            }
        }
        throw new AuthorNotFoundException("Author with id '" + id + "' not found");
    }
}
