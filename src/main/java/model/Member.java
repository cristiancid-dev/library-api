package main.java.model;

import java.time.LocalDate;

public class Member {

    // A class representing a member of the library

    private final int id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private String address;
    private int phoneNumber;

    public Member(int id, String name, String email, LocalDate birthDate, String address, int phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    //Getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    //Setters

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

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setAddress(String adress) {
        if (adress == null) {
            throw new IllegalArgumentException("adress cannot be null");
        }

        this.address = adress;
    }

    public void setPhoneNumber(int phoneNumber) {
        if (phoneNumber < 4 || phoneNumber > 15) {
            throw new IllegalArgumentException("phone number length must be between 4 and 15 digits");
        }
    }
}
