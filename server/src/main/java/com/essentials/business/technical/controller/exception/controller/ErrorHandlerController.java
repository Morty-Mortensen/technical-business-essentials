package com.essentials.business.technical.controller.exception.controller;

import com.essentials.business.technical.controller.exception.TBEBadRequestException;
import com.essentials.business.technical.controller.exception.TBEForbiddenException;
import com.essentials.business.technical.controller.exception.TBEInternalServerErrorException;
import com.essentials.business.technical.controller.exception.TBEUnauthorizedException;
import com.essentials.business.technical.model.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ErrorHandlerController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TBEBadRequestException.class)
    public ErrorInfo handleBadRequest(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURI(), ex);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TBEInternalServerErrorException.class)
    public ErrorInfo handleInternalServerErrorRequest(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURI(), ex);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorInfo handleUnexpectedRequest(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURI(), ex);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(TBEUnauthorizedException.class)
    public ErrorInfo handleUnauthorizedRequest(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURI(), ex);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(TBEForbiddenException.class)
    public ErrorInfo handleForbiddenRequest(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURI(), ex);
    }
}
