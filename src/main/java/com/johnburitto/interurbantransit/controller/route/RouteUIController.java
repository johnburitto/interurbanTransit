package com.johnburitto.interurbantransit.controller.route;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class RouteUIController
 * @version 1.0.0
 * @since 07.06.2022, 18:36
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.service.impls.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui/v1/routes")
public class RouteUIController {
    @Autowired
    RouteService service;

    @GetMapping("/")
    public String showAll(Model model) {
        model.addAttribute("routes", service.getAll());

        return "routes-all";
    }
}
