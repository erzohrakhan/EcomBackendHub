package com.zohra.UserService.exception;

import com.zohra.Core.dto.ErrorResponse;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleUserNotFoundExcpetion(
      UserNotFoundException exception) {
    ResponseEntity<ErrorResponse> errorResponseResponseEntity =
        new ResponseEntity<>(
            new ErrorResponse(exception.getMessage(), exception.getErrorCode()),
            HttpStatus.NOT_FOUND);
    return errorResponseResponseEntity;
  }

  @ExceptionHandler(ValidationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleValidationException(ValidationException exception) {
    return ResponseEntity.badRequest().body(exception.getMessage());
  }

  @ExceptionHandler(UserServiceException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ErrorResponse> handleProductServiceException(
      UserServiceException exception) {
    return new ResponseEntity<>(
        new ErrorResponse(exception.getMessage(), exception.getErrorCode()), HttpStatus.NOT_FOUND);
  }
}
