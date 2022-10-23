package com.johnburitto.interurbantransit.form.sql;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class TransportForm
 * @version 1.0.0
 * @since 24.08.2022, 16:08
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.sql.Transport;
import com.johnburitto.interurbantransit.model.sql.TransportCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransportForm {
    private String brand;
    private String model;
    private String transportNumber;
    private TransportCategory neededTransportCategory;
    private int numberOfPlaces;
    private String companyName;

    public void fillFromTransport(Transport transport) {
        brand = transport.getBrand();
        model = transport.getModel();
        transportNumber = transport.getTransportNumber();
        neededTransportCategory = transport.getNeededTransportCategory();
        numberOfPlaces = transport.getNumberOfPlaces();
        companyName = transport.getCompanyName();
    }
}
