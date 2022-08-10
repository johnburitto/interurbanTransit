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
import com.johnburitto.interurbantransit.service.impls.TransportPassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui/v1/transport-passports")
public class TransportPassportUIController {
    @Autowired
    TransportPassportService service;
    @Autowired
    LogInController logInController;

    @RequestMapping("/")
    public String showAll(Model model) {
        model.addAttribute("passports", service.getAll());
        model.addAttribute("perms", logInController.perms);

        return "transport-passport-all";
    }

    @RequestMapping("/{transportNumber}")
    public String showOne(Model model, @PathVariable String transportNumber) {
        model.addAttribute("passports", service.getOneAsList(transportNumber));
        model.addAttribute("perms", logInController.perms);

        return "transport-passport-all";
    }
}
