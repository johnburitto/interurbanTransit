package com.johnburitto.interurbantransit.exceptions;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class ApiExceptionHandler
 * @version 1.0.0
 * @since 10.09.2022, 19:00
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handlerApiRequestException(ApiRequestException e) {
        ApiException apiException = new ApiException(
                e.getMessage(),
                e.getCause(),
                e.getStatus(),
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }
}
