package com.cristiancid.library.service;

import com.cristiancid.library.exception.MemberAlreadyExistsException;
import com.cristiancid.library.exception.MemberNotFoundException;
import com.cristiancid.library.model.Member;
import com.cristiancid.library.repository.MemberRepository;

import java.time.LocalDate;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;
    private int nextMemberId = 1;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(String name, String email, LocalDate birthDate, String address, int phoneNumber) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new MemberAlreadyExistsException("Member with email '" + email + "' already exists");
        }
        if (memberRepository.findByPhoneNumber(phoneNumber).isPresent()) {
            throw new MemberAlreadyExistsException("Member with phone number '" + phoneNumber + "' already exists");
        }

        Member member = new Member(nextMemberId,name, email, birthDate, address, phoneNumber);
        nextMemberId++;

        memberRepository.saveMember(member);
        return member;
    }

    public Member getMemberById(int id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member with id '" + id + "' not found"));

    }

    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException("Member with email '" + email + "' not found"));
    }

    public Member getMemberByPhoneNumber(int phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new MemberNotFoundException("Member with phone number '" + phoneNumber + "' not found"));
    }

    public Member updateMember(int id, String name, String email, LocalDate birthDate, String address, int phoneNumber) {
        memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member with id '" + id + "' not found"));

        Optional<Member> memberByEmail = memberRepository.findByEmail(email);
        if (memberByEmail.isPresent()) {
            Member member = memberByEmail.get();
            if (member.getId() != id) {
                throw new MemberAlreadyExistsException("Member with email '" + email + "' already exists");
            }
        }

        Optional<Member> memberByPhoneNumber = memberRepository.findByPhoneNumber(phoneNumber);
        if (memberByPhoneNumber.isPresent()) {
            Member member = memberByPhoneNumber.get();
            if (member.getId() != id) {
                throw new MemberAlreadyExistsException("Member with phone number '" + phoneNumber + "' already exists");
            }
        }

        Member member = new Member(id, name, email, birthDate, address, phoneNumber);
        return  memberRepository.updateMember(id, member)
                .orElseThrow(() -> new MemberNotFoundException("Member with id '" + id + "' not found"));
    }

    public void deleteMember(int id) {
        memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member with id '" + id + "' not found"));
        memberRepository.deleteMember(id);
    }
}
