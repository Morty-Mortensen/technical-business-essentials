package com.essentials.business.technical.model.request;

public class ValidateUserRequest {
    private final String email;
    private final String password;

    public ValidateUserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
