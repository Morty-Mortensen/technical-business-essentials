package com.essentials.business.technical.controller.exception.controller;

import com.essentials.business.technical.controller.exception.*;
import com.essentials.business.technical.utils.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RedirectErrorController {
    public static String REDIRECT_ATTRIBUTE = "error";

    @PostMapping("error")
    public void handleError(HttpServletRequest request) throws Exception {
        TBEServerException ex = (TBEServerException) request.getAttribute(REDIRECT_ATTRIBUTE);
        ExceptionHandler.handleException(ex);
    }
}
