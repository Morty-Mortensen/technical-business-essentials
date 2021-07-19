package com.essentials.business.technical.controller.exception;

public class HttpForbiddenException extends Exception {

    public HttpForbiddenException(Exception ex) {
        super(ex.getLocalizedMessage(), ex);
    }

    public HttpForbiddenException(String message) {
        super(message);
    }

    public HttpForbiddenException(String message, Exception e) {
        super(message, e);
    }

}
