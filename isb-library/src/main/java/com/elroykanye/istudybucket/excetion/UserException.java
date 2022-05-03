package com.elroykanye.istudybucket.excetion;

public class UserException {
    public static class UserAlreadyExists extends RuntimeException {
        public UserAlreadyExists(String username, String email) {
            super("User with username: " + username + " or email: " + email + " already exists");
        }
    }

    public static class UserNotFound extends RuntimeException {
        public UserNotFound(Long id) {
            super("User with id: " + id + " not found.");
        }
        public UserNotFound(String username) {
            super("User with username: " + username + " not found.");
        }
    }

    public static class UserVerificationException extends RuntimeException {
        public UserVerificationException(String username) {
            super("User with username: " + username + " not verified.");
        }
    }
}
