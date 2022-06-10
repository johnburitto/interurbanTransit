package com.johnburitto.interurbantransit.controller.driver;

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

import com.johnburitto.interurbantransit.form.DriverForm;
import com.johnburitto.interurbantransit.model.BloodType;
import com.johnburitto.interurbantransit.model.Driver;
import com.johnburitto.interurbantransit.model.TransportCategory;
import com.johnburitto.interurbantransit.model.WorkingBook;
import com.johnburitto.interurbantransit.service.impls.DriverService;
import com.johnburitto.interurbantransit.service.impls.WorkingBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ui/v1/drivers")
public class DriverUIController {
    @Autowired
    DriverService driverService;
    @Autowired
    WorkingBookService workingBookService;

    @RequestMapping("/")
    public String showAll(Model model) {
        model.addAttribute("drivers", driverService.getAll());

        return "drivers-all";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        workingBookService.delete(driverService.getWorkingBookNumber(id));
        driverService.delete(id);

        return "redirect:/ui/v1/drivers/";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addDriver(Model model) {
        model.addAttribute("form", new DriverForm());
        model.addAttribute("bloodTypes", BloodType.values());
        model.addAttribute("categories", TransportCategory.values());

        return "driver-add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addDriver(@ModelAttribute("form") DriverForm form) {
        WorkingBook workingBookToAdd = new WorkingBook(form.getWorkingBookNumber());
        Driver driverToAdd = new Driver();

        workingBookService.create(workingBookToAdd);
        driverToAdd.fillFromForm(form);
        driverToAdd.setWorkingBook(workingBookService.get(form.getWorkingBookNumber()));
        driverService.create(driverToAdd);

        return "redirect:/ui/v1/drivers/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editDriver(Model model, @PathVariable String id) {
        Driver driverToEdit = driverService.get(id);
        DriverForm driverForm = new DriverForm();

        driverForm.fillFromDriver(driverToEdit);
        model.addAttribute("form", driverForm);
        model.addAttribute("bloodTypes", BloodType.values());
        model.addAttribute("categories", TransportCategory.values());

        return "driver-edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editDriver(@ModelAttribute("form") DriverForm form, @PathVariable("id") String id) {
        Driver driverToEdit = driverService.get(id);

        driverToEdit.fillFromForm(form);
        driverService.update(driverToEdit);

        return "redirect:/ui/v1/drivers/";
    }
}
