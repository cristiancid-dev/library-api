package main.java.model;

import java.time.LocalDate;

public class Loan {

    // A class representing Loans

    private final int id;
    private int memberId;
    private int bookId;
    private LocalDate issueDate;
    private LocalDate dueDate;

    public Loan(int id, int memberId, int bookId, LocalDate issueDate, LocalDate dueDate) {
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId;
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

    public int getBookId() {
        return bookId;
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

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
