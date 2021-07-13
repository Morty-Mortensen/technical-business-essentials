package com.essentials.business.technical.controller;

import com.essentials.business.technical.model.User;
import com.essentials.business.technical.model.request.PostUserRequest;
import com.essentials.business.technical.model.request.ValidateUserRequest;
import com.essentials.business.technical.service.UserService;
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
        return service.postUser(userRequest);
    }

    @PostMapping("user/validate")
    public boolean validateUser(@RequestBody ValidateUserRequest userRequest) throws Exception {
        UserService service = getUserService();
        return service.validateUser(userRequest);
    }

    public UserService getUserService() {
        return new UserService();
    }
}
