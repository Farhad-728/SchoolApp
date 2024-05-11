package com.example.schoolapp.handler;


import com.example.schoolapp.dto.FailureResponse;
import com.example.schoolapp.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<FailureResponse> handleNotFoundException(Exception ex) {
        return buildFailureResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    private ResponseEntity<FailureResponse> buildFailureResponse(HttpStatus status, String errorDetail) {
        log.error("Exception occurred: {} ", errorDetail);
        FailureResponse response = FailureResponse.builder()
                .status(String.valueOf(status.value()))
                .error(status.name())
                .errorDetail(errorDetail)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status)
                .body(response);
    }
}