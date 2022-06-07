package com.johnburitto.interurbantransit.model;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class Name
 * @version 1.0.0
 * @since 07.06.2022, 13:30
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Name {
    private String firstName;
    private String middleName;
    private String lastName;
}
