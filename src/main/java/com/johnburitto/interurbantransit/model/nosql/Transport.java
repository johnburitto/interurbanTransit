package com.johnburitto.interurbantransit.model.nosql;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class Transport
 * @version 1.0.0
 * @since 07.06.2022, 13:47
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.form.nosql.TransportForm;
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
@ApiModel(description = "Transport of flight")
public class Transport {
    @Id
    private String id;
    @ApiModelProperty(
            value = "Brand of transport",
            name = "brand",
            dataType = "String",
            example = "Mercedes")
    private String brand;
    @ApiModelProperty(
            value = "Model of transport",
            name = "model",
            dataType = "String",
            example = "Vito")
    private String model;
    @ApiModelProperty(
            value = "Transport passport information",
            name = "passport",
            dataType = "TransportPassport",
            example = "")
    private TransportPassport passport;
    @ApiModelProperty(
            value = "Number of booked places",
            name = "numberOfBookedPlaces",
            dataType = "Integer",
            example = "19")
    private int numberOfBookedPlaces;
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

    public Transport(String id, String brand, String model, TransportPassport passport, int numberOfBookedPlaces) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.passport = passport;
        this.numberOfBookedPlaces = numberOfBookedPlaces;
    }

    public void fillFromForm(TransportForm form) {
        TransportPassport passportToAdd = new TransportPassport();

        brand = form.getBrand();
        model = form.getModel();
        passportToAdd.fillFromForm(form);
        passport = passportToAdd;
        numberOfBookedPlaces = 0;
    }

    public String getTransportNumber() {
        return passport.getTransportNumber();
    }

    public void bookPlace() {
        if (numberOfBookedPlaces++ > passport.getNumberOfPlaces()) {
            numberOfBookedPlaces--;
        }
    }

    public void unbookPlace() {
        if (numberOfBookedPlaces-- < 0) {
            numberOfBookedPlaces++;
        }
    }

    public boolean isFree() {
        return numberOfBookedPlaces < passport.getNumberOfPlaces();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transport transport = (Transport) o;
        return id.equals(transport.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
