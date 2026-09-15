package com.cristiancid.library.repository;

import com.cristiancid.library.model.Member;
import com.cristiancid.library.exception.MemberAlreadyExistsException;
import com.cristiancid.library.exception.MemberNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberRepository {

    private final List<Member> members = new ArrayList<>();

    public void saveMember(Member member) {
        for (Member member1 : members) {
            if (member1.equals(member)) {
                throw new MemberAlreadyExistsException("Member already exists");
            }
        }
        members.add(member);
    }

    public Optional<Member> findById(int id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return Optional.of(member);
            }
        }
        throw new MemberNotFoundException("Member with id '" + id + "' not found");
    }

    public Optional<Member> findByEmail(String email) {
        for (Member member : members) {
            if (member.getEmail().equals(email)) {
                return Optional.of(member);
            }
        }
        throw new MemberNotFoundException("Member with email '" + email + "' not found");
    }
}
