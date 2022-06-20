package com.johnburitto.interurbantransit.model;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class Passenger
 * @version 1.0.0
 * @since 07.06.2022, 13:51
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.form.PassengerForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {
    @Id
    private String id;
    private ContactPerson contactPerson;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Passenger(String id, ContactPerson contactPerson) {
        this.id = id;
        this.contactPerson = contactPerson;
    }

    public void fillFromForm(PassengerForm form) {
        ContactPerson contactPersonToAdd = new ContactPerson();
        contactPersonToAdd.fillFromForm(form);
        contactPerson = contactPersonToAdd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return id.equals(passenger.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
