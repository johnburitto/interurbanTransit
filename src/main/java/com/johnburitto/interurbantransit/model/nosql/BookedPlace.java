package com.johnburitto.interurbantransit.model.nosql;

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

import com.johnburitto.interurbantransit.form.nosql.BookedPlaceForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Represent who and on which flight booked ticket")
public class BookedPlace {
    @Id
    private String id;
    @ApiModelProperty(
            value = "Represent flight",
            name = "flight",
            dataType = "Flight",
            example = "1")
    private Flight flight;
    @ApiModelProperty(
            value = "Represent passenger(user) of flight",
            name = "passenger",
            dataType = "User",
            example = "1")
    private User passenger;
    @ApiModelProperty(
            value = "Day of ticket booking",
            name = "dayOfBooking",
            dataType = "LocalDate",
            example = "2022-10-09")
    private LocalDate dayOfBooking;
    @ApiModelProperty(
            value = "Booked place status",
            name = "status",
            dataType = "BookedPlaceStatus",
            example = "0")
    private BookedPlaceStatus status;
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
