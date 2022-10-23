package com.johnburitto.interurbantransit.model.sql;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class Route
 * @version 1.0.0
 * @since 22.08.2022, 15:09
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.form.sql.RouteForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "route")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private Integer id;
    private double distance;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String fromCity;
    private String toCity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Route(double distance, LocalTime departureTime, LocalTime arrivalTime, String fromCity, String toCity) {
        this.distance = distance;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.fromCity = fromCity;
        this.toCity = toCity;
    }

    public void fillFromForm(RouteForm form) {
        distance = form.getDistance();
        departureTime = form.getDepartureTime();
        arrivalTime = form.getArrivalTime();
        fromCity = form.getFromCity();
        toCity = form.getToCity();
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
