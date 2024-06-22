package com.crater.accounting.exception;

import com.crater.accounting.bean.response.Status;
import com.crater.accounting.bean.response.globalExceptionHandler.GlobalExceptionHandlerResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    public GlobalExceptionHandlerResponse handleException(Exception e) {
        return new GlobalExceptionHandlerResponse(new Status(false, "Unknown exception", e.getMessage()));
    }

    @ExceptionHandler(DbException.class)
    public GlobalExceptionHandlerResponse handleDbException(DbException e) {
        return new GlobalExceptionHandlerResponse(new Status(false, "Database exception", e.getMessage()));
    }

    @ExceptionHandler(RequestFormatException.class)
    public GlobalExceptionHandlerResponse handleRequestFormatException(RequestFormatException e) {
        return new GlobalExceptionHandlerResponse(new Status(false, "Request format exception", e.getMessage()));
    }

    @ExceptionHandler(DataNotFoundException.class)
    public GlobalExceptionHandlerResponse handleDataNotFoundException(DataNotFoundException e) {
        return new GlobalExceptionHandlerResponse(new Status(false, "Data not found exception", e.getMessage()));
    }
}
