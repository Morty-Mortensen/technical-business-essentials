package com.essentials.business.technical.controller.exception.controller;

import com.essentials.business.technical.controller.exception.HttpBadRequestException;
import com.essentials.business.technical.controller.exception.HttpForbiddenException;
import com.essentials.business.technical.controller.exception.HttpInternalServerErrorException;
import com.essentials.business.technical.controller.exception.HttpUnauthorizedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectErrorController {
    @PostMapping("error/401")
    public void unauthorized() throws Exception {
        throw new HttpUnauthorizedException("Unauthorized");
    }

    @PostMapping("error/403")
    public void forbidden() throws Exception {
        throw new HttpForbiddenException("Forbidden");
    }

    @PostMapping("error/400")
    public void badRequest() throws Exception {
        throw new HttpBadRequestException("Bad Request");
    }

    @PostMapping("error/500")
    public void internalServerError() throws Exception {
        throw new HttpInternalServerErrorException("Internal Server Error");
    }
}
