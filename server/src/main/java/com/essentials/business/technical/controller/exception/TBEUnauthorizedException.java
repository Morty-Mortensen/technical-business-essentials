package com.essentials.business.technical.controller.exception;

public class TBEUnauthorizedException extends TBEServerException {

    public TBEUnauthorizedException(Exception ex) {
        super(ex.getLocalizedMessage(), ex);
    }

    public TBEUnauthorizedException(String message) {
        super(message);
    }

    public TBEUnauthorizedException(String message, Exception e) {
        super(message, e);
    }

    @Override
    public void throwException() throws TBEServerException {
        throw new TBEUnauthorizedException(this);
    }
}
