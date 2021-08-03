package com.essentials.business.technical.utils;

import com.essentials.business.technical.controller.exception.*;
import com.essentials.business.technical.controller.exception.controller.RedirectErrorController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandler {
    public static boolean forwardException(HttpServletRequest request, HttpServletResponse response, TBEServerException ex) throws ServletException, IOException {
        request.setAttribute(RedirectErrorController.REDIRECT_ATTRIBUTE, ex);
        request.getRequestDispatcher("/error").forward(request, response);
        return false;
    }
}
