package com.johnburitto.interurbantransit.model;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class PersonalInf
 * @version 1.0.0
 * @since 07.06.2022, 13:32
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.form.DriverForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PersonalInf {
    private Name name;
    private LocalDate dateOfBirth;
    private BloodType bloodType;

    public PersonalInf() {
        this.name = new Name();
    }

    public void fillFromForm(DriverForm form) {
        name.setFirstName(form.getFirstName());
        name.setMiddleName(form.getMiddleName());
        name.setLastName(form.getLastName());
        dateOfBirth = LocalDate.parse(form.getDateOfBirth());
        bloodType = form.getBloodType();
    }
}
