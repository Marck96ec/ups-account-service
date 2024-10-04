package com.ms.account.service.globalexceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.customer.service.server.models.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidAccountException.class)
    public ResponseEntity<String> handleInvalidAccountException(InvalidAccountException ex) {
        ObjectMapper objectMapper = new ObjectMapper();
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        try {
            String json = objectMapper.writeValueAsString(errorResponse);
            return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while processing error response", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}