package com.aniket.taskman.error;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiError > handleUsernameNotFoundException (UsernameNotFoundException exception ){
        ApiError apiError = new ApiError("User not found with uname: "+exception.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, apiError.getStatusCode());
    }
     @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError > handleAuthenticationException (AuthenticationException exception ){
        ApiError apiError = new ApiError("Authentication failed: "+exception.getMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiError, apiError.getStatusCode());
    }
     @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError > handleJwtException (JwtException exception ){
        ApiError apiError = new ApiError("Invalid JWT token: "+exception.getMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiError, apiError.getStatusCode());
    }
     @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError > handleAccessDeniedException (AccessDeniedException exception ){
        ApiError apiError = new ApiError("Access denied insufficient permisison: "+exception.getMessage(), HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(apiError, apiError.getStatusCode());
    }
     @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError > handleGenericException (Exception exception ){
        ApiError apiError = new ApiError("Unexpected Error occured: "+exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apiError, apiError.getStatusCode());
    }

}
