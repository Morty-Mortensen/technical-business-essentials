package com.essentials.business.technical.controller.exception;

public class HttpInternalServerErrorException extends Exception {

    public HttpInternalServerErrorException(Exception ex) {
        super(ex.getLocalizedMessage(), ex);
    }

    public HttpInternalServerErrorException(String message) {
        super(message);
    }

    public HttpInternalServerErrorException(String message, Exception e) {
        super(message, e);
    }
}
