package com.johnburitto.interurbantransit.controller;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class LogInController
 * @version 1.0.0
 * @since 05.08.2022, 12:36
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.form.LoginForm;
import com.johnburitto.interurbantransit.model.User;
import com.johnburitto.interurbantransit.model.UserPerm;
import com.johnburitto.interurbantransit.service.impls.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class LogInController {
    public UserPerm perms;

    @Autowired
    UserService service;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("form", new LoginForm());

        return "login-page";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute LoginForm form) {
        if (form.getLogin().equals("") || form.getPassword().equals("")) {
            return "login-page";
        }

        User currentUser = service.getByLoginAndPassword(form.getLogin(), form.getPassword());

        if (currentUser != null) {
            perms = UserPerm.PermOf(currentUser.getUserType());
        }
        else{
            return "login-page";
        }

        return "redirect:/";
    }
}
