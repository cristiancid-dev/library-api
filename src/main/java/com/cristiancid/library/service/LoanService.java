package com.cristiancid.library.service;

import com.cristiancid.library.exception.BookAlreadyReturnedException;
import com.cristiancid.library.exception.BookOnActiveLoanException;
import com.cristiancid.library.exception.LoanNotFoundException;
import com.cristiancid.library.exception.MemberLoanLimitExceededException;
import com.cristiancid.library.model.Loan;
import com.cristiancid.library.repository.LoanRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanService {

    private final LoanRepository loanRepository;
    private int nextLoanId = 1;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan createLoan(int memberId, String bookIsbn) {
        // TODO: Check that the introduced memberId and bookIsbn exist

        if (loanRepository.findActiveLoanByBookIsbn(bookIsbn).isPresent()) {
            throw new BookOnActiveLoanException("Book with ISBN '" + bookIsbn + "' is currently borrowed");
        }

        if (getActiveLoansByMemberId(memberId).size() >= 5) {
            throw new MemberLoanLimitExceededException("Loan limit reached for member with id '" + memberId);
        }

        Loan loan = new Loan(nextLoanId, memberId, bookIsbn, LocalDate.now(),
                LocalDate.now().plusDays(30) );
        nextLoanId++;

        loanRepository.saveLoan(loan);
        return loan;
    }

    public void returnBook(String bookIsbn) {
        // TODO: Check that the introduced bookIsbn is exists

        Loan loan = loanRepository.findActiveLoanByBookIsbn(bookIsbn)
                .orElseThrow(() -> new BookAlreadyReturnedException
                        ("Book with ISBN '" + bookIsbn + "' is not currently borrowed"));
        loan.returnBook();
    }

    public List<Loan> getByMemberId(int memberId) {
        return loanRepository.findByMemberId(memberId);
    }

    public List<Loan> getActiveLoansByMemberId(int memberId) {
        List<Loan> activeLoans = new ArrayList<>();
        List<Loan> allLoans = getByMemberId(memberId);

        for (Loan loan : allLoans) {
            if (loan.isActive()) {
                activeLoans.add(loan);
            }
        }

        return activeLoans;
    }

    public List<Loan> getLoansByBookIsbn(String bookIsbn) {
        return loanRepository.findLoansByBookIsbn(bookIsbn);
    }

    public Loan getActiveLoanByBookIsbn(String bookIsbn) {
        return loanRepository.findActiveLoanByBookIsbn(bookIsbn)
                .orElseThrow(() -> new LoanNotFoundException("Book with ISBN '" + bookIsbn + "' has no active loan"));
    }

}
