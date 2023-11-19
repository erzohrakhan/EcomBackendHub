package com.zohra.UserService.exception;

public class UserServiceException extends RuntimeException {
    private String errorCode;

    public UserServiceException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}