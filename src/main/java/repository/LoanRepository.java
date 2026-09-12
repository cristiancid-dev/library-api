package main.java.repository;

import main.java.model.Loan;
import main.java.exception.BookAlreadyExistsException;
import main.java.exception.LoanNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoanRepository {

    private final List<Loan> loans =  new ArrayList<>();

    public void saveLoan(Loan loan) {
        for (Loan loan1 : loans) {
            if (loan1.getBookIsbn() == loan.getBookIsbn()) {
                throw new BookAlreadyExistsException("This book is currently loaned");
            }
        }
        loans.add(loan);
    }

    public List<Loan> findByMemberId (int memberId) {
        List<Loan> memberLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.getMemberId() == memberId) {
                memberLoans.add(loan);
            }
        }
        return memberLoans;
    }

    public Optional<Loan> findByBookIsbn (String bookIsbn) {
        for (Loan loan : loans) {
            if (loan.getBookIsbn().equals(bookIsbn)) {
                return Optional.of(loan);
            }
        }
        throw new LoanNotFoundException("Book with ISBN '" + bookIsbn + "' has no active loan");
    }
}
