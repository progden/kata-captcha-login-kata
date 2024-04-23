package com.example.kata.captchalogin;

public class CaptchaLoginService implements CaptchaLoginUseCase {
    private final CaptchaToken captureToken;
    private final ValidateUserPort validateUser;

    public CaptchaLoginService(CaptchaToken captchaToken, ValidateUserPort validateUserPort) {
        this.captureToken = captchaToken;
        this.validateUser = validateUserPort;
    }

    @Override
    public LoginResult login(String user, String password, String captcha) {
        String userCaptcha = this.captureToken.getToken(user);
        if (!captcha.equals(userCaptcha))
            return new LoginResult("/login");
        return new LoginResult("/home");
    }
}
