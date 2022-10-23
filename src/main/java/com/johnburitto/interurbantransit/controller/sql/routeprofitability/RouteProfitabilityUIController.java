package com.johnburitto.interurbantransit.controller.sql.routeprofitability;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class RoteProfitabilityUIController
 * @version 1.0.0
 * @since 24.08.2022, 16:39
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.controller.LogInController;
import com.johnburitto.interurbantransit.form.sql.RouteProfitabilityForm;
import com.johnburitto.interurbantransit.model.sql.FiltersManager;
import com.johnburitto.interurbantransit.model.sql.RouteProfitability;
import com.johnburitto.interurbantransit.service.impls.sql.RouteProfitabilityService;
import com.johnburitto.interurbantransit.service.impls.sql.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/sql/ui/v1/route-profitabilities")
public class RouteProfitabilityUIController {
    @Autowired
    RouteProfitabilityService routeProfitabilityService;
    @Autowired
    RouteService routeService;
    @Autowired
    LogInController logInController;

    @RequestMapping("/")
    public String showAll(Model model) throws IOException {
        //todo - init all profitabilities
        model.addAttribute("rps", routeProfitabilityService.getAll());
        model.addAttribute("perms", logInController.perms);
        model.addAttribute("filters", FiltersManager.readFromFile("routeProfitabilityFilters.txt"));

        return "/sql/route-profitabilities-all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    private String addRouteProfitability(Model model) {
        model.addAttribute("form", new RouteProfitabilityForm());
        model.addAttribute("routes", routeService.getAll());

        return "/sql/route-profitability-add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private String addRouteProfitability(@ModelAttribute("form") RouteProfitabilityForm form) {
        RouteProfitability routeProfitabilityToAdd = new RouteProfitability();

        routeProfitabilityToAdd.fillFromForm(form);
        routeProfitabilityToAdd.setRoute(routeService.get(form.getRoute()));
        routeProfitabilityService.create(routeProfitabilityToAdd);

        return "redirect:/sql/ui/v1/route-profitabilities/";
    }

    @RequestMapping("/filters/{data}")
    public String saveFilters(@PathVariable String data) throws FileNotFoundException {
        FiltersManager.parseAndSaveToFile(data, "routeProfitabilityFilters.txt");

        return "redirect:/sql/ui/v1/route-profitabilities/";
    }
}
