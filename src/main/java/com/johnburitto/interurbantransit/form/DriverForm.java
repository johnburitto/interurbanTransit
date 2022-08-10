package com.johnburitto.interurbantransit.form;

import com.johnburitto.interurbantransit.model.BloodType;
import com.johnburitto.interurbantransit.model.Driver;
import com.johnburitto.interurbantransit.model.TransportCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class DriverForm
 * @version 1.0.0
 * @since 10.06.2022, 18:39
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

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
        firstName = driver.getPersonalInf().getName().getFirstName();
        middleName = driver.getPersonalInf().getName().getMiddleName();
        lastName = driver.getPersonalInf().getName().getLastName();
        dateOfBirth = driver.getPersonalInf().getDateOfBirth().toString();
        bloodType = driver.getPersonalInf().getBloodType();
        transportCategory = driver.getTransportCategory();
    }
}
