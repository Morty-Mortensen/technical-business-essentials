package com.essentials.business.technical.model.request;

import com.essentials.business.technical.model.User;

public class PostUserRequest {
    private User user;
    private String password;

    public PostUserRequest() {
    }

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

    public void setPassword(String password) {
        this.password = password;
    }
}
