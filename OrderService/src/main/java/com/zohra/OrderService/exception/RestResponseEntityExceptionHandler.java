package com.zohra.OrderService.exception;

import com.zohra.Core.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(OrderCustomExcpetion.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleProductServiceException(
            OrderCustomExcpetion exception) {
        return new ResponseEntity<>(
                new ErrorResponse(exception.getMessage(), exception.getErrorCode()), HttpStatus.NOT_FOUND);
    }
}
