package com.cristiancid.library.repository;

import com.cristiancid.library.model.Loan;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoanRepository {

    private final List<Loan> loans =  new ArrayList<>();

    public void saveLoan(Loan loan) {
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

    public List<Loan> findLoansByBookIsbn(String bookIsbn) {
        List<Loan> bookLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.getBookIsbn().equals(bookIsbn)){
                bookLoans.add(loan);
            }
        }
        return bookLoans;
    }

    public Optional<Loan> findActiveLoanByBookIsbn(String bookIsbn) {
        for (Loan loan : loans) {
            if (loan.getBookIsbn().equals(bookIsbn) && loan.isActive()) {
                return Optional.of(loan);
            }
        }
        return Optional.empty();
    }
}
