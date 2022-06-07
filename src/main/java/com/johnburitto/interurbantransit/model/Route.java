package com.johnburitto.interurbantransit.model;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class Route
 * @version 1.0.0
 * @since 07.06.2022, 13:39
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.form.RouteForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    @Id
    private String id;
    private double distance;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String fromCity;
    private String toCity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Route(String id, double distance, LocalTime departureTime, LocalTime arrivalTime,
                 String fromCity, String toCity) {
        this.id = id;
        this.distance = distance;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.fromCity = fromCity;
        this.toCity = toCity;
    }

    public void fillFromForm(RouteForm form) {
        this.distance = form.getDistance();
        this.departureTime = form.getDepartureTime();
        this.arrivalTime = form.getArrivalTime();
        this.fromCity = form.getFromCity();
        this.toCity = form.getToCity();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return id.equals(route.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
