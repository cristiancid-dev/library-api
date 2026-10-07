package com.cristiancid.library.service;

import com.cristiancid.library.exception.BookAlreadyReturnedException;
import com.cristiancid.library.exception.BookOnActiveLoanException;
import com.cristiancid.library.exception.LoanNotFoundException;
import com.cristiancid.library.exception.MemberLoanLimitExceededException;
import com.cristiancid.library.model.Author;
import com.cristiancid.library.model.Book;
import com.cristiancid.library.model.Loan;
import com.cristiancid.library.model.Member;
import com.cristiancid.library.repository.AuthorRepository;
import com.cristiancid.library.repository.BookRepository;
import com.cristiancid.library.repository.LoanRepository;
import com.cristiancid.library.repository.MemberRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LoanServiceTest {

    LoanRepository loanRepository = new LoanRepository();
    LoanService loanService = new LoanService(loanRepository);
    AuthorRepository authorRepository = new AuthorRepository();
    AuthorService authorService = new AuthorService(authorRepository);
    BookRepository  bookRepository = new BookRepository();
    BookService bookService = new BookService(bookRepository,authorRepository);
    MemberRepository memberRepository = new MemberRepository();
    MemberService memberService = new MemberService(memberRepository);

    // createLoan()

    @Test
    void givenCorrectParams_whenCreateLoan_thenLoanCreated() {

        Author author = authorService.createAuthor("J.K Rowling" , LocalDate.of(1965, 7,31));
        Book book = bookService.createBook("1234567890123", "Harry Potter and the Philosopher's Stone",
                LocalDate.of(1997, 6, 26), 1, "Fantasy");
        Member member = memberService.createMember("Cristian", "ccidbe@library.com", LocalDate.of(2000, 1, 6),
                "Test Avenue nº20, Barcelona", 123456789);

        Loan expected = new Loan(1, 1, "1234567890123",
                LocalDate.now(), LocalDate.now().plusDays(30));
        Loan result = loanService.createLoan(member.getId(), book.getIsbn());

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getMemberId(), result.getMemberId());
        assertEquals(expected.getBookIsbn(), result.getBookIsbn());
        assertEquals(expected.getIssueDate(), result.getIssueDate());
        assertEquals(expected.getDueDate(), result.getDueDate());
    }

    @Test
    void givenBorrowedBook_whenCreateLoan_thenBookOnActiveLoanExceptionThrown() {

        Author author = authorService.createAuthor("J.K Rowling" , LocalDate.of(1965, 7,31));
        Book book = bookService.createBook("1234567890123", "Harry Potter and the Philosopher's Stone",
                LocalDate.of(1997, 6, 26), 1, "Fantasy");
        Member member = memberService.createMember("Cristian", "ccidbe@library.com", LocalDate.of(2000, 1, 6),
                "Test Avenue nº20, Barcelona", 123456789);
        loanService.createLoan(member.getId(), book.getIsbn());

        assertThrows(BookOnActiveLoanException.class,() -> {
            loanService.createLoan(member.getId(), book.getIsbn());
        });

    }

    @Test
    void givenMemberWithLoanLimit_whenCreateLoan_thenMemberLoanLimitExceededExceptionThrown() {

        Author author = authorService.createAuthor("J.K Rowling" , LocalDate.of(1965, 7,31));
        Book book1 = bookService.createBook("0000000000001", "Harry Potter and the Philosopher's Stone",
                LocalDate.of(1997, 6, 26), 1, "Fantasy");

        Book book2 = bookService.createBook("0000000000002", "Harry Potter and the Chamber Of Secrets",
                LocalDate.of(1998, 7, 2), 1, "Fantasy");

        Book book3 = bookService.createBook("0000000000003", "Harry Potter and the Prisoner of Azkaban",
                LocalDate.of(1999, 7, 8), 1, "Fantasy");

        Book book4 = bookService.createBook("0000000000004", "Harry Potter and the Goblet of Fire",
                LocalDate.of(2000, 7, 8), 1, "Fantasy");

        Book book5 = bookService.createBook("0000000000005", "Harry Potter and the Order of the Phoenix",
                LocalDate.of(2003, 6, 21), 1, "Fantasy");

        Book book6 = bookService.createBook("0000000000006", "Harry Potter and the Half-Blood Prince",
                LocalDate.of(2005, 7, 16), 1, "Fantasy");
        Member member = memberService.createMember("Cristian", "ccidbe@library.com", LocalDate.of(2000, 1, 6),
                "Test Avenue nº20, Barcelona", 123456789);
        loanService.createLoan(member.getId(), book1.getIsbn());
        loanService.createLoan(member.getId(), book2.getIsbn());
        loanService.createLoan(member.getId(), book3.getIsbn());
        loanService.createLoan(member.getId(), book4.getIsbn());
        loanService.createLoan(member.getId(), book5.getIsbn());

        assertThrows(MemberLoanLimitExceededException.class, () -> {
            loanService.createLoan(member.getId(), book6.getIsbn());
        });
    }

    // returnBook()

    @Test
    void givenCorrectParams_whenReturnBook_thenBookReturned() {

        Author author = authorService.createAuthor("J.K Rowling" , LocalDate.of(1965, 7,31));
        Book book = bookService.createBook("1234567890123", "Harry Potter and the Philosopher's Stone",
                LocalDate.of(1997, 6, 26), 1, "Fantasy");
        Member member = memberService.createMember("Cristian", "ccidbe@library.com", LocalDate.of(2000, 1, 6),
                "Test Avenue nº20, Barcelona", 123456789);
        Loan loan = loanService.createLoan(member.getId(), book.getIsbn());

        loanService.returnBook(book.getIsbn());
        assertFalse(loan.isActive());
    }

    @Test
    void givenAlreadyReturnedBook_whenReturnBook_thenBookAlreadyReturnedExceptionThrown() {

        Author author = authorService.createAuthor("J.K Rowling" , LocalDate.of(1965, 7,31));
        Book book = bookService.createBook("1234567890123", "Harry Potter and the Philosopher's Stone",
                LocalDate.of(1997, 6, 26), 1, "Fantasy");
        Member member = memberService.createMember("Cristian", "ccidbe@library.com", LocalDate.of(2000, 1, 6),
                "Test Avenue nº20, Barcelona", 123456789);
        Loan loan = loanService.createLoan(member.getId(), book.getIsbn());
        loanService.returnBook(book.getIsbn());

        assertThrows(BookAlreadyReturnedException.class,() -> {
            loanService.returnBook(book.getIsbn());
        });
    }

    // getByMemberId()

    @Test
    void givenExistingMemberWithLoans_whenGetByMemberId_thenListOfLoansReturned() {

        Author author = authorService.createAuthor("J.K Rowling" , LocalDate.of(1965, 7,31));
        Book book1 = bookService.createBook("0000000000001", "Harry Potter and the Philosopher's Stone",
                LocalDate.of(1997, 6, 26), 1, "Fantasy");

        Book book2 = bookService.createBook("0000000000002", "Harry Potter and the Chamber Of Secrets",
                LocalDate.of(1998, 7, 2), 1, "Fantasy");

        Book book3 = bookService.createBook("0000000000003", "Harry Potter and the Prisoner of Azkaban",
                LocalDate.of(1999, 7, 8), 1, "Fantasy");
        Member member = memberService.createMember("Cristian", "ccidbe@library.com", LocalDate.of(2000, 1, 6),
                "Test Avenue nº20, Barcelona", 123456789);
        Loan loan1 = loanService.createLoan(member.getId(), book1.getIsbn());
        Loan loan2 = loanService.createLoan(member.getId(), book2.getIsbn());
        Loan loan3 = loanService.createLoan(member.getId(), book3.getIsbn());

        List<Loan> expected = List.of(loan1, loan2, loan3);
        List<Loan> result = loanService.getByMemberId(member.getId());

        assertEquals(expected, result);
    }

    @Test
    void givenExistingMemberWithNoLoans_WhenGetByMemberId_thenEmptyListReturned() {

        Member member = memberService.createMember("Cristian", "ccidbe@library.com", LocalDate.of(2000, 1, 6),
                "Test Avenue nº20, Barcelona", 123456789);

        List<Loan> expected = new ArrayList<>();
        List<Loan> result = loanService.getByMemberId(member.getId());

        assertEquals(expected, result);
    }

    // getActiveLoansByMemberId()

    @Test
    void givenExistingMemberWithActiveLoans_whenGetActiveLoansByMemberId_thenListOfActiveLoansReturned() {

        Author author = authorService.createAuthor("J.K Rowling" , LocalDate.of(1965, 7,31));
        Book book1 = bookService.createBook("0000000000001", "Harry Potter and the Philosopher's Stone",
                LocalDate.of(1997, 6, 26), 1, "Fantasy");

        Book book2 = bookService.createBook("0000000000002", "Harry Potter and the Chamber Of Secrets",
                LocalDate.of(1998, 7, 2), 1, "Fantasy");

        Book book3 = bookService.createBook("0000000000003", "Harry Potter and the Prisoner of Azkaban",
                LocalDate.of(1999, 7, 8), 1, "Fantasy");
        Member member = memberService.createMember("Cristian", "ccidbe@library.com", LocalDate.of(2000, 1, 6),
                "Test Avenue nº20, Barcelona", 123456789);
        Loan loan1 = loanService.createLoan(member.getId(), book1.getIsbn());
        Loan loan2 = loanService.createLoan(member.getId(), book2.getIsbn());
        Loan loan3 = loanService.createLoan(member.getId(), book3.getIsbn());

        List<Loan> expected = List.of(loan1, loan2, loan3);
        List<Loan> result = loanService.getActiveLoansByMemberId(member.getId());

        assertEquals(expected, result);
    }

    @Test
    void givenExistingMemberWithNoActiveLoans_WhenGetActiveLoansByMemberId_thenEmptyListReturned() {

        Author author = authorService.createAuthor("J.K Rowling" , LocalDate.of(1965, 7,31));
        Book book = bookService.createBook("0000000000001", "Harry Potter and the Philosopher's Stone",
                LocalDate.of(1997, 6, 26), 1, "Fantasy");

        Member member = memberService.createMember("Cristian", "ccidbe@library.com", LocalDate.of(2000, 1, 6),
                "Test Avenue nº20, Barcelona", 123456789);
        loanService.createLoan(member.getId(), book.getIsbn());
        loanService.returnBook(book.getIsbn());

        List<Loan> expected = new ArrayList<>();
        List<Loan> result = loanService.getActiveLoansByMemberId(member.getId());

        assertEquals(expected, result);
    }

    // getLoansByBookIsbn()

    @Test
    void givenBookWithLoans_whenGetLoansByBookIsbn_thenListOfLoansReturned() {

        Author author = authorService.createAuthor("J.K Rowling" , LocalDate.of(1965, 7,31));
        Book book = bookService.createBook("0000000000001", "Harry Potter and the Philosopher's Stone",
                LocalDate.of(1997, 6, 26), 1, "Fantasy");

        Member member1 = memberService.createMember("Cristian", "ccidbe@library.com", LocalDate.of(2000, 1, 6),
                "Test Avenue nº20, Barcelona", 123456789);
        Member member2 = memberService.createMember("David", "david@library.com", LocalDate.of(1990, 6, 23),
                "Test Avenue nº3, Sydney", 987654321);
        Loan loan1 = loanService.createLoan(member1.getId(), book.getIsbn());
        loanService.returnBook(book.getIsbn());
        Loan loan2 = loanService.createLoan(member2.getId(),book.getIsbn());

        List<Loan> expected = List.of(loan1, loan2);
        List<Loan> result = loanService.getLoansByBookIsbn(book.getIsbn());

        assertEquals(expected, result);
    }

    @Test
    void givenBookWithNoLoans_whenGetLoansByBookIsbn_thenEmptyListReturned() {

        Author author = authorService.createAuthor("J.K Rowling" , LocalDate.of(1965, 7,31));
        Book book = bookService.createBook("0000000000001", "Harry Potter and the Philosopher's Stone",
                LocalDate.of(1997, 6, 26), 1, "Fantasy");

        List<Loan> expected = new ArrayList<>();
        List<Loan> result = loanService.getLoansByBookIsbn(book.getIsbn());

        assertEquals(expected, result);
    }

    // getActiveLoanByBookIsbn()

    @Test
    void givenBookWithActiveLoan_whenGetActiveLoanByBookIsbn_thenLoanReturned() {

        Author author = authorService.createAuthor("J.K Rowling" , LocalDate.of(1965, 7,31));
        Book book = bookService.createBook("0000000000001", "Harry Potter and the Philosopher's Stone",
                LocalDate.of(1997, 6, 26), 1, "Fantasy");

        Member member = memberService.createMember("Cristian", "ccidbe@library.com", LocalDate.of(2000, 1, 6),
                "Test Avenue nº20, Barcelona", 123456789);

        Loan expected = loanService.createLoan(member.getId(), book.getIsbn());
        Loan result = loanService.getActiveLoanByBookIsbn(book.getIsbn());

        assertEquals(expected, result);

    }

    @Test
    void givenBookWithNoActiveLoan_whenGetActiveLoanByBookIsbn_thenLoanNotFoundExceptionThrown() {

        Author author = authorService.createAuthor("J.K Rowling" , LocalDate.of(1965, 7,31));
        Book book = bookService.createBook("0000000000001", "Harry Potter and the Philosopher's Stone",
                LocalDate.of(1997, 6, 26), 1, "Fantasy");

        assertThrows(LoanNotFoundException.class, () -> {
            loanService.getActiveLoanByBookIsbn(book.getIsbn());
        });
    }

}
