package com.example.spring_api_nhansu.exception;

public class UserNotException extends RuntimeException {
    public UserNotException(String message) {
        super(message);
    }
}
