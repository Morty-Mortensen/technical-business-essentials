package com.essentials.business.technical.model.request;

import com.essentials.business.technical.model.User;

public class PostUserRequest {
    private final User user;
    private final String password;

    public PostUserRequest(User user, String password) {
        this.user = user;
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
