package com.example.kata.captchalogin;

public class CaptchaLoginService implements CaptchaLoginUseCase {
    private final CaptchaToken captureToken;
    private final ValidateUserPort validateUser;

    public CaptchaLoginService(CaptchaToken captchaToken, ValidateUserPort validateUserPort) {
        this.captureToken = captchaToken;
        this.validateUser = validateUserPort;
    }

    @Override
    public LoginResult login(String inputUser, String inputPass, String inputToken) {

        String token = this.captureToken.getToken(inputUser);
        if (!inputToken.equals(token))
            return new LoginResult("/login");

        boolean isValidLogin = this.validateUser.validate(inputUser, inputPass);
        if (!isValidLogin)
            return new LoginResult("/login");

        return new LoginResult("/home");
    }
}
