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
        // validate input
        if (inputUser == null || inputUser.isEmpty() || inputUser.isBlank())
            return new LoginResult("/login");
        if (inputPass == null || inputPass.isEmpty() || inputPass.isBlank())
            return new LoginResult("/login");
        if (inputToken == null || inputToken.isEmpty() || inputToken.isBlank())
            return new LoginResult("/login");

        // 取得一次性的 toeken 並驗證
        String token = this.captureToken.getToken(inputUser);
        if (!inputToken.equals(token))
            return new LoginResult("/login");

        // 驗證登入
        boolean isValidLogin = this.validateUser.validate(inputUser, inputPass);
        if (!isValidLogin)
            return new LoginResult("/login");

        return new LoginResult("/home");
    }
}
