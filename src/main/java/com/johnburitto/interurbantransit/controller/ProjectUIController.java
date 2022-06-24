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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ProjectUIController {
    @RequestMapping("/")
    public String showAllTables() {
        return "start-page";
    }
}
