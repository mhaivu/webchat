package com.interview.webchat.handlers;

import com.interview.webchat.payload.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<?> handle(final BadCredentialsException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Failed to authenticate."));
    }
}
