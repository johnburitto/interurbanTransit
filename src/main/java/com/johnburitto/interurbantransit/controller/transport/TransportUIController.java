package com.johnburitto.interurbantransit.controller.transport;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class TransportUIController
 * @version 1.0.0
 * @since 13.06.2022, 12:59
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.controller.LogInController;
import com.johnburitto.interurbantransit.form.TransportForm;
import com.johnburitto.interurbantransit.model.FiltersManager;
import com.johnburitto.interurbantransit.model.Transport;
import com.johnburitto.interurbantransit.model.TransportCategory;
import com.johnburitto.interurbantransit.service.impls.TransportPassportService;
import com.johnburitto.interurbantransit.service.impls.TransportService;
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
@RequestMapping("/ui/v1/transports")
public class TransportUIController {
    @Autowired
    TransportService transportService;
    @Autowired
    TransportPassportService transportPassportService;
    @Autowired
    LogInController logInController;

    @RequestMapping("/")
    public String showAll(Model model) throws IOException {
        model.addAttribute("transports", transportService.getAll());
        model.addAttribute("perms", logInController.perms);
        model.addAttribute("filters", FiltersManager.readFromFile("transportFilters.txt"));

        return "transports-all";
    }

    @RequestMapping("/{id}")
    public String showOne(Model model, @PathVariable String id) throws IOException {
        model.addAttribute("transports", transportService.getOneAsList(id));
        model.addAttribute("perms", logInController.perms);
        model.addAttribute("filters", FiltersManager.readFromFile("transportFilters.txt"));

        return "transports-all";
    }

    @RequestMapping("/delete/{id}")
    public String deleteOne(@PathVariable String id) {
        transportPassportService.delete(transportService.get(id).getTransportNumber());
        transportService.delete(id);

        return "redirect:/ui/v1/transports/paging/7&0";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addTransport(Model model) {
        model.addAttribute("form", new TransportForm());
        model.addAttribute("categories", TransportCategory.values());

        return "transport-add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addTransport(@ModelAttribute("form") TransportForm form) {
        Transport transportToAdd = new Transport();

        transportToAdd.fillFromForm(form);
        transportService.create(transportToAdd);
        transportPassportService.create(transportToAdd.getPassport());

        return "redirect:/ui/v1/transports/paging/7&0";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editTransport(Model model, @PathVariable String id) {
        Transport transportToEdit = transportService.get(id);
        TransportForm transportForm = new TransportForm();

        transportForm.fillFromTransport(transportToEdit);
        model.addAttribute("form", transportForm);
        model.addAttribute("categories", TransportCategory.values());

        return "transport-edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editTransport(@ModelAttribute("form") TransportForm form, @PathVariable("id") String id) {
        Transport transportToEdit = transportService.get(id);

        transportToEdit.fillFromForm(form);
        transportService.update(transportToEdit);
        transportPassportService.update(transportToEdit.getPassport());

        return "redirect:/ui/v1/transports/paging/7&0";
    }

    @RequestMapping("/filters/{data}")
    public String saveFilters(@PathVariable String data) throws FileNotFoundException {
        FiltersManager.parseAndSaveToFile(data, "transportFilters.txt");

        return "redirect:/ui/v1/transports/paging/7&0";
    }

    @RequestMapping("/paging/{size}&{pageNumber}")
    public String getUserInPaging(@PathVariable int size, @PathVariable int pageNumber, Model model) throws IOException {
        model.addAttribute("transports", transportService.getAllInPage(size, pageNumber));
        model.addAttribute("perms", logInController.perms);
        model.addAttribute("filters", FiltersManager.readFromFile("transportFilters.txt"));

        return "transport-paging";
    }
}
