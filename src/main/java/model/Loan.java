package main.java.model;

import java.time.LocalDate;

public class Loan {

    // A class representing Loans

    private final int id;
    private int memberId;
    private String bookIsbn;
    private LocalDate issueDate;
    private LocalDate dueDate;

    public Loan(int id, int memberId, String bookIsbn, LocalDate issueDate, LocalDate dueDate) {
        this.id = id;
        this.memberId = memberId;
        this.bookIsbn = bookIsbn;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    // Getters


    public int getId() {
        return id;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    // Setters


    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
