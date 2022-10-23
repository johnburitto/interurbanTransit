package com.johnburitto.interurbantransit.model.sql;

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

import com.johnburitto.interurbantransit.form.sql.TransportForm;
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

    public void fillFromForm(TransportForm form) {
        brand = form.getBrand();
        model = form.getModel();
        transportNumber = form.getTransportNumber();
        neededTransportCategory = form.getNeededTransportCategory();
        numberOfPlaces = form.getNumberOfPlaces();
        numberOfBookedPlaces = 0;
        companyName = form.getCompanyName();
    }

    public void bookPlace() {
        if (numberOfBookedPlaces++ > numberOfPlaces) {
            numberOfBookedPlaces--;
        }
    }

    public void unbookPlace() {
        if (numberOfBookedPlaces-- < 0) {
            numberOfBookedPlaces++;
        }
    }

    public boolean isFree() {
        return numberOfBookedPlaces < numberOfPlaces;
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
