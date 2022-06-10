package com.johnburitto.interurbantransit.form;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class PlaceOfWorkForm
 * @version 1.0.0
 * @since 09.06.2022, 11:24
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.PlaceOfWork;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlaceOfWorkForm {
    private String company;
    private String workFrom;
    private String workTo;

    public void fillFromPlaceOfWork(PlaceOfWork placeOfWork) {
        company = placeOfWork.getCompany();
        workFrom = placeOfWork.getWorkFrom().toString();
        workTo = placeOfWork.getWorkTo().toString();
    }
}
