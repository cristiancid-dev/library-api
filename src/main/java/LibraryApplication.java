package main.java;

import main.java.model.Author;
import main.java.model.Book;
import main.java.model.Member;
import main.java.repository.AuthorRepository;
import main.java.repository.BookRepository;
import main.java.repository.MemberRepository;

import java.time.LocalDate;
import java.util.Optional;

public class LibraryApplication {
    public static void main(String[] args) {
        AuthorRepository authorRepository = new AuthorRepository();
        BookRepository bookRepository = new BookRepository();
        MemberRepository memberRepository = new MemberRepository();

        Author author = new Author(1, "JKRowling", LocalDate.of(1965, 7, 31));
        authorRepository.saveAuthor(author);
        Author JKRowling = authorRepository.findById(1).get();

        Book book = new Book("9788831020787", "Harry Potter And The Philosopher's Stone",
                LocalDate.of(1997, 6,26), JKRowling,"Fantasy" );
        bookRepository.saveBook(book);
        Book savedBook = bookRepository.findByIsbn("9788831020787").get();

        Member member = new Member(1, "Cristian", "ccidbe@gmail.com",
                LocalDate.of(2000, 1, 6), "Av del Onze de Setembre, 20", 697545660);
        memberRepository.saveMember(member);
        Optional<Member> optionalMember = memberRepository.findById(1);
        System.out.println(optionalMember.toString());
    }
}
