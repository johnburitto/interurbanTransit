package com.johnburitto.interurbantransit.controller;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class LogInController
 * @version 1.0.0
 * @since 24.08.2022, 13:59
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.form.sql.LoginForm;
import com.johnburitto.interurbantransit.form.sql.RegisterForm;
import com.johnburitto.interurbantransit.model.sql.User;
import com.johnburitto.interurbantransit.model.sql.UserPerm;
import com.johnburitto.interurbantransit.service.impls.sql.UserService;
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
    public User user;

    @Autowired
    UserService service;

    @RequestMapping("/")
    public String appOpen() {
        if (perms == null || user == null) {
            return "redirect:/login";
        }

        return "redirect:/sql/tables/paging/5&0";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("form", new LoginForm());

        return "/sql/login-page";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute LoginForm form) {
        if (form.getLogin().equals("") || form.getPassword().equals("")) {
            return "/sql/login-page";
        }

        User currentUser = service.getByLoginAndPassword(form.getLogin(), form.getPassword());

        if (currentUser != null) {
            perms = UserPerm.PermOf(currentUser.getUserType());
            user = currentUser;
        }
        else{
            return "/sql/login-page";
        }

        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout() {
        perms = null;
        user = null;

        return "redirect:/";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("form", new RegisterForm());

        return "/sql/register-page";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute RegisterForm form) {
        if (form.IsHasBlankFields()) {
            return "/sql/register-page";
        }

        User newUser = new User();

        newUser.fillFromForm(form);
        service.create(newUser);

        return "redirect:/";
    }
}
