package com.johnburitto.interurbantransit.form;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class FlightForm
 * @version 1.0.0
 * @since 25.08.2022, 14:50
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.Flight;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FlightForm {
    private Integer transport;
    private Integer driver;
    private Integer route;
    private String startDay;
    private String endDay;
    private double costOfTicket;

    public String getStringCostOfTicket() {
        return String.valueOf(costOfTicket);
    }

    public void fillFromFlight(Flight flight) {
        transport = flight.getTransport();
        driver = flight.getDriver();
        route = flight.getRoute();
        startDay = flight.getStartDay().toString();
        endDay = flight.getEndDay().toString();
        costOfTicket = flight.getCostOfTicket();
    }
}
