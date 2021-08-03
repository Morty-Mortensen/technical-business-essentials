package com.essentials.business.technical.controller.exception;

public class HttpException extends Exception {

    public HttpException(Exception ex) {
        super(ex.getLocalizedMessage(), ex);
    }

    public HttpException(String message) {
        super(message);
    }

    public HttpException(String message, Exception e) {
        super(message, e);
    }
}
