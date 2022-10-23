package com.johnburitto.interurbantransit.model.sql;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class FlightStatus
 * @version 1.0.0
 * @since 22.08.2022, 15:33
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

public enum FlightStatus {
    Waiting,
    Canceled,
    Canceled_HasNext,
    InRoad,
    Completed,
    Completed_HasNext,
    Postponed
}
