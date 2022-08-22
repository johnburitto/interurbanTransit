package com.johnburitto.interurbantransit.model;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class Driver
 * @version 1.0.0
 * @since 22.08.2022, 13:13
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
@Table(name = "driver")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private BloodType bloodType;
    private TransportCategory transportCategory;
    @OneToOne(mappedBy = "driver")
    private Flight flight;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Driver(String firstName, String middleName, String lastName, LocalDate dateOfBirth, BloodType bloodType,
                  TransportCategory transportCategory) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bloodType = bloodType;
        this.transportCategory = transportCategory;
    }

    /*public void fillFromForm(DriverForm form) {
        PersonalInf personalInfToAdd = new PersonalInf();

        personalInfToAdd.fillFromForm(form);
        personalInf = personalInfToAdd;
        transportCategory = form.getTransportCategory();
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return id.equals(driver.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
