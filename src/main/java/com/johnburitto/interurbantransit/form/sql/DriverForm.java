package com.johnburitto.interurbantransit.form.sql;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class DriverForm
 * @version 1.0.0
 * @since 24.08.2022, 15:09
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.sql.BloodType;
import com.johnburitto.interurbantransit.model.sql.Driver;
import com.johnburitto.interurbantransit.model.sql.TransportCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DriverForm {
    private String firstName;
    private String middleName;
    private String lastName;
    private String dateOfBirth;
    private BloodType bloodType;
    private TransportCategory transportCategory;

    public void fillFromDriver(Driver driver) {
        firstName = driver.getFirstName();
        middleName = driver.getMiddleName();
        lastName = driver.getLastName();
        dateOfBirth = driver.getDateOfBirth().toString();
        bloodType = driver.getBloodType();
        transportCategory = driver.getTransportCategory();
    }
}
