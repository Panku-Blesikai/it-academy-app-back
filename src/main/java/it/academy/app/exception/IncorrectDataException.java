package it.academy.app.exception;

import javax.naming.AuthenticationException;

public class IncorrectDataException extends AuthenticationException {
    public IncorrectDataException(String message) {
        super(message);
    }
}
