package com.johnburitto.interurbantransit.model.nosql;

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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Represent name of person")
public class Name {
    @ApiModelProperty(
            value = "First name of person",
            name = "firstName",
            dataType = "String",
            example = "Петро")
    private String firstName;
    @ApiModelProperty(
            value = "Middle name of person",
            name = "middleName",
            dataType = "String",
            example = "Петрович")
    private String middleName;
    @ApiModelProperty(
            value = "Last name of person",
            name = "lastName",
            dataType = "String",
            example = "Петренко")
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
