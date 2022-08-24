package com.johnburitto.interurbantransit.form;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class RegisterForm
 * @version 1.0.0
 * @since 24.08.2022, 14:01
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterForm {
    private String firstName;
    private String middleName;
    private String lastName;
    private String telephoneNumber;
    private String eMail;
    private String login;
    private String password;

    public boolean IsHasBlankFields() {
        return firstName.equals("") || middleName.equals("") || lastName.equals("") || telephoneNumber.equals("") ||
                eMail.equals("") || login.equals("") || password.equals("");
    }
}
