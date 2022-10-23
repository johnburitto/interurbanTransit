package com.johnburitto.interurbantransit.form.nosql;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class FlightForm
 * @version 1.0.0
 * @since 20.06.2022, 15:07
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.nosql.Flight;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FlightForm {
    private String transport;
    private String driver;
    private String route;
    private String startDay;
    private String endDay;
    private double costOfTicket;

    public String getStringCostOfTicket() {
        return String.valueOf(costOfTicket);
    }

    public void fillFromFlight(Flight flight) {
        transport = flight.getTransport().getId();
        driver = flight.getDriver().getId();
        route = flight.getRoute().getId();
        startDay = flight.getStartDay().toString();
        endDay = flight.getEndDay().toString();
        costOfTicket = flight.getCostOfTicket();
    }
}
