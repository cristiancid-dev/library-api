package com.cristiancid.library.repository;

import com.cristiancid.library.model.Author;
import com.cristiancid.library.model.Book;
import com.cristiancid.library.model.Loan;
import com.cristiancid.library.model.Member;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoanRepositoryTest {

    LoanRepository loanRepository = new LoanRepository();

    // saveLoan()

    @Test
    void givenLoan_whenSaveLoan_thenLoanSaved() {

        Author author = new Author(1,"J.K Rowling" , LocalDate.of(1965, 7,31));
        Book book = new Book("1234567890123", "Harry Potter and the Philosopher's Stone",
                LocalDate.of(1997, 6, 26), author, "Fantasy");
        Member member = new Member(1, "Cristian", "ccidbe@library.com", LocalDate.of(2000, 1, 6),
                "Test Avenue nº20, Barcelona", "123456789");
        Loan loan = new Loan(1, 1, "1234567890123", LocalDate.now(), LocalDate.now().plusDays(30));

        loanRepository.saveLoan(loan);

        Optional<Loan> expected = Optional.of(loan);
        Optional<Loan> result = loanRepository.findActiveLoanByBookIsbn(book.getIsbn());

        assertEquals(expected,result);
    }

    // findByMemberId()

    @Test
    void givenMemberWithLoans_whenFindByMemberId_thenListOfLoansReturned() {

        Author author = new Author(1,"J.K Rowling" , LocalDate.of(1965, 7,31));
        Book book1 = new Book("0000000000001", "Harry Potter and the Philosopher's Stone",
                LocalDate.of(1997, 6, 26), author, "Fantasy");
        Book book2 = new Book("0000000000002", "Harry Potter and the Chamber Of Secrets",
                LocalDate.of(1998, 7, 2), author, "Fantasy");

        Member member = new Member(1, "Cristian", "ccidbe@library.com", LocalDate.of(2000, 1, 6),
                "Test Avenue nº20, Barcelona", "123456789");
        Loan loan1 = new Loan(1, 1, "0000000000001", LocalDate.now(), LocalDate.now().plusDays(30));
        Loan loan2 = new Loan(2, 1, "0000000000002", LocalDate.now(), LocalDate.now().plusDays(30));
        loanRepository.saveLoan(loan1);
        loanRepository.saveLoan(loan2);

        List<Loan> expected = List.of(loan1, loan2);
        List<Loan> result = loanRepository.findByMemberId(member.getId());

        assertEquals(expected, result);

    }

    @Test
    void givenMemberWithNoLoans_whenFindByMemberId_thenEmptyListReturned() {

        Member member = new Member(1, "Cristian", "ccidbe@library.com", LocalDate.of(2000, 1, 6),
                "Test Avenue nº20, Barcelona", "123456789");

        List<Loan> expected = new ArrayList<>();
        List<Loan> result = loanRepository.findByMemberId(member.getId());

        assertEquals(expected, result);
    }

    // findLoansByBookIsbn()

    @Test
    void givenBookWithLoans_whenFindLoansByBookIsbn_thenListOfLoansReturned() {

        Author author = new Author(1,"J.K Rowling" , LocalDate.of(1965, 7,31));
        Book book = new Book("1234567890123", "Harry Potter and the Philosopher's Stone",
                LocalDate.of(1997, 6, 26), author, "Fantasy");
        Member member1 = new Member(1, "Cristian", "ccidbe@library.com", LocalDate.of(2000, 1, 6),
                "Test Avenue nº20, Barcelona", "123456789");
        Member member2 = new Member(2, "David", "david@library.com", LocalDate.of(2000, 1, 6),
                "Test Avenue nº20, Barcelona", "987654321");
        Loan loan1 = new Loan(1, 1, "1234567890123", LocalDate.now(), LocalDate.now().plusDays(30));
        Loan loan2 = new Loan(2, 2, "1234567890123", LocalDate.now(), LocalDate.now().plusDays(30));
        loanRepository.saveLoan(loan1);
        loanRepository.saveLoan(loan2);

        List<Loan> expected = List.of(loan1, loan2);
        List<Loan> result = loanRepository.findLoansByBookIsbn(book.getIsbn());

        assertEquals(expected, result);
    }

    @Test
    void givenBookWithNoLoans_whenFindLoansByBookIsbn_thenEmptyListReturned() {

        Author author = new Author(1,"J.K Rowling" , LocalDate.of(1965, 7,31));
        Book book = new Book("1234567890123", "Harry Potter and the Philosopher's Stone",
                LocalDate.of(1997, 6, 26), author, "Fantasy");

        List<Loan> expected = new ArrayList<>();
        List<Loan> result = loanRepository.findLoansByBookIsbn(book.getIsbn());

        assertEquals(expected, result);
    }

    // findActiveLoansByBookIsbn()

    @Test
    void givenBookWithActiveLoan_whenFindActiveLoanByBookIsbn_thenOptionalOfLoanReturned() {

        Author author = new Author(1,"J.K Rowling" , LocalDate.of(1965, 7,31));
        Book book = new Book("1234567890123", "Harry Potter and the Philosopher's Stone",
                LocalDate.of(1997, 6, 26), author, "Fantasy");
        Member member = new Member(1, "Cristian", "ccidbe@library.com", LocalDate.of(2000, 1, 6),
                "Test Avenue nº20, Barcelona", "123456789");
        Loan loan = new Loan(1, 1, "1234567890123", LocalDate.now(), LocalDate.now().plusDays(30));
        loanRepository.saveLoan(loan);

        Optional<Loan> expected = Optional.of(loan);
        Optional<Loan> result = loanRepository.findActiveLoanByBookIsbn(book.getIsbn());

        assertEquals(expected, result);
    }

    @Test
    void givenBookWithNoActiveLoan_whenFindActiveLoanByBookIsbn_thenOptionalEmptyReturned() {

        Author author = new Author(1,"J.K Rowling" , LocalDate.of(1965, 7,31));
        Book book = new Book("1234567890123", "Harry Potter and the Philosopher's Stone",
                LocalDate.of(1997, 6, 26), author, "Fantasy");

        Optional<Loan> expected = Optional.empty();
        Optional<Loan> result = loanRepository.findActiveLoanByBookIsbn(book.getIsbn());
    }
}
