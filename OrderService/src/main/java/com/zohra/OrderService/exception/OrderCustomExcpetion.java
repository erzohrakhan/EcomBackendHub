package com.zohra.OrderService.exception;

public class OrderCustomExcpetion extends RuntimeException{
    private String errorCode;

    public OrderCustomExcpetion(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
