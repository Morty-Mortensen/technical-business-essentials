package com.essentials.business.technical.controller.exception;

public class HttpException extends Exception {
    private final ErrorType type;

    public HttpException(String message, ErrorType type) {
        super(message);
        this.type = type;
    }

    public HttpException(String message, Exception e, ErrorType type) {
        super(message, e);
        this.type = type;
    }

    public ErrorType getType() {
        return type;
    }
}
