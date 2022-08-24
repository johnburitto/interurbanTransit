package com.johnburitto.interurbantransit.model;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class BookedPlace
 * @version 1.0.0
 * @since 22.08.2022, 16:01
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
import java.util.Objects;

@Entity
@Table(name = "bookedPlace")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookedPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booked_place_id")
    private Integer id;
    private Integer flight;
    private Integer passenger;
    private LocalDate dayOfBooking;
    private BookedPlaceStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BookedPlace(Flight flight, User passenger, LocalDate dayOfBooking) {
        this.flight = flight.getId();
        this.passenger = passenger.getId();
        this.dayOfBooking = dayOfBooking;
        this.status = BookedPlaceStatus.OK;
    }

    /*public void fillFromForm(BookedPlaceForm form) {
        dayOfBooking = LocalDate.now();
        status = BookedPlaceStatus.OK;
    }*/

    public boolean canBeCanceledRoReturn() {
        return status.equals(BookedPlaceStatus.OK) || status.equals(BookedPlaceStatus.Postponed_OK);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookedPlace that = (BookedPlace) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
