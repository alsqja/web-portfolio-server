package com.example.webportfolio.global.error;

import io.jsonwebtoken.JwtException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResDto<HttpStatusCode>> handleResponseStatusException(ResponseStatusException ex) {

        return new ResponseEntity<>(
                new ErrorResDto<>(
                        ex.getStatusCode().value(),
                        ex.getStatusCode(),
                        ex.getReason(),
                        System.currentTimeMillis()
                ), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResDto<HttpStatus>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {

        return new ResponseEntity<>(
                new ErrorResDto<>(
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST,
                        "check your data type",
                        System.currentTimeMillis()
                ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResDto<HttpStatus>> handleValidException(MethodArgumentNotValidException ex) {

        return new ResponseEntity<>(
                new ErrorResDto<>(
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST,
                        Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage(),
                        System.currentTimeMillis()
                ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResDto<HttpStatus>> handleAuthException(AuthenticationException e) {

        return new ResponseEntity<>(
                new ErrorResDto<>(
                        HttpStatus.UNAUTHORIZED.value(),
                        HttpStatus.UNAUTHORIZED,
                        "invalid token",
                        System.currentTimeMillis()
                ), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResDto<HttpStatus>> handleAccessDeniedException(AccessDeniedException e) {

        return new ResponseEntity<>(
                new ErrorResDto<>(
                        HttpStatus.FORBIDDEN.value(),
                        HttpStatus.FORBIDDEN,
                        "access denied",
                        System.currentTimeMillis()
                ), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ErrorResDto<HttpStatus>> handleAuthorizationDeniedException(AuthorizationDeniedException e) {

        return new ResponseEntity<>(
                new ErrorResDto<>(
                        HttpStatus.FORBIDDEN.value(),
                        HttpStatus.FORBIDDEN,
                        "access denied",
                        System.currentTimeMillis()
                ), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResDto<HttpStatus>> handleJwtException(JwtException e) {

        return new ResponseEntity<>(
                new ErrorResDto<>(
                        HttpStatus.UNAUTHORIZED.value(),
                        HttpStatus.UNAUTHORIZED,
                        "invalid token",
                        System.currentTimeMillis()
                ), HttpStatus.UNAUTHORIZED);
    }

    @Getter
    public static class ErrorResDto<T> {
        private final Integer status;
        private final T error;
        private final String message;
        private final Long timestamp;

        public ErrorResDto(Integer status, T error, String message, Long timestamp) {
            this.status = status;
            this.message = message;
            this.error = error;
            this.timestamp = timestamp;
        }
    }
}
