package com.elroykanye.istudybucket.excetion;

public class IstudybucketException extends RuntimeException {
    public IstudybucketException(String exceptionMessage) {
        super(exceptionMessage);
    }

    public static class RefreshTokenException extends RuntimeException{
        public RefreshTokenException (String token) {
            super("Refresh token: " + token + " is invalid");
        }
    }
}
