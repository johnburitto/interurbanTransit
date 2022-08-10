package com.johnburitto.interurbantransit.model;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class User
 * @version 1.0.0
 * @since 05.08.2022, 12:51
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.form.RegisterForm;
import com.johnburitto.interurbantransit.form.UserForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String login;
    private String password;
    private UserType userType;
    private ContactPerson contactPerson;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(String id, String login, String password, UserType userType, ContactPerson contactPerson) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.userType = userType;
        this.contactPerson = contactPerson;
    }

    public void fillFromForm(UserForm form) {
        contactPerson = new ContactPerson();
        contactPerson.setName(new Name(form.getFirstName(), form.getMiddleName(), form.getLastName()));
        contactPerson.setTelephoneNumber(form.getTelephoneNumber());
        contactPerson.setEMail(form.getEMail());
        login = form.getLogin();
        password = form.getPassword();
        userType = form.getUserType();
    }

    public void fillFromForm(RegisterForm form) {
        contactPerson = new ContactPerson();
        contactPerson.setName(new Name(form.getFirstName(), form.getMiddleName(), form.getLastName()));
        contactPerson.setTelephoneNumber(form.getTelephoneNumber());
        contactPerson.setEMail(form.getEMail());
        login = form.getLogin();
        password = form.getPassword();
        userType = UserType.Guest;
    }

    public void updatePersonalInf(UserForm form) {
        contactPerson = new ContactPerson();
        contactPerson.setName(new Name(form.getFirstName(), form.getMiddleName(), form.getLastName()));
        contactPerson.setTelephoneNumber(form.getTelephoneNumber());
        contactPerson.setEMail(form.getEMail());
    }
}
