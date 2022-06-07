package com.johnburitto.interurbantransit.model;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class Flight
 * @version 1.0.0
 * @since 07.06.2022, 13:53
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
public class Flight {
    @Id
    private String id;
    private Transport transport;
    private Driver driver;
    private Route route;
    private Passenger passenger;
    private double costOfTicket;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id.equals(flight.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
