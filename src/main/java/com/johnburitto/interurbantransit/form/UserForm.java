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

import com.johnburitto.interurbantransit.model.User;
import com.johnburitto.interurbantransit.model.UserType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserForm {
    private String login;
    private String password;
    private UserType userType;

    public void fillFromUser(User user) {
        login = user.getLogin();
        password = user.getPassword();
        userType = user.getUserType();
    }
}
