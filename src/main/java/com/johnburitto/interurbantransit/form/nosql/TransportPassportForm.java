package com.johnburitto.interurbantransit.form.nosql;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class TransportPassportForm
 * @version 1.0.0
 * @since 13.06.2022, 13:25
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.nosql.TransportCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransportPassportForm {
    private String transportNumber;
    private TransportCategory neededTransportCategory;
    private int numberOfPlaces;
    private String companyName;
}
