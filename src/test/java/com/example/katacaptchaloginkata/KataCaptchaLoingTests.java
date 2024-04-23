package com.example.katacaptchaloginkata;

import com.example.kata.captchalogin.CaptchaLoginService;
import com.example.kata.captchalogin.CaptchaLoginUseCase;
import com.example.kata.captchalogin.LoginResult;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.assertj.core.api.Assertions.assertThat;

@MockitoSettings
class KataCaptchaLoingTests {

    @Test
    void login_when_correct_then_redirect_to_home() {
        CaptchaLoginUseCase captchaLogin = new CaptchaLoginService();
        LoginResult loginResult = captchaLogin.login("user", "password", "captcha");

        // assert
        assertThat(loginResult.directTo()).isEqualTo("/home");
    }
}
