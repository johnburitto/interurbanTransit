package com.johnburitto.interurbantransit.model.nosql;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class TransportPassport
 * @version 1.0.0
 * @since 07.06.2022, 13:44
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
@ApiModel(description = "Represent \"personal\" information about transport")
public class TransportPassport {
    @Id
    private String transportNumber;
    @ApiModelProperty(
            value = "Transport category",
            name = "neededTransportCategory",
            dataType = "TransportCategory",
            example = "0")
    private TransportCategory neededTransportCategory;
    @ApiModelProperty(
            value = "General number of places",
            name = "numberOfPlaces",
            dataType = "Integer",
            example = "19")
    private int numberOfPlaces;
    @ApiModelProperty(
            value = "Name of company which transport belongs",
            name = "companyName",
            dataType = "String",
            example = "\"Capitan soap\"")
    private String companyName;
    private boolean needInspection;
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

    public TransportPassport(String transportNumber, TransportCategory neededTransportCategory,
                             int numberOfPlaces, String companyName, boolean needInspection) {
        this.transportNumber = transportNumber;
        this.neededTransportCategory = neededTransportCategory;
        this.numberOfPlaces = numberOfPlaces;
        this.companyName = companyName;
        this.needInspection = needInspection;
    }

    public void fillFromForm(TransportForm form) {
        transportNumber = form.getTransportNumber();
        neededTransportCategory = form.getNeededTransportCategory();
        numberOfPlaces = form.getNumberOfPlaces();
        companyName = form.getCompanyName();
        needInspection = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransportPassport that = (TransportPassport) o;
        return transportNumber.equals(that.transportNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transportNumber);
    }
}
