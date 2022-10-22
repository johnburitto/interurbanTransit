package com.johnburitto.interurbantransit.model;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class Driver
 * @version 1.0.0
 * @since 07.06.2022, 13:34
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.form.DriverForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Driver of flight")
public class Driver {
    @Id
    private String id;
    @ApiModelProperty(
            value = "Personal information about driver",
            name = "personalInf",
            dataType = "PersonalInf",
            example = "")
    private PersonalInf personalInf;
    @ApiModelProperty(
            value = "Driver's transport category",
            name = "transportCategory",
            dataType = "TransportCategory",
            example = "0")
    private TransportCategory transportCategory;
    @ApiModelProperty(
            value = "Date and time of creating object",
            name = "createdAt",
            dataType = "LocalDateTime",
            example = "2017-01-13T17:09:42.411")
    private LocalDateTime createdAt;
    @ApiModelProperty(
            value = "Date and time of last updating of object",
            name = "updatedAt",
            dataType = "LocalDateTime",
            example = "2017-01-13T17:09:42.411")
    private LocalDateTime updatedAt;

    public Driver(String id, PersonalInf personalInf, TransportCategory transportCategory) {
        this.id = id;
        this.personalInf = personalInf;
        this.transportCategory = transportCategory;
    }

    public void fillFromForm(DriverForm form) {
        PersonalInf personalInfToAdd = new PersonalInf();

        personalInfToAdd.fillFromForm(form);
        personalInf = personalInfToAdd;
        transportCategory = form.getTransportCategory();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return id.equals(driver.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
