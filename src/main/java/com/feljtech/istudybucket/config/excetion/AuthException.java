package com.feljtech.istudybucket.config.excetion;

public class AuthException extends RuntimeException {
    public AuthException() {
        super("Authentication failed");
    }
    public static class LoginFailedException extends RuntimeException {
        public LoginFailedException() {
            super("Login failed.");
        }
    }

    public static class RegisterFailedException extends RuntimeException {
        public RegisterFailedException() {
            super("Registeration failed.");
        }
    }

}
