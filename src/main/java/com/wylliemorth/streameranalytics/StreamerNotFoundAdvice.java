package com.wylliemorth.streameranalytics;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class StreamerNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(StreamerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String streamerNotFoundHandler(StreamerNotFoundException ex) {
        return ex.getMessage();
    }
}