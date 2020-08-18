package com.hmm.hmm.interfaces.auth;

public class PasswordWrongException extends RuntimeException {
    public PasswordWrongException() {
        super("Password is wrong");
    }
}

