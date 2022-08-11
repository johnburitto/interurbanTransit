package com.johnburitto.interurbantransit.controller.transportpassport;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class TransportPassportUIController
 * @version 1.0.0
 * @since 11.06.2022, 15:03
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.controller.LogInController;
import com.johnburitto.interurbantransit.model.FiltersManager;
import com.johnburitto.interurbantransit.service.impls.TransportPassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/ui/v1/transport-passports")
public class TransportPassportUIController {
    @Autowired
    TransportPassportService service;
    @Autowired
    LogInController logInController;

    @RequestMapping("/")
    public String showAll(Model model) throws IOException {
        model.addAttribute("passports", service.getAll());
        model.addAttribute("perms", logInController.perms);
        model.addAttribute("filters", FiltersManager.readFromFile("transportPassportPlaceFilters.txt"));

        return "transport-passport-all";
    }

    @RequestMapping("/{transportNumber}")
    public String showOne(Model model, @PathVariable String transportNumber) throws IOException {
        model.addAttribute("passports", service.getOneAsList(transportNumber));
        model.addAttribute("filters", FiltersManager.readFromFile("transportPassportPlaceFilters.txt"));
        model.addAttribute("perms", logInController.perms);

        return "transport-passport-all";
    }

    @RequestMapping("/filters/{data}")
    public String saveFilters(@PathVariable String data) throws FileNotFoundException {
        FiltersManager.parseAndSaveToFile(data, "transportPassportPlaceFilters.txt");

        return "redirect:/ui/v1/transport-passports/";
    }
}
