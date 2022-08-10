package com.johnburitto.interurbantransit.form;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class UserForm
 * @version 1.0.0
 * @since 05.08.2022, 14:23
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.ContactPerson;
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
        firstName = user.getContactPerson().getName().getFirstName();
        middleName = user.getContactPerson().getName().getMiddleName();
        lastName = user.getContactPerson().getName().getLastName();
        telephoneNumber = user.getContactPerson().getTelephoneNumber();
        eMail = user.getContactPerson().getEMail();
        login = user.getLogin();
        password = user.getPassword();
        userType = user.getUserType();
    }

    public void fillFromContactPerson(ContactPerson contactPerson) {
        firstName = contactPerson.getName().getFirstName();
        middleName = contactPerson.getName().getMiddleName();
        lastName = contactPerson.getName().getLastName();
        telephoneNumber = contactPerson.getTelephoneNumber();
        eMail = contactPerson.getEMail();
    }
}
