package com.johnburitto.interurbantransit.controller.route;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class RouteUIController
 * @version 1.0.0
 * @since 24.08.2022, 16:19
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.controller.LogInController;
import com.johnburitto.interurbantransit.form.RouteForm;
import com.johnburitto.interurbantransit.model.FiltersManager;
import com.johnburitto.interurbantransit.model.Route;
import com.johnburitto.interurbantransit.service.impls.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/sql/ui/v1/routes")
public class RouteUIController {
    @Autowired
    RouteService service;
    @Autowired
    LogInController logInController;

    @RequestMapping("/")
    public String showAll(Model model) throws IOException {
        model.addAttribute("routes", service.getAll());
        model.addAttribute("perms", logInController.perms);
        model.addAttribute("filters", FiltersManager.readFromFile("routeFilters.txt"));

        return "routes-all";
    }

    @RequestMapping("/delete/{id}")
    public String deleteRoute(@PathVariable Integer id) {
        service.delete(id);

        return "redirect:/sql/ui/v1/routes/";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addRoute(Model model) {
        model.addAttribute("form", new RouteForm());

        return "route-add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addRoute(@ModelAttribute("form") RouteForm routeForm) {
        Route routeToAdd = new Route();

        routeToAdd.fillFromForm(routeForm);
        service.create(routeToAdd);

        return "redirect:/sql/ui/v1/routes/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editRoute(Model model, @PathVariable Integer id) {
        Route routeToEdit = service.get(id);
        RouteForm routeForm = new RouteForm();

        routeForm.fillFromRoute(routeToEdit);
        model.addAttribute("form", routeForm);

        return "route-edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editRoute(@ModelAttribute("form") RouteForm routeForm, @PathVariable("id") Integer id) {
        Route routeToEdit = service.get(id);

        routeToEdit.fillFromForm(routeForm);
        service.update(routeToEdit);

        return "redirect:/sql/ui/v1/routes/";
    }

    @RequestMapping("/filters/{data}")
    public String saveFilters(@PathVariable String data) throws FileNotFoundException {
        FiltersManager.parseAndSaveToFile(data, "routeFilters.txt");

        return "redirect:/sql/ui/v1/routes/";
    }
}
