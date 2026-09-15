package com.cristiancid.library.model;

import java.time.LocalDate;

public class Author {

    // A class representing a book author

    private final int id;
    private String name;
    private LocalDate birthDate;

    public Author(int id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }


    // Setters

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }

        name = name.trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be empty");
        }

        if (name.matches(".*\\d.*")) {
            throw new IllegalArgumentException("name cannot contain digits");
        }

        if (name.length() < 2 || name.length() > 30) {
            throw new IllegalArgumentException("name length must be between 2 and 30");
        }

        name = name.toLowerCase();
        this.name =name.substring(0,1).toUpperCase() + name.substring(1);
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Id: " + id + " Name: " + name + " Birth date: " + birthDate;
    }
}
