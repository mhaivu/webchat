package com.interview.webchat.handlers;

import com.interview.webchat.payload.GeneralResponse;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.NoSuchAlgorithmException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<?> handle(final BadCredentialsException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GeneralResponse("Failed to authenticate."));
    }

    @ExceptionHandler(value = NoSuchAlgorithmException.class)
    public ResponseEntity<?> handle(final NoSuchAlgorithmException exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GeneralResponse("Internal Server Error."));
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> handle(final NotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GeneralResponse("Invalid data."));
    }
}
