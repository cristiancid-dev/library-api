package main.java.model;

import java.time.LocalDate;

public class Book {

    // A class representing a book

    private final String isbn;
    private final String title;
    private final LocalDate publicationDate;
    private Author author;
    private String category;

    public Book(String isbn, String title, LocalDate publicationDate, Author author, String category) {
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


    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn + " Title: " + title + " Publication date: " + publicationDate +
                " Author: " + author + " Category: " + category;
    }
}

