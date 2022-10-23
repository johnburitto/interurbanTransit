package com.johnburitto.interurbantransit.model.nosql;

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

import com.johnburitto.interurbantransit.form.nosql.DriverForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@ApiModel(description = "Represent personal information about driver")
public class PersonalInf {
    @ApiModelProperty(
            value = "Name of person",
            name = "name",
            dataType = "Name",
            example = "")
    private Name name;
    @ApiModelProperty(
            value = "Person date of birth",
            name = "dateOfBirth",
            dataType = "LocalDate",
            example = "2022-10-09")
    private LocalDate dateOfBirth;
    @ApiModelProperty(
            value = "Person blood type",
            name = "bloodType",
            dataType = "BloodType",
            example = "0")
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
