package de.thro.inf.pvs;

import org.springframework.http.HttpStatus;

public class BackendException extends RuntimeException{
    public BackendException(String message) {
        super(message);
    }
}
