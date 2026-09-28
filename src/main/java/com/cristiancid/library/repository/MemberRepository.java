package com.cristiancid.library.repository;

import com.cristiancid.library.model.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberRepository {

    private final List<Member> members = new ArrayList<>();

    public void saveMember(Member member) {
        members.add(member);
    }

    public Optional<Member> findById(int id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }

    public Optional<Member> findByEmail(String email) {
        for (Member member : members) {
            if (member.getEmail().equals(email)) {
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }

    public Optional<Member> findByPhoneNumber(int phoneNumber) {
        for (Member member : members) {
            if (member.getPhoneNumber() == phoneNumber) {
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }

    public Optional<Member> updateMember(int id, Member member) {
        if (findById(id).isEmpty()) {
            return Optional.empty();
        }
        int index = members.indexOf(findById(id).get());
        members.set(index, member);
        return Optional.of(member);
    }

    public void deleteMember(int id) {
        members.remove(findById(id).get());
    }
}
