package com.essentials.business.technical.controller.exception;

public class TBEBadRequestException extends TBEServerException {

    public TBEBadRequestException(Exception ex) {
        super(ex.getLocalizedMessage(), ex);
    }

    public TBEBadRequestException(String message) {
        super(message);
    }

    public TBEBadRequestException(String message, Exception e) {
        super(message, e);
    }
}
