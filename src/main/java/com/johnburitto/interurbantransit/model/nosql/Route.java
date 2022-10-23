package com.johnburitto.interurbantransit.model.nosql;

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

import com.johnburitto.interurbantransit.form.nosql.RouteForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Route of flight")
public class Route {
    @Id
    private String id;
    @ApiModelProperty(
            value = "Length of route",
            name = "distance",
            dataType = "Double",
            example = "100.10")
    private double distance;
    @ApiModelProperty(
            value = "Departure time",
            name = "departureTime",
            dataType = "LocalDate",
            example = "2022-10-09")
    private LocalTime departureTime;
    @ApiModelProperty(
            value = "Arrival time",
            name = "arrivalTime",
            dataType = "LocalDate",
            example = "2022-10-09")
    private LocalTime arrivalTime;
    @ApiModelProperty(
            value = "Departure city",
            name = "fromCity",
            dataType = "String",
            example = "Вижниця")
    private String fromCity;
    @ApiModelProperty(
            value = "Arrival time",
            name = "toCity",
            dataType = "String",
            example = "Чернівці")
    private String toCity;
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
