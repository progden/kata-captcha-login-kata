package com.example.kata.captchalogin;

public interface CaptchaLoginUseCase {
    LoginResult login(String user, String password, String captcha);
}
