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
