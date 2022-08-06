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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ProjectUIController {
    @Autowired
    LogInController logInController;

    @RequestMapping("/tables")
    public String showAllTables(Model model) {
        model.addAttribute("perms", logInController.perms);

        return "start-page";
    }
}
