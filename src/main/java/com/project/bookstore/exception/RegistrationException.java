package com.project.bookstore.exception;

public class RegistrationException extends Exception {
    public RegistrationException(String message) {
        super(message);
    }

    public RegistrationException(String message, Throwable ex) {
        super(message, ex);
    }
}
