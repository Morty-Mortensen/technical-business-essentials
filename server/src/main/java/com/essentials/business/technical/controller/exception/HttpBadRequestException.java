package com.essentials.business.technical.controller.exception;

public class HttpBadRequestException extends Exception {

    public HttpBadRequestException(Exception ex) {
        super(ex.getLocalizedMessage(), ex);
    }

    public HttpBadRequestException(String message) {
        super(message);
    }

    public HttpBadRequestException(String message, Exception e) {
        super(message, e);
    }
}
