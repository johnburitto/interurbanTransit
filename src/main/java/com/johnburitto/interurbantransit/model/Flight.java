package com.johnburitto.interurbantransit.model;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class Flight
 * @version 1.0.0
 * @since 22.08.2022, 15:31
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "flight")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private Integer transport;
    private Integer driver;
    private Integer route;
    private double costOfTicket;
    private LocalDate startDay;
    private LocalDate endDay;
    private FlightStatus flightStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Flight(Transport transport, Driver driver, Route route, double costOfTicket, LocalDate startDay,
                  LocalDate endDay) {
        this.transport = transport.getId();
        this.driver = driver.getId();
        this.route = route.getId();
        this.costOfTicket = costOfTicket;
        this.startDay = startDay;
        this.endDay = endDay;
        this.flightStatus = FlightStatus.Waiting;
    }

    public String getDatesInRoad() {
        if (startDay.equals(endDay)) {
            return startDay.toString();
        }

        return startDay + "---" + endDay;
    }

    /*public void fillFromForm(FlightForm form) {
        startDay = LocalDate.parse(form.getStartDay());
        endDay = LocalDate.parse(form.getEndDay());
        costOfTicket = form.getCostOfTicket();
        flightStatus = FlightStatus.Waiting;
    }*/

    /*public boolean conditionOfInRoad() {
        if (LocalDate.now().isAfter(startDay) && (flightStatus.equals(FlightStatus.Waiting) ||
                flightStatus.equals(FlightStatus.Postponed))) {
            return true;
        }
        else return LocalDate.now().equals(startDay) &&
                LocalTime.now().isAfter(route.getDepartureTime()) &&
                (flightStatus.equals(FlightStatus.Waiting) || flightStatus.equals(FlightStatus.Postponed));
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
                flightStatus.equals(FlightStatus.InRoad) ||
                flightStatus.equals(FlightStatus.Postponed);
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
    }*/

    public String flightStatusToString() {
        switch (flightStatus) {
            case Canceled:
            case Canceled_HasNext: {
                return "canceled";
            }
            case Completed:
            case Completed_HasNext: {
                return "completed";
            }
            case Waiting: {
                return "waiting";
            }
            case Postponed: {
                return "postponed";
            }
            case InRoad: {
                return "in-road";
            }
            default: return "";
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
