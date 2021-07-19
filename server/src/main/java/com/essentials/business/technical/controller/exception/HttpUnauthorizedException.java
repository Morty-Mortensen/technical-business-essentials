package com.essentials.business.technical.controller.exception;

public class HttpUnauthorizedException extends Exception {

    public HttpUnauthorizedException(Exception ex) {
        super(ex.getLocalizedMessage(), ex);
    }

    public HttpUnauthorizedException(String message) {
        super(message);
    }

    public HttpUnauthorizedException(String message, Exception e) {
        super(message, e);
    }
}
