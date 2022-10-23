package com.johnburitto.interurbantransit.model.nosql;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class RouteProfitabilityService
 * @version 1.0.0
 * @since 21.06.2022, 22:35
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.form.nosql.RouteProfitabilityForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Represent profitability of routes")
public class RouteProfitability {
    @Id
    private String id;
    @ApiModelProperty(
            value = "Route",
            name = "route",
            dataType = "Route",
            example = "")
    private Route route;
    @ApiModelProperty(
            value = "Start day of calculating",
            name = "startDay",
            dataType = "LocalDate",
            example = "2022-10-09")
    private LocalDate startDay;
    @ApiModelProperty(
            value = "End day of calculating",
            name = "endDay",
            dataType = "LocalDate",
            example = "2022-10-09")
    private LocalDate endDay;
    @ApiModelProperty(
            value = "Number of passengers",
            name = "numberOfPassengers",
            dataType = "Integer",
            example = "56")
    private int numberOfPassengers;
    @ApiModelProperty(
            value = "Profitability of route",
            name = "profitability",
            dataType = "Integer",
            example = "56")
    private int profitability;
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

    public RouteProfitability(String id, Route route, LocalDate startDay, LocalDate endDay) {
        this.id = id;
        this.route = route;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public void setNumberOfPassengersAndCalcProfitability(List<BookedPlace> allPlaces) {
        numberOfPassengers = 0;
        profitability = 0;

        numberOfPassengers = (int) allPlaces.stream()
                .filter(bookedPlace ->
                                bookedPlace.getStatus().equals(BookedPlaceStatus.OK) ||
                                bookedPlace.getStatus().equals(BookedPlaceStatus.Postponed_OK))
                .count();

        allPlaces.stream()
                .filter(bookedPlace ->
                                bookedPlace.getStatus().equals(BookedPlaceStatus.OK) ||
                                bookedPlace.getStatus().equals(BookedPlaceStatus.Postponed_OK))
                .forEach(bookedPlace -> profitability += bookedPlace.getFlight().getCostOfTicket());
    }

    public void fillFromForm(RouteProfitabilityForm form) {
        startDay = LocalDate.parse(form.getStartDay());
        endDay = LocalDate.parse(form.getEndDay());
        numberOfPassengers = 0;
        profitability = 0;
    }

    public long daysOfAccrual() {
        return ChronoUnit.DAYS.between(startDay, endDay);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteProfitability that = (RouteProfitability) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
