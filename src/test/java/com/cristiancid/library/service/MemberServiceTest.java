package com.cristiancid.library.service;

import com.cristiancid.library.exception.MemberAlreadyExistsException;
import com.cristiancid.library.exception.MemberNotFoundException;
import com.cristiancid.library.model.Member;
import com.cristiancid.library.repository.MemberRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MemberServiceTest {

    MemberRepository memberRepository = new MemberRepository();
    MemberService memberService = new MemberService(memberRepository);

    // createMember()

    @Test
    void givenCorrectParams_whenCreateMember_thenMemberCreated() {

        Member expected = new Member(1, "Cristian", "ccidbe@library.com", LocalDate.of(2000,1,6),
                "Test Street nº20, Barcelona", 123456789 );
        Member result = memberService.createMember("Cristian", "ccidbe@library.com", LocalDate.of(2000,1,6),
                "Test Street nº20, Barcelona", 123456789);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getEmail(),result.getEmail());
        assertEquals(expected.getBirthDate(), result.getBirthDate());
        assertEquals(expected.getAddress(),result.getAddress());
        assertEquals(expected.getPhoneNumber(),result.getPhoneNumber());
    }

    @Test
    void givenTwoMembers_whenCreateMember_thenIdIncrements() {

        Member member1 = memberService.createMember("Cristian", "ccidbe@library.com", LocalDate.of(2000,1,6),
                "Test Street nº20, Barcelona", 123456789);
        Member member2 = memberService.createMember("David", "david@library.com", LocalDate.of(1990, 6, 23),
                "Test Avenue nº3, Sydney", 987654321);

        assertEquals(1, member1.getId());
        assertEquals(2, member2.getId());
    }

    @Test
    void givenExistingEmail_whenCreateMember_thenMemberAlreadyExistsExceptionThrown() {

        String email = "ccidbe@library.com";

        memberService.createMember("Cristian", email, LocalDate.of(2000,1,6),
                "Test Street nº20, Barcelona", 123456789);

        assertThrows(MemberAlreadyExistsException.class, () -> {
            memberService.createMember("David", email, LocalDate.of(1990, 6, 23),
                    "Test Avenue nº3, Sydney", 987654321);
        });
    }

    @Test
    void givenExistingPhoneNumber_whenCreateMember_thenMemberAlreadyExistsExceptionThrown() {

        int phoneNumber = 123456789;

        memberService.createMember("Cristian", "ccidbe@library.com", LocalDate.of(2000,1,6),
                "Test Street nº20, Barcelona", phoneNumber);

        assertThrows(MemberAlreadyExistsException.class, () -> {
            memberService.createMember("David", "david@library.com", LocalDate.of(1990, 6, 23),
                    "Test Avenue nº3, Sydney", phoneNumber);
        });
    }

    // getMemberById()

    @Test
    void givenExistingMember_whenGetMemberById_thenMemberReturned() {

        Member expected = memberService.createMember("Cristian", "ccidbe@library.com", LocalDate.of(2000,1,6),
                "Test Street nº20, Barcelona", 123456789);

        Member result = memberService.getMemberById(expected.getId());

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getEmail(),result.getEmail());
        assertEquals(expected.getBirthDate(), result.getBirthDate());
        assertEquals(expected.getAddress(),result.getAddress());
        assertEquals(expected.getPhoneNumber(),result.getPhoneNumber());
    }

    @Test
    void givenNonExistingMember_whenGetMemberById_thenMemberNotFoundExceptionThrown() {

        assertThrows(MemberNotFoundException.class, () -> {
            memberService.getMemberById(1);
        });
    }

    // getMemberByEmail()

    @Test
    void givenExistingMember_whenGetMemberByEmail_thenMemberReturned() {

        String email = "ccidbe@library.com";
        Member expected = memberService.createMember("Cristian", email, LocalDate.of(2000,1,6),
                "Test Street nº20, Barcelona", 123456789);
        Member result = memberService.getMemberByEmail(email);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getEmail(),result.getEmail());
        assertEquals(expected.getBirthDate(), result.getBirthDate());
        assertEquals(expected.getAddress(),result.getAddress());
        assertEquals(expected.getPhoneNumber(),result.getPhoneNumber());
    }

    @Test
    void givenNonExistingMember_whenGetMemberByEmail_thenMemberNotFoundExceptionThrown() {

        assertThrows(MemberNotFoundException.class, () -> {
            memberService.getMemberByEmail("ccidbe@library.com");
        });
    }

    // getMemberByPhoneNumber()

    @Test
    void givenExistingMember_whenGetMemberByPhoneNumber_thenMemberReturned() {

        int phoneNumber = 123456789;
        Member expected = memberService.createMember("Cristian", "ccidbe@library.com", LocalDate.of(2000,1,6),
                "Test Street nº20, Barcelona", phoneNumber);
        Member result = memberService.getMemberByPhoneNumber(phoneNumber);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getEmail(),result.getEmail());
        assertEquals(expected.getBirthDate(), result.getBirthDate());
        assertEquals(expected.getAddress(),result.getAddress());
        assertEquals(expected.getPhoneNumber(),result.getPhoneNumber());
    }

    @Test
    void givenNonExistingMember_whenGetMemberByPhoneNumber_thenMemberNotFoundExceptionThrown() {

        assertThrows(MemberNotFoundException.class, () -> {
            memberService.getMemberByPhoneNumber(123456789);
        });
    }

    // updateMember()

    @Test
    void givenExistingMemberAndCorrectParams_whenUpdateMember_thenMemberUpdated() {

        memberService.createMember("Cristian", "ccidbe@library.com", LocalDate.of(2000,1,6),
                "Test Street nº20, Barcelona", 123456789);

        Member expected = new Member(1, "David", "david@library.com", LocalDate.of(1990, 6, 23),
                "Test Avenue nº3, Sydney", 987654321);
        Member result = memberService.updateMember(1, "David", "david@library.com", LocalDate.of(1990, 6, 23),
                "Test Avenue nº3, Sydney", 987654321);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getEmail(),result.getEmail());
        assertEquals(expected.getBirthDate(), result.getBirthDate());
        assertEquals(expected.getAddress(),result.getAddress());
        assertEquals(expected.getPhoneNumber(),result.getPhoneNumber());
    }

    @Test
    void givenNonExistingMember_whenUpdateMember_thenMemberNotFoundExceptionThrown() {

        assertThrows(MemberNotFoundException.class, () -> {
            memberService.updateMember(1, "David", "david@library.com", LocalDate.of(1990, 6, 23),
                    "Test Avenue nº3, Sydney", 987654321);
        });
    }

    @Test
    void givenExistingEmail_whenUpdateMember_thenMemberAlreadyExistsExceptionThrown() {

        String email = "david@library.com";
        memberService.createMember("Cristian", "ccidbe@library.com", LocalDate.of(2000,1,6),
                "Test Street nº20, Barcelona", 123456789);
        memberService.createMember("David", email, LocalDate.of(2002, 4, 21),
                "Test Street nº67, Madrid", 321456987);

        assertThrows(MemberAlreadyExistsException.class, () -> {
            memberService.updateMember(1, "David", email, LocalDate.of(1990, 6, 23),
                    "Test Avenue nº3, Sydney", 987654321);
        });

    }

    @Test
    void givenExistingPhoneNumber_whenUpdateMember_thenMemberAlreadyExistsExceptionThrown() {

        int phoneNumber = 987654321;
        memberService.createMember("Cristian", "ccidbe@library.com", LocalDate.of(2000,1,6),
                "Test Street nº20, Barcelona", 123456789);
        memberService.createMember("David", "david@library.com", LocalDate.of(2002, 4, 21),
                "Test Street nº67, Madrid", phoneNumber);

        assertThrows(MemberAlreadyExistsException.class, () -> {
            memberService.updateMember(1, "David", "ccidbe@library.com", LocalDate.of(1990, 6, 23),
                    "Test Avenue nº3, Sydney", phoneNumber);
        });
    }

    @Test
    void givenSameEmail_whenUpdateMember_thenMemberUpdated() {

        String email = "ccidbe@library.com";
        memberService.createMember("Cristian", email, LocalDate.of(2000,1,6),
                "Test Street nº20, Barcelona", 123456789);

        Member expected = new Member(1, "David", email, LocalDate.of(1990, 6, 23),
                "Test Avenue nº3, Sydney", 987654321);
        Member result = memberService.updateMember(1, "David", email, LocalDate.of(1990, 6, 23),
                "Test Avenue nº3, Sydney", 987654321);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getEmail(),result.getEmail());
        assertEquals(expected.getBirthDate(), result.getBirthDate());
        assertEquals(expected.getAddress(),result.getAddress());
        assertEquals(expected.getPhoneNumber(),result.getPhoneNumber());
    }

    @Test
    void givenSamePhoneNumber_whenUpdateMember_thenMemberUpdated() {

        int phoneNumber = 123456789;
        memberService.createMember("Cristian", "ccidbe@library.com", LocalDate.of(2000,1,6),
                "Test Street nº20, Barcelona", phoneNumber);

        Member expected = new Member(1, "David", "david@library.com", LocalDate.of(1990, 6, 23),
                "Test Avenue nº3, Sydney", phoneNumber);
        Member result = memberService.updateMember(1, "David", "david@library.com", LocalDate.of(1990, 6, 23),
                "Test Avenue nº3, Sydney", phoneNumber);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getEmail(),result.getEmail());
        assertEquals(expected.getBirthDate(), result.getBirthDate());
        assertEquals(expected.getAddress(),result.getAddress());
        assertEquals(expected.getPhoneNumber(),result.getPhoneNumber());
    }

    // deleteMember()

    @Test
    void givenExistingMember_whenDeleteMember_thenMemberDeleted() {

        memberService.createMember("Cristian", "ccidbe@library.com", LocalDate.of(2000,1,6),
                "Test Street nº20, Barcelona", 123456789);

        assertDoesNotThrow(() -> {
            memberService.deleteMember(1);
        });
        assertThrows(MemberNotFoundException.class, () -> {
            memberService.getMemberById(1);
        });
    }

    @Test
    void givenNonExistingMember_whenDeleteMember_thenMemberNotFoundExceptionThrown() {

        assertThrows(MemberNotFoundException.class, () -> {
            memberService.deleteMember(1);
        });
    }

}
