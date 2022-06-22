package com.johnburitto.interurbantransit.form;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class FlightPostponeForm
 * @version 1.0.0
 * @since 22.06.2022, 21:34
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class FlightPostponeForm {
    private String startDay;
    private String endDay;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
}
