package com.essentials.business.technical.model.request;

public class LogoutUserRequest {
    private String token;

    public LogoutUserRequest() {
    }

    public LogoutUserRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
