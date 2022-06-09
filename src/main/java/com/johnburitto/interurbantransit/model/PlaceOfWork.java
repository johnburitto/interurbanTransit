package com.johnburitto.interurbantransit.model;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class PlaceOfWork
 * @version 1.0.0
 * @since 08.06.2022, 13:54
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.form.PlaceOfWorkForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOfWork {
    private String company;
    private LocalDate workFrom;
    private LocalDate workTo;

    public void fillFromForm(PlaceOfWorkForm form) {
        company = form.getCompany();
        workFrom = LocalDate.parse(form.getWorkFrom());
        workTo = LocalDate.parse(form.getWorkTo());
    }
}
