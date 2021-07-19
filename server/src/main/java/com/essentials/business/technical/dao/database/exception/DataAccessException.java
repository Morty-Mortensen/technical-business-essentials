package com.essentials.business.technical.dao.database.exception;

import com.essentials.business.technical.controller.exception.HttpException;
import com.essentials.business.technical.controller.exception.ErrorType;

public class DataAccessException extends HttpException {

    public DataAccessException(String message, ErrorType type) {
        super(message, type);
    }

    public DataAccessException(String message, Exception e, ErrorType type) {
        super(message, e, type);
    }
}
