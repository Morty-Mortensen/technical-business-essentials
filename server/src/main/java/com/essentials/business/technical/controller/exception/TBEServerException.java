package com.essentials.business.technical.controller.exception;

public class TBEServerException extends Exception {

    public TBEServerException(Exception ex) {
        super(ex.getLocalizedMessage(), ex);
    }

    public TBEServerException(String message) {
        super(message);
    }

    public TBEServerException(String message, Exception e) {
        super(message, e);
    }
}
