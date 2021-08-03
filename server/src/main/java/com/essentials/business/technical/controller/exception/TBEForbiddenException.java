package com.essentials.business.technical.controller.exception;

public class TBEForbiddenException extends TBEServerException {

    public TBEForbiddenException(Exception ex) {
        super(ex.getLocalizedMessage(), ex);
    }

    public TBEForbiddenException(String message) {
        super(message);
    }

    public TBEForbiddenException(String message, Exception e) {
        super(message, e);
    }

    @Override
    public void throwException() throws TBEServerException {
        throw new TBEForbiddenException(this);
    }

}
