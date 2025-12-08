package com.unitTestGenerator.exception.model;


import java.time.LocalDateTime;

public class ErrorResponse {
    private final String errorCode;
    private final String message;
    private final LocalDateTime timestamp;
    private final String threadName;

    public ErrorResponse(String errorCode, String message, String threadName) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.threadName = threadName;
    }

    // Getters
    public String getErrorCode() { return errorCode; }
    public String getMessage() { return message; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getThreadName() { return threadName; }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s (Hilo: %s)",
                timestamp, errorCode, message, threadName);
    }
}