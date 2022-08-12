package com.johnburitto.interurbantransit.controller;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class ProjectUIController
 * @version 1.0.0
 * @since 24.06.2022, 13:34
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.form.UserForm;
import com.johnburitto.interurbantransit.model.FiltersManager;
import com.johnburitto.interurbantransit.model.User;
import com.johnburitto.interurbantransit.service.impls.BookedPlaceService;
import com.johnburitto.interurbantransit.service.impls.FlightService;
import com.johnburitto.interurbantransit.service.impls.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.time.LocalDate;

@Controller
@RequestMapping("")
public class ProjectUIController {
    @Autowired
    LogInController logInController;
    @Autowired
    FlightService flightService;
    @Autowired
    BookedPlaceService bookedPlaceService;
    @Autowired
    UserService userService;

    @RequestMapping("/tables")
    public String showAllTables(Model model) throws IOException {
        model.addAttribute("flights", flightService.updateAndGetByStartDay(LocalDate.now()));
        model.addAttribute("perms", logInController.perms);
        model.addAttribute("name", logInController.contactInf.getName());
        model.addAttribute("filters", FiltersManager.readFromFile("flightFilters.txt"));
        bookedPlaceService.updateAndGetAll();

        return "start-page";
    }

    @RequestMapping("/personal-office")
    public String personalOffice(Model model) {
        model.addAttribute("personalInf", logInController.contactInf);
        model.addAttribute("role", logInController.perms.getType());

        return "personal-office";
    }

    @RequestMapping(value = "/personal-office/edit", method = RequestMethod.GET)
    public String personalOfficeEdit(Model model) {
        UserForm form = new UserForm();

        form.fillFromContactPerson(logInController.contactInf);
        model.addAttribute("form", form);

        return "personal-office-edit";
    }

    @RequestMapping(value = "/personal-office/edit", method = RequestMethod.POST)
    public String personalOfficeEdit(@ModelAttribute UserForm form) {
        User userToUpdate = userService.get(logInController.currentUserId);

        userToUpdate.updatePersonalInf(form);
        userService.update(userToUpdate);

        return "redirect:/personal-office";
    }
}
