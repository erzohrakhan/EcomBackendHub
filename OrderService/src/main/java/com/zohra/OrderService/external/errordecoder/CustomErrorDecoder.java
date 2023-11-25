package com.zohra.OrderService.external.errordecoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zohra.Core.dto.ErrorResponse;
import com.zohra.OrderService.exception.OrderCustomExcpetion;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CustomErrorDecoder implements ErrorDecoder {
    private static final Logger log = LoggerFactory.getLogger(CustomErrorDecoder.class);
    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("::{}", response.request().url());
        log.info("::{}", response.request().headers());
        try {
            ErrorResponse errorResponse = objectMapper.readValue(
                    response.body().asInputStream(), ErrorResponse.class);
            return new OrderCustomExcpetion(errorResponse.message(), errorResponse.errorCode());
        } catch (IOException e) {
            throw new OrderCustomExcpetion("Internal server error",
                    "INTERNAL_SERVER_ERROR");
        }
    }
}
