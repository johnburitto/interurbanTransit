package com.johnburitto.interurbantransit.model;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class ContactPerson
 * @version 1.0.0
 * @since 07.06.2022, 13:50
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Represent contact information of person")
public class ContactPerson {
    private Name name;
    @ApiModelProperty(
            value = "Telephone number",
            name = "telephoneNumber",
            dataType = "String",
            example = "+380000000000")
    private String telephoneNumber;
    @ApiModelProperty(
            value = "E-mail",
            name = "eMail",
            dataType = "String",
            example = "example@example.com")
    private String eMail;
}
