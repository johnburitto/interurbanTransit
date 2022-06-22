package com.johnburitto.interurbantransit.model;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class Transport
 * @version 1.0.0
 * @since 07.06.2022, 13:47
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.form.TransportForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transport {
    @Id
    private String id;
    private String brand;
    private String model;
    private TransportPassport passport;
    private int numberOfBookedPlaces;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Transport(String id, String brand, String model, TransportPassport passport, int numberOfBookedPlaces) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.passport = passport;
        this.numberOfBookedPlaces = numberOfBookedPlaces;
    }

    public void fillFromForm(TransportForm form) {
        TransportPassport passportToAdd = new TransportPassport();

        brand = form.getBrand();
        model = form.getModel();
        passportToAdd.fillFromForm(form);
        passport = passportToAdd;
        numberOfBookedPlaces = 0;
    }

    public String getTransportNumber() {
        return passport.getTransportNumber();
    }

    public void bookPlace() {
        if (numberOfBookedPlaces++ > passport.getNumberOfPlaces()) {
            numberOfBookedPlaces--;
        }
    }

    public void unbookPlace() {
        if (numberOfBookedPlaces-- < 0) {
            numberOfBookedPlaces++;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transport transport = (Transport) o;
        return id.equals(transport.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
