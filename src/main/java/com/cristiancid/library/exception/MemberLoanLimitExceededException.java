package com.cristiancid.library.exception;

public class MemberLoanLimitExceededException extends RuntimeException {
    public MemberLoanLimitExceededException(String message) {
        super(message);
    }
}
