package com.zohra.UserService.exception;

public class UserNotFoundException extends RuntimeException{
        private String errorCode;
        public UserNotFoundException(String message, String errorCode) {
            super(message);
            this.errorCode = errorCode;
        }
        public String getErrorCode() {
            return errorCode;
        }
}
