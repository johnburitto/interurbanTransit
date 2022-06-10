package com.johnburitto.interurbantransit.model;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class Driver
 * @version 1.0.0
 * @since 07.06.2022, 13:34
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.form.DriverForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {
    @Id
    private String id;
    private PersonalInf personalInf;
    private TransportCategory transportCategory;
    private WorkingBook workingBook;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Driver(String id, PersonalInf personalInf, TransportCategory transportCategory, WorkingBook workingBook) {
        this.id = id;
        this.personalInf = personalInf;
        this.transportCategory = transportCategory;
        this.workingBook = workingBook;
    }

    public void fillFromForm(DriverForm form) {
        PersonalInf personalInfToAdd = new PersonalInf();

        personalInfToAdd.fillFromForm(form);
        personalInf = personalInfToAdd;
        transportCategory = form.getTransportCategory();
    }

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
