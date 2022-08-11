package com.johnburitto.interurbantransit.form;

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

import com.johnburitto.interurbantransit.model.BookedPlace;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
