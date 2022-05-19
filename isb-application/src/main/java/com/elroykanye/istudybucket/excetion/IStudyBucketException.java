package com.elroykanye.istudybucket.excetion;

import org.springframework.http.HttpStatus;

public class IStudyBucketException extends RuntimeException {
    HttpStatus status;

    public IStudyBucketException(String message) {
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public IStudyBucketException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public static class IllegalArgumentException extends IStudyBucketException {
        public IllegalArgumentException(String message) {
            super(message);
        }
        public IllegalArgumentException(String message, HttpStatus status) {
            super(message, status);
        }
    }

    public static class IllegalStateException extends IStudyBucketException {
        public IllegalStateException(String message) {
            super(message);
        }
        public IllegalStateException(String message, HttpStatus status) {
            super(message, status);
        }
    }

    public static class RefreshTokenException extends RuntimeException{
        public RefreshTokenException (String token) {
            super("Refresh token: " + token + " is invalid");
        }
    }

    public static class NotAuthorisedException extends RuntimeException {
        public NotAuthorisedException(String message) {super(message);}
    }
}
