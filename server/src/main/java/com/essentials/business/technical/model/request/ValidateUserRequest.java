package com.essentials.business.technical.model.request;

public class ValidateUserRequest {
    private String email;
    private String password;

    public ValidateUserRequest() {
    }

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
