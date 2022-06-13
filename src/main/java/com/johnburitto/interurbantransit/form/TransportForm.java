package com.johnburitto.interurbantransit.form;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class TransportForm
 * @version 1.0.0
 * @since 13.06.2022, 13:26
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.Transport;
import com.johnburitto.interurbantransit.model.TransportPassport;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransportForm extends TransportPassportForm {
    private String brand;
    private String model;

    public void fillFromTransport(Transport transport) {
        brand = transport.getBrand();
        model = transport.getModel();
        setTransportNumber(transport.getTransportNumber());
        setNeededTransportCategory(transport.getPassport().getNeededTransportCategory());
        setNumberOfPlaces(transport.getPassport().getNumberOfPlaces());
        setMileage(transport.getPassport().getMileage());
        setCompanyName(transport.getPassport().getCompanyName());
    }
}
