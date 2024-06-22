package com.crater.accounting.exception;

public class GenerateResponseException extends RuntimeException {
    public GenerateResponseException() {
    }

    public GenerateResponseException(String message) {
        super(message);
    }

    public GenerateResponseException(String message, Throwable cause) {
        super(message, cause);
    }
}
