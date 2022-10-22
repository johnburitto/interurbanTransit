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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Represent user of system")
public class User {
    @Id
    private String id;
    @ApiModelProperty(
            value = "User's login",
            name = "login",
            dataType = "String",
            example = "owner")
    private String login;
    @ApiModelProperty(
            value = "User's password",
            name = "password",
            dataType = "String",
            example = "owner")
    private String password;
    @ApiModelProperty(
            value = "User's role",
            name = "userType",
            dataType = "UserType",
            example = "0")
    private UserType userType;
    @ApiModelProperty(
            value = "Contact information of person",
            name = "contactPerson",
            dataType = "ContactPerson",
            example = "")
    private ContactPerson contactPerson;
    @ApiModelProperty(
            value = "Date and time of creating object",
            name = "createdAt",
            dataType = "LocalDateTime",
            example = "2017-01-13T17:09:42.411")
    private LocalDateTime createdAt;
    @ApiModelProperty(
            value = "Date and time of last updating of object",
            name = "updatedAt",
            dataType = "LocalDateTime",
            example = "2017-01-13T17:09:42.411")
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
