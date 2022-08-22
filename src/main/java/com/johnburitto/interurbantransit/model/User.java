package com.johnburitto.interurbantransit.model;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class User
 * @version 1.0.0
 * @since 22.08.2022, 15:53
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    private String login;
    private String password;
    private UserType userType;
    private String firstName;
    private String middleName;
    private String lastName;
    private String telephoneNumber;
    private String eMail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(String login, String password, UserType userType, String firstName, String middleName,
                String lastName, String telephoneNumber, String eMail) {
        this.login = login;
        this.password = password;
        this.userType = userType;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.telephoneNumber = telephoneNumber;
        this.eMail = eMail;
    }

    /*public void fillFromForm(UserForm form) {
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
    }*/
}