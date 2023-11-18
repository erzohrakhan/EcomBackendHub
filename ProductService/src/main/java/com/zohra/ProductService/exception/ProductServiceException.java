package com.zohra.ProductService.exception;

public class ProductServiceException extends RuntimeException {
  private String errorCode;

  public ProductServiceException(String message, String errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  public String getErrorCode() {
    return errorCode;
  }
}
