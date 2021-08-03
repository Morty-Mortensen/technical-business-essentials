package com.essentials.business.technical.model;

public class Token {
    public static final String TOKEN = "token";
    private final String token;
    private final long time;

    public Token(String token, long time) {
        this.token = token;
        this.time = time;
    }

    public String getToken() {
        return token;
    }

    public long getTime() {
        return time;
    }
}
