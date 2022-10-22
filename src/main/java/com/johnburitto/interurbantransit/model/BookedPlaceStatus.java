package com.johnburitto.interurbantransit.model;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class BookedPlaceStatus
 * @version 1.0.0
 * @since 22.06.2022, 13:58
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Booked place status")
public enum BookedPlaceStatus {
    OK,
    Canceled,
    Returned,
    Postponed_OK
}
