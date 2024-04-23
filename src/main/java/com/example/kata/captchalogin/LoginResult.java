package com.example.kata.captchalogin;

public class LoginResult {
    private final String directTo;

    public LoginResult(String redirectUrl) {
        this.directTo = redirectUrl;
    }

    public String directTo() {
        return directTo;
    }
}
