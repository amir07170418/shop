package org.example.shop.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ShopException.class)
    public ResponseEntity<ErrorResponse> shopExceptionHandler(ShopException e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setCode(e.getHttpStatus().value());
        errorResponse.setTime(LocalDateTime.now());
        return ResponseEntity.status(e.getHttpStatus()).body(errorResponse);
    }
}
