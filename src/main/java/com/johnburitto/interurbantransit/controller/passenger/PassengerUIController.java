package com.johnburitto.interurbantransit.controller.passenger;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class PassengerUIController
 * @version 1.0.0
 * @since 15.06.2022, 14:54
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.form.PassengerForm;
import com.johnburitto.interurbantransit.model.Passenger;
import com.johnburitto.interurbantransit.service.impls.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ui/v1/passengers")
public class PassengerUIController {
    @Autowired
    PassengerService service;

    @RequestMapping("/")
    public String showAll(Model model) {
        model.addAttribute("passengers", service.getAll());

        return "passengers-all";
    }

    @RequestMapping("/delete/{id}")
    public String deletePassenger(@PathVariable String id) {
        service.delete(id);

        return "redirect:/ui/v1/passengers/";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPassenger(Model model) {
        model.addAttribute("form", new PassengerForm());

        return "passenger-add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPassenger(@ModelAttribute("form") PassengerForm form) {
        Passenger passengerToAdd = new Passenger();

        passengerToAdd.fillFromForm(form);
        service.create(passengerToAdd);

        return "redirect:/ui/v1/passengers/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editRoute(Model model, @PathVariable String id) {
        Passenger passengerToEdit = service.get(id);
        PassengerForm passengerForm = new PassengerForm();

        passengerForm.fillFromPassenger(passengerToEdit);
        model.addAttribute("form", passengerForm);

        return "passenger-edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editRoute(@ModelAttribute("form") PassengerForm form, @PathVariable("id") String id) {
        Passenger passengerToEdit = service.get(id);

        passengerToEdit.fillFromForm(form);
        service.update(passengerToEdit);

        return "redirect:/ui/v1/passengers/";
    }
}
