package com.essentials.business.technical.controller.exception;

public class TBEInternalServerErrorException extends TBEServerException {

    public TBEInternalServerErrorException(Exception ex) {
        super(ex.getLocalizedMessage(), ex);
    }

    public TBEInternalServerErrorException(String message) {
        super(message);
    }

    public TBEInternalServerErrorException(String message, Exception e) {
        super(message, e);
    }
}
