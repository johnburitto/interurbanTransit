package com.johnburitto.interurbantransit.form.sql;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class FlightPostponeForm
 * @version 1.0.0
 * @since 25.08.2022, 14:52
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.sql.Flight;
import com.johnburitto.interurbantransit.model.sql.Route;
import com.johnburitto.interurbantransit.service.impls.sql.RouteService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class FlightPostponeForm {
    @Autowired
    RouteService routeService;
    private String startDay;
    private String endDay;
    private LocalTime departureTime;
    private LocalTime arrivalTime;

    public void fillFromFlight(Flight flight) {
        Route route = routeService.get(flight.getRoute());

        startDay = flight.getStartDay().toString();
        endDay = flight.getEndDay().toString();
        departureTime = route.getDepartureTime();
        arrivalTime = route.getArrivalTime();
    }
}
