package com.unitTestGenerator.exception;

public class TechnicalException extends RuntimeException {
    private final String errorCode;

    public TechnicalException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
