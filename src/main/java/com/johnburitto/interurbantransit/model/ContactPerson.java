package com.johnburitto.interurbantransit.model;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class ContactPerson
 * @version 1.0.0
 * @since 07.06.2022, 13:50
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.form.PassengerForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactPerson {
    private Name name;
    private String telephoneNumber;
    private String eMail;

    public void fillFromForm(PassengerForm form) {
        name = new Name();
        name.setFirstName(form.getFirstName());
        name.setMiddleName(form.getMiddleName());
        name.setLastName(form.getLastName());
        telephoneNumber = form.getTelephoneNumber();
        eMail = form.getEMail();
    }
}
