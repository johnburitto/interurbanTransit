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

import com.johnburitto.interurbantransit.form.nosql.LoginForm;
import com.johnburitto.interurbantransit.form.nosql.RegisterForm;
import com.johnburitto.interurbantransit.model.nosql.ContactPerson;
import com.johnburitto.interurbantransit.model.nosql.User;
import com.johnburitto.interurbantransit.model.nosql.UserPerm;
import com.johnburitto.interurbantransit.service.impls.nosql.UserService;
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
    public ContactPerson contactInf;
    public String currentUserId;

    @Autowired
    UserService service;

    @RequestMapping("/")
    public String appOpen() {
        if (perms == null || contactInf == null) {
            return "redirect:/login";
        }

        return "redirect:/tables/paging/5&0";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("form", new LoginForm());

        return "/nosql/login-page";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute LoginForm form) {
        if (form.getLogin().equals("") || form.getPassword().equals("")) {
            return "/nosql/login-page";
        }

        User currentUser = service.getByLoginAndPassword(form.getLogin(), form.getPassword());

        if (currentUser != null) {
            perms = UserPerm.PermOf(currentUser.getUserType());
            contactInf = currentUser.getContactPerson();
            currentUserId = currentUser.getId();
        }
        else{
            return "/nosql/login-page";
        }

        return "redirect:/tables/paging/5&0";
    }

    @RequestMapping("/logout")
    public String logout() {
        perms = null;
        contactInf = null;
        currentUserId = null;

        return "redirect:/";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("form", new RegisterForm());

        return "/nosql/register-page";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute RegisterForm form) {
        if (form.IsHasBlankFields()) {
            return "/nosql/register-page";
        }

        User newUser = new User();

        newUser.fillFromForm(form);
        service.create(newUser);

        return "redirect:/";
    }
}
