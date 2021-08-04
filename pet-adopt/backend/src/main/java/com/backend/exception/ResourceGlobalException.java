package com.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceGlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception e, HttpServletRequest request){
        ApiError error = new ApiError(request.getRequestURI(), e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleGlobalException(BadRequestException e, HttpServletRequest request){
        ApiError error = new ApiError(request.getRequestURI(), e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, error.getStatus());
    }




}
