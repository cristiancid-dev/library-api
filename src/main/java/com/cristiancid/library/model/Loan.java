package com.cristiancid.library.model;

import com.cristiancid.library.model.enums.Status;

import java.time.LocalDate;

public class Loan {

    // A class representing Loans

    private final int id;
    private int memberId;
    private String bookIsbn;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Status status;

    public Loan(int id, int memberId, String bookIsbn, LocalDate issueDate, LocalDate dueDate) {
        this.id = id;
        this.memberId = memberId;
        this.bookIsbn = bookIsbn;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        returnDate = null;
        status = Status.ACTIVE;
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

    public Status getStatus() {
        return status;
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

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isActive() {
        return status == Status.ACTIVE;
    }

    public boolean isOverDue() {
        return isActive() && dueDate.isBefore(LocalDate.now());
    }

    public void returnBook() {
        this.returnDate = LocalDate.now();
        this.status = Status.RETURNED;
    }

    @Override
    public String toString() {
        return "Id: " + id + " Member Id: " + memberId + " Book ISBN: " + bookIsbn +
                " Issue date: " + issueDate + " Due date: " + dueDate;
    }
}
