package com.johnburitto.interurbantransit.form;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class RouteForm
 * @version 1.0.0
 * @since 07.06.2022, 20:02
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.Route;
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
        this.distance = route.getDistance();
        this.departureTime = route.getDepartureTime();
        this.arrivalTime = route.getArrivalTime();
        this.fromCity = route.getFromCity();
        this.toCity = route.getToCity();
    }
}
