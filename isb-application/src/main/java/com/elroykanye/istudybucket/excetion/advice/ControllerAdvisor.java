package com.elroykanye.istudybucket.excetion.advice;

import com.elroykanye.istudybucket.excetion.AuthException;
import com.elroykanye.istudybucket.excetion.EntityException;
import com.elroykanye.istudybucket.excetion.IStudyBucketException;
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
    @ExceptionHandler(IStudyBucketException.RefreshTokenException.class)
    public ResponseEntity<ExceptionBody> handleRefreshTokenException(
            IStudyBucketException.RefreshTokenException refreshTokenException,
            WebRequest webRequest) {
        return getExceptionEntity(refreshTokenException, webRequest, HttpStatus.UNAUTHORIZED, List.of("Refresh token expired"));
    }

    /**
     *
     */
    @ExceptionHandler(IStudyBucketException.NotAuthorisedException.class)
    public ResponseEntity<ExceptionBody> handleNotAuthorisedException(
            IStudyBucketException.NotAuthorisedException notAuthorisedException,
            WebRequest webRequest) {
        return getExceptionEntity(notAuthorisedException, webRequest, HttpStatus.UNAUTHORIZED, List.of("Action not authorised"));
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
        return getExceptionEntity(loginFailedException, webRequest, HttpStatus.FORBIDDEN, List.of("Password Incorrect", "User not verified"));
    }

    // ENTITY EXCEPTION HANDLERS
    @ExceptionHandler(EntityException.EntityNotFoundException.class)
    public ResponseEntity<ExceptionBody> handleEntityNotFoundException(
            EntityException.EntityNotFoundException entityNotFoundException,
            WebRequest webRequest) {
        return getExceptionEntity(entityNotFoundException, webRequest, HttpStatus.NOT_FOUND, List.of("Entity not found"));
    }

    @ExceptionHandler(EntityException.EntityAlreadyExistsException.class)
    public ResponseEntity<ExceptionBody> handleEntityAlreadyExistsException(
            EntityException.EntityAlreadyExistsException entityAlreadyExistsException,
            WebRequest webRequest) {
        return getExceptionEntity(entityAlreadyExistsException, webRequest, HttpStatus.CONFLICT, List.of("Entity already exists"));
    }

    private ResponseEntity<ExceptionBody> getExceptionEntity(
            RuntimeException ex,
            WebRequest webRequest,
            HttpStatus status,
            List<String> messages) {
        ExceptionBody exceptionBody = ExceptionBody.buildExceptionBody(ex, webRequest, status, messages);
        return new ResponseEntity<>(exceptionBody, exceptionBody.getStatus());
    }
}
