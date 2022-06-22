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

import com.johnburitto.interurbantransit.form.FlightForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
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
    private double costOfTicket;
    private LocalDate startDay;
    private LocalDate endDay;
    private FlightStatus flightStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Flight(String id, Transport transport, Driver driver, Route route, double costOfTicket, LocalDate startDay,
                  LocalDate endDay, FlightStatus flightStatus) {
        this.id = id;
        this.transport = transport;
        this.driver = driver;
        this.route = route;
        this.costOfTicket = costOfTicket;
        this.startDay = startDay;
        this.endDay = endDay;
        this.flightStatus = flightStatus;
    }

    public String getDatesInRoad() {
        if (startDay.equals(endDay)) {
            return startDay.toString();
        }

        return startDay + "---" + endDay;
    }

    public void fillFromForm(FlightForm form) {
        startDay = LocalDate.parse(form.getStartDay());
        endDay = LocalDate.parse(form.getEndDay());
        costOfTicket = form.getCostOfTicket();
        flightStatus = FlightStatus.Waiting;
    }

    public boolean conditionOfInRoad() {
        if (LocalDate.now().isAfter(startDay) && flightStatus.equals(FlightStatus.Waiting)) {
            return true;
        }
        else return LocalDate.now().equals(startDay) &&
                    LocalTime.now().isAfter(route.getDepartureTime()) &&
                    flightStatus.equals(FlightStatus.Waiting);
    }

    public boolean conditionOfCompleted() {
        if (LocalDate.now().isAfter(endDay) && flightStatus.equals(FlightStatus.InRoad)) {
            return true;
        }
        else return LocalDate.now().equals(endDay) &&
                    LocalTime.now().isAfter(route.getArrivalTime()) &&
                    flightStatus.equals(FlightStatus.InRoad);
    }

    public boolean conditionOfCanceling() {
        return flightStatus.equals(FlightStatus.Waiting) ||
               flightStatus.equals(FlightStatus.InRoad) ||
               flightStatus.equals(FlightStatus.Postponed);
    }

    public boolean conditionOfPostponing() {
        return flightStatus.equals(FlightStatus.Waiting) ||
               flightStatus.equals(FlightStatus.InRoad);
    }

    public boolean conditionOfNeedingNextFlight() {
        return flightStatus.equals(FlightStatus.Completed) ||
               flightStatus.equals(FlightStatus.Canceled);
    }

    private long daysBetweenNextFlight() {
        long daysBetween = ChronoUnit.DAYS.between(startDay, endDay);

        if (daysBetween > 0) {
            return daysBetween + 2;
        }

        return daysBetween + 1;
    }

    public Flight generateNextFlight() {
        Flight nextFlight = new Flight();

        nextFlight.setTransport(transport);
        nextFlight.setDriver(driver);
        nextFlight.setRoute(route);
        nextFlight.setCostOfTicket(costOfTicket);
        nextFlight.setStartDay(startDay.plusDays(daysBetweenNextFlight()));
        nextFlight.setEndDay(endDay.plusDays(daysBetweenNextFlight()));
        nextFlight.setFlightStatus(FlightStatus.Waiting);

        return nextFlight;
    }

    public boolean isCanceled() {
        return flightStatus.equals(FlightStatus.Canceled);
    }

    public boolean isPostponed() {
        return flightStatus.equals(FlightStatus.Postponed);
    }

    public boolean isCompleted() {
        return flightStatus.equals(FlightStatus.Completed);
    }

    public void setNewStatus() {
        if (isCompleted()) {
            flightStatus = FlightStatus.Completed_HasNext;
        }
        else if (isCanceled()) {
            flightStatus = FlightStatus.Canceled_HasNext;
        }
    }

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
