package com.example.katacaptchaloginkata;

import com.example.kata.captchalogin.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = org.mockito.quality.Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class KataCaptchaLoingTests {

    @InjectMocks
    private CaptchaLoginService captchaLogin;
    private LoginResult loginResult;
    @Mock
    private CaptchaToken captchaToken;
    @Mock
    private ValidateUserPort validateUserPort;

    @Test
    void login_when_correct_then_redirect_to_home() {
        givenStoredUserAndCaptcha("user", "captcha");
        givenCorrectValidateLogin("user", "password");

        login("user", "password", "captcha");

        // assert
        shouldRedirectTo("/home");
    }

    @ParameterizedTest(name = "login with wrong login user:{0}, pass:{1}")
    @CsvSource({
            "user, wrong-password",
            "wrong-user, password",
            "'', ''",
            "'', password",
            "user, ''",
            ", ",
            "user, ",
            ", password",
    })
    void login_when_wrong_login_then_redirect_to_login(String user, String pass) {
        givenStoredUserAndCaptcha("user", "captcha");
        givenCorrectValidateLogin("user", "password");

        login(user, pass, "captcha");

        // assert
        shouldRedirectTo("/login");
    }

    @Test
    void login_when_validate_user_fail_then_redirect_to_login() {
        givenStoredUserAndCaptcha("user", "captcha");
        givenCorrectValidateLogin("user", "password");

        login("user", "wrong-password", "captcha");

        // assert
        shouldRedirectTo("/login");
    }

    private void givenCorrectValidateLogin(String user, String password) {
        when(validateUserPort.validate(user, password)).thenReturn(true);
    }

    @Test
    void login_when_captcha_wrong_then_redirect_to_login() {
        givenStoredUserAndCaptcha("user", "captcha");

        login("user", "password", "wrong-captcha");

        // assert
        shouldRedirectTo("/login");
    }

    private void givenStoredUserAndCaptcha(String user, String captcha) {
        when(captchaToken.getToken(user)).thenReturn(captcha);
    }

    private void login(String user, String password, String captcha) {
        loginResult = captchaLogin.login(user, password, captcha);
    }

    private void shouldRedirectTo(String expectedPath) {
        assertThat(loginResult.directTo()).isEqualTo(expectedPath);
    }
}
