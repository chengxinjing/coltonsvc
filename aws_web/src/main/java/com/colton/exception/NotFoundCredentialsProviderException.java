package com.colton.exception;

public class NotFoundCredentialsProviderException extends RuntimeException {
    public NotFoundCredentialsProviderException(String message) {
        super(message);
    }
}
