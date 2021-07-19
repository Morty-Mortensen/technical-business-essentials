package com.essentials.business.technical.utils;

import com.essentials.business.technical.controller.exception.HttpException;
import com.essentials.business.technical.controller.exception.HttpBadRequestException;
import com.essentials.business.technical.controller.exception.HttpForbiddenException;
import com.essentials.business.technical.controller.exception.HttpInternalServerErrorException;
import com.essentials.business.technical.controller.exception.HttpUnauthorizedException;

public class ExceptionHandler {
    public static void handleException(HttpException ex) throws Exception {
        switch (ex.getType()) {
            case BAD_REQUEST:
                throw new HttpBadRequestException(ex);
            case INTERNAL_SERVER_ERROR:
                throw new HttpInternalServerErrorException(ex);
            case UNAUTHORIZED:
                throw new HttpUnauthorizedException(ex);
            case FORBIDDEN:
                throw new HttpForbiddenException(ex);
            default:
                throw new Exception("An unknown exception occurred.", ex);
        }
    }
}
