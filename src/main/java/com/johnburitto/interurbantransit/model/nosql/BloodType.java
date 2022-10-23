package com.johnburitto.interurbantransit.model.nosql;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class BloodType
 * @version 1.0.0
 * @since 07.06.2022, 13:33
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Person blood type")
public enum BloodType {
    O_I,
    A_II,
    B_III,
    AB_IV
}
