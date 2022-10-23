package com.johnburitto.interurbantransit.controller.nosql.routeprofitability;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class RouteProfitabilityUIController
 * @version 1.0.0
 * @since 21.06.2022, 23:24
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.controller.LogInController;
import com.johnburitto.interurbantransit.form.nosql.RouteProfitabilityForm;
import com.johnburitto.interurbantransit.model.nosql.FiltersManager;
import com.johnburitto.interurbantransit.model.nosql.RouteProfitability;
import com.johnburitto.interurbantransit.service.impls.nosql.RouteProfitabilityService;
import com.johnburitto.interurbantransit.service.impls.nosql.RouteService;
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
@RequestMapping("/ui/v1/route-profitabilities")
public class RouteProfitabilityUIController {
    @Autowired
    RouteProfitabilityService routeProfitabilityService;
    @Autowired
    RouteService routeService;
    @Autowired
    LogInController logInController;

    @RequestMapping("/")
    public String showAll(Model model) throws IOException {
        model.addAttribute("rps", routeProfitabilityService.initAndGetAll());
        model.addAttribute("perms", logInController.perms);
        model.addAttribute("filters", FiltersManager.readFromFile("routeProfitabilityFilters.txt"));

        return "/nosql/route-profitability-all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    private String addRouteProfitability(Model model) {
        model.addAttribute("form", new RouteProfitabilityForm());
        model.addAttribute("routes", routeService.getAll());

        return "/nosql/route-profitability-add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private String addRouteProfitability(@ModelAttribute("form") RouteProfitabilityForm form) {
        RouteProfitability routeProfitabilityToAdd = new RouteProfitability();

        routeProfitabilityToAdd.fillFromForm(form);
        routeProfitabilityToAdd.setRoute(routeService.get(form.getRoute()));
        routeProfitabilityService.create(routeProfitabilityToAdd);

        return "redirect:/ui/v1/route-profitabilities/paging/7&0";
    }

    @RequestMapping("/filters/{data}")
    public String saveFilters(@PathVariable String data) throws FileNotFoundException {
        FiltersManager.parseAndSaveToFile(data, "routeProfitabilityFilters.txt");

        return "redirect:/ui/v1/route-profitabilities/paging/7&0";
    }

    @RequestMapping("/paging/{size}&{pageNumber}")
    public String getUserInPaging(@PathVariable int size, @PathVariable int pageNumber, Model model) throws IOException {
        model.addAttribute("rps", routeProfitabilityService.getAllInPage(size, pageNumber));
        model.addAttribute("perms", logInController.perms);
        model.addAttribute("filters", FiltersManager.readFromFile("routeProfitabilityFilters.txt"));

        return "/nosql/route-profitability-paging";
    }
}
