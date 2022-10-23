package com.johnburitto.interurbantransit.annotations;

/*
 * @author Denta
 * @project interurbanTransit
 * @class LoggIt
 * @version 1.0.0
 * @since 16.10.2022, 15:38
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggIt {

}
