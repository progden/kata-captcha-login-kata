package com.example.kata.captchalogin;

public class CaptchaLoginService implements CaptchaLoginUseCase {
    private final CaptchaToken captureToken;

    public CaptchaLoginService(CaptchaToken captchaToken) {
        this.captureToken = captchaToken;
    }

    @Override
    public LoginResult login(String user, String password, String captcha) {
        return new LoginResult("/home");
    }
}
