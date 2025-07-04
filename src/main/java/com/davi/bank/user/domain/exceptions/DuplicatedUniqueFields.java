package com.davi.bank.user.domain.exceptions;

public class DuplicatedUniqueFields extends RuntimeException {
    public DuplicatedUniqueFields(String message) {
        super(message);
    }
}
