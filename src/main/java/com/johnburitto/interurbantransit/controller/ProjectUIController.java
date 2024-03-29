package com.johnburitto.interurbantransit.controller;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class ProjectUIController
 * @version 1.0.0
 * @since 24.08.2022, 14:16
 * @since 24.06.2022, 13:34
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.form.sql.UserForm;
import com.johnburitto.interurbantransit.model.sql.FiltersManager;
import com.johnburitto.interurbantransit.model.sql.User;
import com.johnburitto.interurbantransit.service.impls.sql.BookedPlaceService;
import com.johnburitto.interurbantransit.service.impls.sql.FlightService;
import com.johnburitto.interurbantransit.service.impls.sql.UserService;
import com.johnburitto.interurbantransit.form.nosql.UserForm;
import com.johnburitto.interurbantransit.model.nosql.FiltersManager;
import com.johnburitto.interurbantransit.model.nosql.User;
import com.johnburitto.interurbantransit.service.impls.nosql.BookedPlaceService;
import com.johnburitto.interurbantransit.service.impls.nosql.FlightService;
import com.johnburitto.interurbantransit.service.impls.nosql.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

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

   @RequestMapping("/sql/tables")
    public String showAllTables(Model model) throws IOException {
        model.addAttribute("flights", flightService.updateAndGetAll());
        model.addAttribute("perms", logInController.perms);
        model.addAttribute("user", logInController.user);
        model.addAttribute("filters", FiltersManager.readFromFile("flightFilters.txt"));
        bookedPlaceService.updateAndGetAll();

        return "/sql/start-page";
        
    @RequestMapping("/tables/paging/{size}&{pageNumber}")
    public String showAllTables(Model model, @PathVariable int size, @PathVariable int pageNumber) throws IOException {
        model.addAttribute("flights", flightService.getAllInPage(size, pageNumber));
        model.addAttribute("perms", logInController.perms);
        model.addAttribute("name", logInController.contactInf.getName());
        model.addAttribute("filters", FiltersManager.readFromFile("flightFilters.txt"));
        bookedPlaceService.updateAndGetAll();

        return "/nosql/start-page";
    }

    @RequestMapping("/personal-office")
    public String personalOffice(Model model) {
        model.addAttribute("personalInf", logInController.user);
        model.addAttribute("role", logInController.perms.getType());

        return "/sql/personal-office";

        model.addAttribute("personalInf", logInController.contactInf);
        model.addAttribute("role", logInController.perms.getType());

        return "/nosql/personal-office";
    }

    @RequestMapping(value = "/personal-office/edit", method = RequestMethod.GET)
    public String personalOfficeEdit(Model model) {
        UserForm form = new UserForm();

        form.fillOnlyContactData(logInController.user);
        model.addAttribute("form", form);

        return "/sql/personal-office-edit";
        
        form.fillFromContactPerson(logInController.contactInf);
        model.addAttribute("form", form);

        return "/nosql/personal-office-edit";
    }

    @RequestMapping(value = "/personal-office/edit", method = RequestMethod.POST)
    public String personalOfficeEdit(@ModelAttribute UserForm form) {

        User userToUpdate = logInController.user;

        User userToUpdate = userService.get(logInController.currentUserId);

        userToUpdate.updatePersonalInf(form);
        userService.update(userToUpdate);

        return "redirect:/personal-office";
    }
}
