package com.johnburitto.interurbantransit.form;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class UserForm
 * @version 1.0.0
 * @since 24.08.2022, 16:47
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.User;
import com.johnburitto.interurbantransit.model.UserType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserForm {
    private String firstName;
    private String middleName;
    private String lastName;
    private String telephoneNumber;
    private String eMail;
    private String login;
    private String password;
    private UserType userType;

    public void fillFromUser(User user) {
        firstName = user.getFirstName();
        middleName = user.getMiddleName();
        lastName = user.getLastName();
        telephoneNumber = user.getTelephoneNumber();
        eMail = user.getEMail();
        login = user.getLogin();
        password = user.getPassword();
        userType = user.getUserType();
    }

    public void fillOnlyContactData(User user) {
        firstName = user.getFirstName();
        middleName = user.getMiddleName();
        lastName = user.getLastName();
        telephoneNumber = user.getTelephoneNumber();
        eMail = user.getEMail();
        userType = user.getUserType();
    }
}
