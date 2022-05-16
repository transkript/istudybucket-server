package com.elroykanye.istudybucket.excetion;

public class AuthException {

    public static class LoginFailedException extends RuntimeException {
        public LoginFailedException() {
            super("Login failed.");
        }
    }

    public static class UserVerificationException extends RuntimeException {
        public UserVerificationException(String username) {
            super("User with username: " + username + " not verified.");
        }
    }
}
