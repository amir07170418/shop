package org.example.shop.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String message;
    private  Integer code;
    private LocalDateTime time;

    public ErrorResponse(String message, Integer code, LocalDateTime time) {
        this.message = message;
        this.code = code;
        this.time = time;
    }

    public ErrorResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", time=" + time +
                '}';
    }
}
