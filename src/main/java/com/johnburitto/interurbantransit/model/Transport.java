package com.johnburitto.interurbantransit.model;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class Transport
 * @version 1.0.0
 * @since 22.08.2022, 15:17
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "transport")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transport_id")
    private Integer id;
    private String brand;
    private String model;
    private String transportNumber;
    private TransportCategory neededTransportCategory;
    private int numberOfPlaces;
    private String companyName;
    private int numberOfBookedPlaces;
    @OneToOne(mappedBy = "transport")
    private Flight flight;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Transport(String brand, String model, String transportNumber, TransportCategory neededTransportCategory,
                     int numberOfPlaces, String companyName) {
        this.brand = brand;
        this.model = model;
        this.transportNumber = transportNumber;
        this.neededTransportCategory = neededTransportCategory;
        this.numberOfPlaces = numberOfPlaces;
        this.companyName = companyName;
    }

    /*public void fillFromForm(TransportForm form) {
        TransportPassport passportToAdd = new TransportPassport();

        brand = form.getBrand();
        model = form.getModel();
        passportToAdd.fillFromForm(form);
        passport = passportToAdd;
        numberOfBookedPlaces = 0;
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

    public boolean isFree() {
        return numberOfBookedPlaces < passport.getNumberOfPlaces();
    }*/

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
