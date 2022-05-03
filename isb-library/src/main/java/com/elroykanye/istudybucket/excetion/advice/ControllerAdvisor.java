package com.elroykanye.istudybucket.excetion.advice;

import com.elroykanye.istudybucket.excetion.AuthException;
import com.elroykanye.istudybucket.excetion.IstudybucketException;
import com.elroykanye.istudybucket.excetion.body.ExceptionBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    // IStudyBucketExceptionHandler

    /**
     * Handle all refresh token expired exception
     * @param refreshTokenException the exception
     * @param webRequest the web request
     * @return the response entity
     */
    @ExceptionHandler(IstudybucketException.RefreshTokenException.class)
    public ResponseEntity<ExceptionBody> handleRefreshTokenException(
            IstudybucketException.RefreshTokenException refreshTokenException,
            WebRequest webRequest) {
        ExceptionBody exceptionBody = ExceptionBody.buildExceptionBody(
                refreshTokenException,
                HttpStatus.UNAUTHORIZED,
                List.of("Refresh token expired"));

        return new ResponseEntity<>(exceptionBody, exceptionBody.getStatus());
    }

    /**
     * Handle all failed login exception
     * @param loginFailedException the exception
     * @param webRequest the web request
     * @return the response entity
     */
    @ExceptionHandler(AuthException.LoginFailedException.class)
    public ResponseEntity<ExceptionBody> handleLoginFailed(
            AuthException.LoginFailedException loginFailedException,
            WebRequest webRequest) {
        ExceptionBody exceptionBody = ExceptionBody.buildExceptionBody(
                loginFailedException,
                HttpStatus.FORBIDDEN,
                List.of("Password Incorrect", "User not verified"));

        return new ResponseEntity<>(exceptionBody, exceptionBody.getStatus());
    }


}
