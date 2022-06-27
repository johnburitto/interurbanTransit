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

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Name {
    private String firstName;
    private String middleName;
    private String lastName;
    private final static String NAME_SEPARATOR = "-";

    public static Name parse(String name) {
        List<String> components = Arrays.asList(name.split(NAME_SEPARATOR));

        return new Name(components.get(0), components.get(1), components.get(2));
    }

    public String nameForURL() {
        return firstName + "-" + middleName + "-" + lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + middleName + " " + lastName;
    }
}
