package com.johnburitto.interurbantransit.form;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class PassengerForm
 * @version 1.0.0
 * @since 15.06.2022, 16:09
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.Passenger;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PassengerForm {
    private String firstName;
    private String middleName;
    private String lastName;
    private String telephoneNumber;
    private String eMail;

    public void fillFromPassenger(Passenger passenger) {
        firstName = passenger.getContactPerson().getName().getFirstName();
        middleName = passenger.getContactPerson().getName().getMiddleName();
        lastName = passenger.getContactPerson().getName().getLastName();
        telephoneNumber = passenger.getContactPerson().getTelephoneNumber();
        eMail = passenger.getContactPerson().getEMail();
    }
}
