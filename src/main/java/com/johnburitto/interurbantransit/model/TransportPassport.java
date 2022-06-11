package com.johnburitto.interurbantransit.model;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransportPassport {
    @Id
    private String transportNumber;
    private TransportCategory neededTransportCategory;
    private int numberOfPlaces;
    private double mileage;
    private String companyName;
    private boolean needInspection;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TransportPassport(String transportNumber, TransportCategory neededTransportCategory,
                             int numberOfPlaces, double mileage, String companyName, boolean needInspection) {
        this.transportNumber = transportNumber;
        this.neededTransportCategory = neededTransportCategory;
        this.numberOfPlaces = numberOfPlaces;
        this.mileage = mileage;
        this.companyName = companyName;
        this.needInspection = needInspection;
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
