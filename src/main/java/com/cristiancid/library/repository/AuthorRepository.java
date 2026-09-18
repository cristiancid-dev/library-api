package com.cristiancid.library.repository;

import com.cristiancid.library.model.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorRepository {

    private final List<Author> authors = new ArrayList<>();

    public void saveAuthor(Author author) {
        authors.add(author);
    }

    public Optional<Author> findById(int id) {
        for (Author author:authors) {
            if (author.getId() == id) {
                return Optional.of(author);
            }
        }
        return Optional.empty();
    }

    public Optional<Author> updateAuthor(int id, Author author) {
        if (findById(id).isEmpty()) {
            return Optional.empty();
        }
        int index = authors.indexOf(findById(id).get());
        authors.set(index,author);
        return Optional.of(author);
    }

    public void deleteAuthor(int id) {
        authors.remove(findById(id).get());
    }
}
