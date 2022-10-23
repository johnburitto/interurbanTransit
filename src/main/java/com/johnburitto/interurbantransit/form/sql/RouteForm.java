package com.johnburitto.interurbantransit.form.sql;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class RouteForm
 * @version 1.0.0
 * @since 24.08.2022, 16:20
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.sql.Route;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class RouteForm {
    private double distance;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String fromCity;
    private String toCity;

    public void fillFromRoute(Route route) {
        distance = route.getDistance();
        departureTime = route.getDepartureTime();
        arrivalTime = route.getArrivalTime();
        fromCity = route.getFromCity();
        toCity = route.getToCity();
    }
}