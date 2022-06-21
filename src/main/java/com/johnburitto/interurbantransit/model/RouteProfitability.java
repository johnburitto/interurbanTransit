package com.johnburitto.interurbantransit.model;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class RouteProfitabilityService
 * @version 1.0.0
 * @since 21.06.2022, 22:35
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.form.RouteProfitabilityForm;
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
public class RouteProfitability {
    @Id
    private String id;
    private Route route;
    private LocalDate startDay;
    private LocalDate endDay;
    private int numberOfPassengers;
    private int profitability;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public RouteProfitability(String id, Route route, LocalDate startDay, LocalDate endDay) {
        this.id = id;
        this.route = route;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public void fillFromForm(RouteProfitabilityForm form) {
        startDay = LocalDate.parse(form.getStartDay());
        endDay = LocalDate.parse(form.getEndDay());
        numberOfPassengers = 0;
        profitability = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteProfitability that = (RouteProfitability) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
