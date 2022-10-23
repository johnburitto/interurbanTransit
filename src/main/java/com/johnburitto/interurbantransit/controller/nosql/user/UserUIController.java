package com.johnburitto.interurbantransit.controller.nosql.user;

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

import com.johnburitto.interurbantransit.form.nosql.UserForm;
import com.johnburitto.interurbantransit.model.nosql.User;
import com.johnburitto.interurbantransit.model.nosql.UserType;
import com.johnburitto.interurbantransit.service.impls.nosql.UserService;
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

        return "/nosql/users-all";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        service.delete(id);

        return "redirect:/ui/v1/keys/paging/7&0";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String adduser(Model model) {
        model.addAttribute("form", new UserForm());
        model.addAttribute("types", UserType.values());

        return "/nosql/user-add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String adduser(@ModelAttribute UserForm form) {
        User userToAdd = new User();

        userToAdd.fillFromForm(form);
        service.create(userToAdd);

        return "redirect:/ui/v1/keys/paging/7&0";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editUser(Model model, @PathVariable String id) {
        User userToEdit = service.get(id);
        UserForm form = new UserForm();

        form.fillFromUser(userToEdit);
        model.addAttribute("form", form);
        model.addAttribute("types", UserType.values());

        return "/nosql/user-edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editUser(@ModelAttribute UserForm form, @PathVariable String id) {
        User userToEdit = service.get(id);

        userToEdit.fillFromForm(form);
        service.update(userToEdit);

        return "redirect:/ui/v1/keys/paging/7&0";
    }

    @RequestMapping("/paging/{size}&{pageNumber}")
    public String getUserInPaging(@PathVariable int size, @PathVariable int pageNumber, Model model) {
        model.addAttribute("users", service.getAllInPage(size, pageNumber));

        return "/nosql/users-paging";
    }
}
