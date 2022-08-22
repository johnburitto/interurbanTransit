package com.johnburitto.interurbantransit.model;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class RouteProfitability
 * @version 1.0.0
 * @since 22.08.2022, 16:00
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "routeProfitability")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteProfitability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_profitability_id")
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "route", referencedColumnName = "route_id")
    private Route route;
    private LocalDate startDay;
    private LocalDate endDay;
    private int numberOfPassengers;
    private int profitability;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public RouteProfitability(Route route, LocalDate startDay, LocalDate endDay, int numberOfPassengers, int profitability) {
        this.route = route;
        this.startDay = startDay;
        this.endDay = endDay;
        this.numberOfPassengers = numberOfPassengers;
        this.profitability = profitability;
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

    /*public void fillFromForm(RouteProfitabilityForm form) {
        startDay = LocalDate.parse(form.getStartDay());
        endDay = LocalDate.parse(form.getEndDay());
        numberOfPassengers = 0;
        profitability = 0;
    }*/

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