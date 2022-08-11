package com.johnburitto.interurbantransit.model;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class BookedPlace
 * @version 1.0.0
 * @since 21.06.2022, 15:28
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.form.BookedPlaceForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookedPlace {
    @Id
    private String id;
    private Flight flight;
    private User passenger;
    private LocalDate dayOfBooking;
    private BookedPlaceStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BookedPlace(String id, Flight flight, User passenger, LocalDate dayOfBooking) {
        this.id = id;
        this.flight = flight;
        this.passenger = passenger;
        this.dayOfBooking = dayOfBooking;
        this.status = BookedPlaceStatus.OK;
    }

    public void fillFromForm(BookedPlaceForm form) {
        dayOfBooking = LocalDate.now();
        status = BookedPlaceStatus.OK;
    }

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
