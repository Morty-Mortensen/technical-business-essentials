package com.essentials.business.technical.controller;

import com.essentials.business.technical.controller.exception.TBEServerException;
import com.essentials.business.technical.model.User;
import com.essentials.business.technical.model.request.LogoutUserRequest;
import com.essentials.business.technical.model.request.PostUserRequest;
import com.essentials.business.technical.model.request.ValidateUserRequest;
import com.essentials.business.technical.service.AuthenticationService;
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
    public User postUser(@RequestBody PostUserRequest userRequest) throws TBEServerException {
        UserService service = getUserService();
        User user = null;
        user = service.postUser(userRequest);
        return user;
    }

    @PostMapping("user/validate")
    public User validateUser(@RequestBody ValidateUserRequest userRequest) throws TBEServerException {
        UserService service = getUserService();
        User user = null;
        user = service.validateUser(userRequest);
        return user;
    }

    @PostMapping("user/logout")
    public void logoutUser(@RequestBody LogoutUserRequest userRequest) throws TBEServerException {
        AuthenticationService service = getAuthenticationService();
        service.logoutUser(userRequest);
    }

    public UserService getUserService() {
        return new UserService();
    }

    public AuthenticationService getAuthenticationService() {
        return new AuthenticationService();
    }
}
