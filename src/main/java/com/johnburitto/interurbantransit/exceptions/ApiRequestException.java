package com.johnburitto.interurbantransit.exceptions;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class ApiRequestException
 * @version 1.0.0
 * @since 10.09.2022, 18:57
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiRequestException extends RuntimeException {
    private final HttpStatus status;

    public ApiRequestException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ApiRequestException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }
}
