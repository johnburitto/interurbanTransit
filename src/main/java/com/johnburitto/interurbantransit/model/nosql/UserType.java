package com.johnburitto.interurbantransit.model.nosql;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class UserType
 * @version 1.0.0
 * @since 05.08.2022, 12:58
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import io.swagger.annotations.ApiModel;

@ApiModel(description = "User role")
public enum UserType {
    Owner,
    Administrator,
    Operator,
    Guest
}
