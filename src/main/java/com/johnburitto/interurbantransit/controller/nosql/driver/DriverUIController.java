package com.johnburitto.interurbantransit.controller.nosql.driver;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class DriverUIController
 * @version 1.0.0
 * @since 10.06.2022, 17:02
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.controller.LogInController;
import com.johnburitto.interurbantransit.form.nosql.DriverForm;
import com.johnburitto.interurbantransit.model.nosql.BloodType;
import com.johnburitto.interurbantransit.model.nosql.Driver;
import com.johnburitto.interurbantransit.model.nosql.FiltersManager;
import com.johnburitto.interurbantransit.model.nosql.TransportCategory;
import com.johnburitto.interurbantransit.service.impls.nosql.DriverService;
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
@RequestMapping("/ui/v1/drivers")
public class DriverUIController {
    @Autowired
    DriverService driverService;
    @Autowired
    LogInController logInController;

    @RequestMapping("/")
    public String showAll(Model model) throws IOException {
        model.addAttribute("drivers", driverService.getAll());
        model.addAttribute("perms", logInController.perms);
        model.addAttribute("filters", FiltersManager.readFromFile("driverFilters.txt"));

        return "/nosql/drivers-all";
    }

    @RequestMapping("/delete/{id}")
    public String deleteDriver(@PathVariable String id) {
        driverService.delete(id);

        return "redirect:/ui/v1/drivers/paging/7&0";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addDriver(Model model) {
        model.addAttribute("form", new DriverForm());
        model.addAttribute("bloodTypes", BloodType.values());
        model.addAttribute("categories", TransportCategory.values());

        return "/nosql/driver-add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addDriver(@ModelAttribute("form") DriverForm form) {
        Driver driverToAdd = new Driver();

        driverToAdd.fillFromForm(form);
        driverService.create(driverToAdd);

        return "redirect:/ui/v1/drivers/paging/7&0";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editDriver(Model model, @PathVariable String id) {
        Driver driverToEdit = driverService.get(id);
        DriverForm driverForm = new DriverForm();

        driverForm.fillFromDriver(driverToEdit);
        model.addAttribute("form", driverForm);
        model.addAttribute("bloodTypes", BloodType.values());
        model.addAttribute("categories", TransportCategory.values());

        return "/nosql/driver-edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editDriver(@ModelAttribute("form") DriverForm form, @PathVariable("id") String id) {
        Driver driverToEdit = driverService.get(id);

        driverToEdit.fillFromForm(form);
        driverService.update(driverToEdit);

        return "redirect:/ui/v1/drivers/paging/7&0";
    }

    @RequestMapping("/filters/{data}")
    public String saveFilters(@PathVariable String data) throws FileNotFoundException {
        FiltersManager.parseAndSaveToFile(data, "driverFilters.txt");

        return "redirect:/ui/v1/drivers/paging/7&0";
    }

    @RequestMapping("/paging/{size}&{pageNumber}")
    public String getUserInPaging(@PathVariable int size, @PathVariable int pageNumber, Model model) throws IOException {
        model.addAttribute("drivers", driverService.getAllInPage(size, pageNumber));
        model.addAttribute("perms", logInController.perms);
        model.addAttribute("filters", FiltersManager.readFromFile("driverFilters.txt"));

        return "/nosql/drivers-paging";
    }
}
