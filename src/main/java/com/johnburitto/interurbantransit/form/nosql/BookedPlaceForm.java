package com.johnburitto.interurbantransit.form.nosql;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class BookedPlaceForm
 * @version 1.0.0
 * @since 21.06.2022, 16:44
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.nosql.BookedPlace;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookedPlaceForm {
    private String flight;
    private String passenger;
    private int bookedPlace;

    public void fillFromBookedPlace(BookedPlace bookedPlaceObj) {
        flight = bookedPlaceObj.getFlight().getId();
        passenger = bookedPlaceObj.getPassenger().getId();
    }
}
