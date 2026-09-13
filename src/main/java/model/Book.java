package main.java.model;

import java.time.LocalDate;

public class Book {

    // A class representing a book

    private final String isbn;
    private String title;
    private LocalDate publicationDate;
    private Author author;
    private String category;

    public Book(String isbn, String title, LocalDate publicationDate, Author author, String category) {
        if (isbn == null) {
            throw new IllegalArgumentException("ISBN cannot be null");
        }
        if (isbn.length() != 13) {
            throw new IllegalArgumentException("ISBN length must be 13 digits");
        }
        this.isbn = isbn;
        this.title = title;
        this.publicationDate = publicationDate;
        this.author = author;
        this.category = category;
    }

    // Getters

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public Author getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }


    // Setters


    public void setTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException("title cannot be null");
        }

        title = title.trim();

        if (title.isEmpty()) {
            throw new IllegalArgumentException("title cannot be empty");
        }

        this.title = title;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        if (publicationDate == null) {
            throw new IllegalArgumentException("publication date cannot be null");
        }
        this.publicationDate = publicationDate;
    }

    public void setAuthor(Author author) {
        if (author == null) {
            throw new IllegalArgumentException("author cannot be null");
        }
        this.author = author;
    }

    public void setCategory(String category) {
        if (category == null) {
            throw new IllegalArgumentException("category cannot be null");
        }

        category = category.trim();

        if (category.isEmpty()) {
            throw new IllegalArgumentException("category cannot be empty");
        }
        this.category = category;
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn + " Title: " + title + " Publication date: " + publicationDate +
                " Author: " + author + " Category: " + category;
    }
}

