package com.example.katacaptchaloginkata;

import com.example.kata.captchalogin.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = org.mockito.quality.Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class KataCaptchaLoingTests {

    @InjectMocks
    private CaptchaLoginService captchaLogin;
    private LoginResult loginResult;
    @Mock
    private CaptchaToken captchaToken;

    @Test
    void login_when_correct_then_redirect_to_home() {
        givenStoredUserAndCaptcha("user", "captcha");

        login("user", "password", "captcha");

        // assert
        shouldRedirectTo("/home");
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
