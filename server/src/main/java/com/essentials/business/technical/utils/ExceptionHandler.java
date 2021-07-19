package com.essentials.business.technical.utils;

import com.essentials.business.technical.controller.exception.*;
import com.essentials.business.technical.controller.exception.controller.RedirectErrorController;
import com.essentials.business.technical.controller.middleware.CacheMiddleware;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    public static boolean forwardException(HttpServletRequest request, HttpServletResponse response, String interceptorName, ErrorType type) throws ServletException, IOException {
        request.setAttribute(RedirectErrorController.REDIRECT_ATTRIBUTE, new HttpException(interceptorName, type));
        request.getRequestDispatcher("/error").forward(request, response);
        return false;
    }
}
