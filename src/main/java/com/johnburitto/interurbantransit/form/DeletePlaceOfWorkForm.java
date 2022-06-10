package com.johnburitto.interurbantransit.form;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class DeletePlaceOfWorkForm
 * @version 1.0.0
 * @since 10.06.2022, 15:38
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.PlaceOfWork;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeletePlaceOfWorkForm {
    private String infAboutPlaceOfWork;
    public static final String SPLITTER = "_";

    public PlaceOfWork generatePlaceOfWork() {
        String[] list = infAboutPlaceOfWork.split(SPLITTER);

        return new PlaceOfWork(list[0], LocalDate.parse(list[1]), LocalDate.parse(list[2]));
    }
}
