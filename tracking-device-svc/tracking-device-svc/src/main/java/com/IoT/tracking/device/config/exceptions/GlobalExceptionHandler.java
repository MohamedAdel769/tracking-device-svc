package com.IoT.tracking.device.config.exceptions;

import com.IoT.tracking.device.entity.dto.TextResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<TextResponse> handleBusinessException(final BusinessException e) {
        return new ResponseEntity<>(new TextResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
