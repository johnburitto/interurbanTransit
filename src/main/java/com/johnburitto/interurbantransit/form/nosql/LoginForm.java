package com.johnburitto.interurbantransit.form.nosql;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class LoginForm
 * @version 1.0.0
 * @since 05.08.2022, 14:59
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginForm {
    private String login;
    private String password;
}
