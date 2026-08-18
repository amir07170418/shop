package org.example.shop.exception;

import org.springframework.http.HttpStatus;

public class ShopException extends RuntimeException{
    private HttpStatus httpStatus;

    public ShopException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

}
