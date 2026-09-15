package com.cristiancid.library.model;

public class Librarian {

    // A class representing the staff of the library

    private final int id;
    private String name;
    private String password;
    private String email;

    public Librarian(int id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }


    // Getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
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

    public void setPassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("password cannot be null");
        }

        password = password.trim();
        if (password.isEmpty()) {
            throw new IllegalArgumentException("password cannot be empty");
        }

        if (password.length() < 8 || password.length() > 20) {
            throw new IllegalArgumentException("password length must be between 8 and 20");
        }

        if (!password.matches(".*\\d.*")) {
            throw new IllegalArgumentException("password must contain at least one digit");
        }

        if (password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("password must contain at least one capital letter");
        }

        this.password = password;
    }

    public void setEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("email cannot be null");
        }

        email = email.trim();
        if (email.isEmpty()) {
            throw new IllegalArgumentException("email cannot be empty");
        }

        if (!email.contains("@library.com")) {
            throw new IllegalArgumentException("email format must be (youremail@library.com");
        }

        if (email.length() > 40) {
            throw new IllegalArgumentException("email length cannot be larger than 40");
        }

        this.email = email;

    }

    @Override
    public String toString() {
        return "Id: " + id + " Name: " + name + " Email: " + email;
    }
}
