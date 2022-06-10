package com.johnburitto.interurbantransit.model;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class WorkingBook
 * @version 1.0.0
 * @since 08.06.2022, 14:04
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.form.PlaceOfWorkForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkingBook {
    @Id
    private String numberOfWorkingBook;
    private List<PlaceOfWork> placesOfWork;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public WorkingBook(String numberOfWorkingBook) {
        this.numberOfWorkingBook = numberOfWorkingBook;
        this.placesOfWork = new ArrayList<>();
    }

    public void addPlaceOfWork(PlaceOfWorkForm form) {
        PlaceOfWork placeOfWorkToAdd = new PlaceOfWork();

        placeOfWorkToAdd.fillFromForm(form);
        placesOfWork.add(placeOfWorkToAdd);
    }

    public void deletePlaceOfWor(PlaceOfWork placeOfWork) {
        placesOfWork.remove(placeOfWork);
    }

    public double getExpOfWork() {
        double expOfWork = 0;

        for (PlaceOfWork placeOfWork : placesOfWork) {
            expOfWork += placeOfWork.getExpOfWork();
        }

        return expOfWork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkingBook that = (WorkingBook) o;
        return numberOfWorkingBook.equals(that.numberOfWorkingBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfWorkingBook);
    }
}
