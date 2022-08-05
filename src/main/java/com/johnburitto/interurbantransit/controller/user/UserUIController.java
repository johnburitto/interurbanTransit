package com.johnburitto.interurbantransit.controller.user;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class UserUIController
 * @version 1.0.0
 * @since 05.08.2022, 14:02
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.form.UserForm;
import com.johnburitto.interurbantransit.model.User;
import com.johnburitto.interurbantransit.model.UserType;
import com.johnburitto.interurbantransit.service.impls.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ui/v1/keys")
public class UserUIController {
    @Autowired
    UserService service;

    @RequestMapping("/")
    public String showAll(Model model) {
        model.addAttribute("users", service.getAll());

        return "users-all";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        service.delete(id);

        return "redirect:/ui/v1/keys/";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String adduser(Model model) {
        model.addAttribute("form", new UserForm());
        model.addAttribute("types", UserType.values());

        return "user-add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String adduser(@ModelAttribute UserForm form) {
        User userToAdd = new User();

        userToAdd.fillFromForm(form);
        service.create(userToAdd);

        return "redirect:/ui/v1/keys/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editUser(Model model, @PathVariable String id) {
        User userToEdit = service.get(id);
        UserForm form = new UserForm();

        form.fillFromUser(userToEdit);
        model.addAttribute("form", form);
        model.addAttribute("types", UserType.values());

        return "user-edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editUser(@ModelAttribute UserForm form, @PathVariable String id) {
        User userToEdit = service.get(id);

        userToEdit.fillFromForm(form);
        service.update(userToEdit);

        return "redirect:/ui/v1/keys/";
    }
}
