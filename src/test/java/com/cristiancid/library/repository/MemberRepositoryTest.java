package com.cristiancid.library.repository;

import com.cristiancid.library.model.Member;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemberRepositoryTest {

    MemberRepository memberRepository = new MemberRepository();

    // saveMember()

    @Test
    void givenMember_whenSaveMember_thenMemberSaved() {

        Member member = new Member(1, "Cristian", "ccidbe@library.com", LocalDate.of(2000,1,6),
                "Test Street nº20, Barcelona", 123456789 );

        memberRepository.saveMember(member);

        Optional<Member> expected = Optional.of(member);
        Optional<Member> result = memberRepository.findById(1);

        assertEquals(expected, result);
    }

    // findById()

    @Test
    void givenExistingMember_whenFindById_thenOptionalOfMemberReturned() {

        Member member = new Member(1, "Cristian", "ccidbe@library.com", LocalDate.of(2000,1,6),
                "Test Street nº20, Barcelona", 123456789 );
        memberRepository.saveMember(member);

        Optional<Member> expected = Optional.of(member);
        Optional<Member> result = memberRepository.findById(1);

        assertEquals(expected, result);
    }

    @Test
    void givenNonExistingMember_whenFindById_thenOptionalEmptyReturned() {

        Optional<Member> expected = Optional.empty();
        Optional<Member> result = memberRepository.findById(1);

        assertEquals(expected, result);
    }

    // findByEmail()

    @Test
    void givenExistingMember_whenFindByEmail_thenOptionalOfMemberReturned() {

        String email = "ccidbe@library.com";
        Member member = new Member(1, "Cristian", email, LocalDate.of(2000,1,6),
                "Test Street nº20, Barcelona", 123456789 );
        memberRepository.saveMember(member);

        Optional<Member> expected = Optional.of(member);
        Optional<Member> result = memberRepository.findByEmail(email);

        assertEquals(expected, result);
    }

    @Test
    void givenNonExistingMember_whenFindByEmail_thenOptionalEmptyReturned() {

        Optional<Member> expected = Optional.empty();
        Optional<Member> result = memberRepository.findByEmail("ccidbe@library.com");

        assertEquals(expected, result);
    }

    // findByPhoneNumber()

    @Test
    void givenExistingMember_whenFindByPhoneNumber_thenOptionalOfMemberReturned() {

        int phoneNumber = 123456789;
        Member member = new Member(1, "Cristian", "ccidbe@library.com", LocalDate.of(2000,1,6),
                "Test Street nº20, Barcelona", phoneNumber );
        memberRepository.saveMember(member);

        Optional<Member> expected = Optional.of(member);
        Optional<Member> result = memberRepository.findByPhoneNumber(phoneNumber);

        assertEquals(expected, result);
    }

    @Test
    void givenNonExistingMember_whenFindByPhoneNumber_thenOptionalEmptyReturned() {

        Optional<Member> expected = Optional.empty();
        Optional<Member> result = memberRepository.findByPhoneNumber(123456789);

        assertEquals(expected, result);
    }

    // updateMember()

    @Test
    void givenExistingMember_whenUpdateMember_thenOptionalOfUpdatedMemberReturned() {

        Member member = new Member(1, "Cristian", "ccidbe@library.com", LocalDate.of(2000,1,6),
                "Test Street nº20, Barcelona", 123456789 );
        memberRepository.saveMember(member);
        Member updatedMember = new Member(1, "David", "david@library.com", LocalDate.of(2005, 4, 21),
                "Test Avenue nº 3, Madrid", 987654321);

        Optional<Member> expected = Optional.of(updatedMember);
        Optional<Member> result = memberRepository.updateMember(1, updatedMember);

        assertEquals(expected, result);

    }

    @Test
    void givenNonExistingMember_whenUpdateMember_thenOptionalEmptyReturned() {

        Member member = new Member(1, "Cristian", "ccidbe@library.com", LocalDate.of(2000,1,6),
                "Test Street nº20, Barcelona", 123456789 );

        Optional<Member> expected = Optional.empty();
        Optional<Member> result = memberRepository.updateMember(1, member);

        assertEquals(expected, result);
    }

    // deleteMember()

    @Test
    void givenMember_whenDeleteMember_thenMemberDeleted() {

        Member member = new Member(1, "Cristian", "ccidbe@library.com", LocalDate.of(2000,1,6),
                "Test Street nº20, Barcelona", 123456789 );
        memberRepository.saveMember(member);

        memberRepository.deleteMember(1);

        Optional<Member> expected = Optional.empty();
        Optional<Member> result = memberRepository.findById(1);

        assertEquals(expected, result);
    }
}
