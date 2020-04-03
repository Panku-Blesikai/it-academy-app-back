package it.academy.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;

public class IncorrectDataException extends AuthenticationException {
    public IncorrectDataException(String message) {
        super(message);
    }
}
