package com.hmm.hmm.interfaces.auth;

public class EmailNotExistedException extends RuntimeException{

    public EmailNotExistedException(String email) {
        super("Email is not registered" + email);
    }
}

