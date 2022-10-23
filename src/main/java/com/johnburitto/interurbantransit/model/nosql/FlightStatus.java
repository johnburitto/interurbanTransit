package com.johnburitto.interurbantransit.model.nosql;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class FlightStatus
 * @version 1.0.0
 * @since 07.06.2022, 15:26
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Status of flight")
public enum FlightStatus {
    Waiting,
    Canceled,
    InRoad,
    Completed,
    Postponed
}
