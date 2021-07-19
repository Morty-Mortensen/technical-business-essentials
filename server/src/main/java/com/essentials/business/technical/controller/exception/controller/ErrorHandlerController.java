package com.essentials.business.technical.controller.exception.controller;

import com.essentials.business.technical.controller.exception.HttpBadRequestException;
import com.essentials.business.technical.controller.exception.HttpForbiddenException;
import com.essentials.business.technical.controller.exception.HttpInternalServerErrorException;
import com.essentials.business.technical.controller.exception.HttpUnauthorizedException;
import com.essentials.business.technical.model.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ErrorHandlerController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpBadRequestException.class)
    public ErrorInfo handleBadRequest(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURI(), ex);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HttpInternalServerErrorException.class)
    public ErrorInfo handleInternalServerErrorRequest(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURI(), ex);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorInfo handleUnexpectedRequest(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURI(), ex);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(HttpUnauthorizedException.class)
    @ResponseBody
    ErrorInfo
    handleUnauthorizedRequest(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURI(), ex);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(HttpForbiddenException.class)
    @ResponseBody
    ErrorInfo
    handleForbiddenRequest(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURI(), ex);
    }
}
