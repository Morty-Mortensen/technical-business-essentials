package com.essentials.business.technical.controller;

import com.essentials.business.technical.controller.exception.HttpException;
import com.essentials.business.technical.model.User;
import com.essentials.business.technical.model.request.PostUserRequest;
import com.essentials.business.technical.model.request.ValidateUserRequest;
import com.essentials.business.technical.service.UserService;
import com.essentials.business.technical.utils.ExceptionHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:4200")
@RestController
public class UserController {

    @PostMapping("user")
    public User postUser(@RequestBody PostUserRequest userRequest) throws Exception {
        UserService service = getUserService();
        User user = null;
        try {
            user = service.postUser(userRequest);
        } catch (HttpException ex) {
            ExceptionHandler.handleException(ex);
        }
        return user;
    }

    @PostMapping("user/validate")
    public User validateUser(@RequestBody ValidateUserRequest userRequest) throws Exception {
        UserService service = getUserService();
        User user = null;
        try {
            user = service.validateUser(userRequest);
        } catch (HttpException ex) {
            ExceptionHandler.handleException(ex);
        }
        return user;
    }

    public UserService getUserService() {
        return new UserService();
    }
}
