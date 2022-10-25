package com.johnburitto.interurbantransit.controller.sql.flight;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class FlightUIController
 * @version 1.0.0
 * @since 25.08.2022, 14:49
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.controller.LogInController;
import com.johnburitto.interurbantransit.form.sql.FlightForm;
import com.johnburitto.interurbantransit.form.sql.FlightPostponeForm;
import com.johnburitto.interurbantransit.model.sql.Flight;
import com.johnburitto.interurbantransit.model.sql.FiltersManager;
import com.johnburitto.interurbantransit.service.impls.sql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/sql/ui/v1/flights")
public class FlightUIController {
    @Autowired
    FlightService flightService;
    @Autowired
    TransportService transportService;
    @Autowired
    DriverService driverService;
    @Autowired
    RouteService routeService;
    @Autowired
    BookedPlaceService bookedPlaceService;
    @Autowired
    LogInController logInController;

    @RequestMapping("/delete/{id}")
    public String deleteFlight(@PathVariable Integer id) {
        flightService.delete(id);

        return "redirect:/sql/ui/v1/flights/";
    }

    @RequestMapping("/cancel/{id}")
    public String cancelFlight(@PathVariable Integer id) {
        flightService.cancel(id);

        return "redirect:/sql/ui/v1/booked-places/redirect/flights";
    }

    @RequestMapping(value = "/postpone/{id}", method = RequestMethod.GET)
    public String postponeFlight(Model model, @PathVariable Integer id) {
        Flight flightToPostpone = flightService.get(id);
        FlightPostponeForm flightPostponeForm = new FlightPostponeForm();

        flightPostponeForm.fillFromFlight(flightToPostpone);
        model.addAttribute("form", flightPostponeForm);

        return "/sql/flight-postpone";
    }

    @RequestMapping(value = "/postpone/{id}", method = RequestMethod.POST)
    public String postponeFlight(@ModelAttribute("form") FlightPostponeForm form, @PathVariable Integer id) {
        flightService.postpone(id, form);

        return "redirect:/tables";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createFlight(Model model) {
        model.addAttribute("form", new FlightForm());
        model.addAttribute("transports", transportService.getAll());
        model.addAttribute("drivers", driverService.getAll());
        model.addAttribute("routes", routeService.getAll());

        return "/sql/flight-add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String createFlight(@ModelAttribute("form") FlightForm form) {
        Flight flightToAdd = new Flight();

        flightToAdd.fillFromForm(form);
        flightToAdd.setTransport(form.getTransport());
        flightToAdd.setDriver(form.getDriver());
        flightToAdd.setRoute(form.getRoute());
        flightService.create(flightToAdd);

        return "redirect:/sql/tables";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editFlight(Model model, @PathVariable Integer id) {
        Flight flightToEdit = flightService.get(id);
        FlightForm flightForm = new FlightForm();

        flightForm.fillFromFlight(flightToEdit);
        model.addAttribute("form", flightForm);
        model.addAttribute("transports", transportService.getAll());
        model.addAttribute("drivers", driverService.getAll());
        model.addAttribute("routes", routeService.getAll());
        model.addAttribute("currentTransport", transportService.get(flightForm.getTransport()));
        model.addAttribute("currentDriver", driverService.get(flightForm.getDriver()));
        model.addAttribute("currentRoute", routeService.get(flightForm.getRoute()));

        return "/sql/flight-edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editFlight(@ModelAttribute("form") FlightForm form, @PathVariable("id") Integer id) {
        Flight flightToEdit = flightService.get(id);

        flightToEdit.fillFromForm(form);
        flightToEdit.setTransport(form.getTransport());
        flightToEdit.setDriver(form.getDriver());
        flightToEdit.setRoute(form.getRoute());
        flightService.update(flightToEdit);

        return "redirect:/sql/tables";
    }

    @RequestMapping("/{flightStatus}")
    public String showAllFlightByStatus(Model model, @PathVariable String flightStatus) throws IOException {
        List<Flight> flights = flightService.getAllFlightsByStatus(flightStatus);

        model.addAttribute("flights", flights);
        model.addAttribute("numberOfBookedPlaces", bookedPlaceService.getNumberOfAllBookedPlaces(flights));
        model.addAttribute("perms", logInController.perms);
        model.addAttribute("perms", logInController.perms);
        model.addAttribute("filters", FiltersManager.readFromFile("flightFilters.txt"));

        return "/sql/flights-all-with-number-of-booked-places";
    }

    @RequestMapping("/{flightStatus}/route/{id}")
    public String showAllFlightByRouteAndStatus(Model model, @PathVariable Integer id,
                                                @PathVariable String flightStatus) throws IOException {
        List<Flight> flights = flightService.getAllFlightsByRouteAndStatus(id, flightStatus);

        model.addAttribute("flights", flights);
        model.addAttribute("numberOfBookedPlaces", bookedPlaceService.getNumberOfAllBookedPlaces(flights));
        model.addAttribute("perms", logInController.perms);
        model.addAttribute("filters", FiltersManager.readFromFile("flightFilters.txt"));

        return "/sql/flights-all-with-number-of-booked-places";
    }

    @RequestMapping("/{id}/{flightStatus}/passengers")
    public String showAllPassengersOfFlightByStatus(Model model, @PathVariable Integer id,
                                                    @PathVariable String flightStatus) {
        model.addAttribute("passengers", flightService.getAllPassengersOfFlight(id, flightStatus));
        model.addAttribute("perms", logInController.perms);

        return "/sql/passengers-all";
    }

    @RequestMapping("/filters/{data}")
    public String saveFilters(@PathVariable String data) throws FileNotFoundException {
        FiltersManager.parseAndSaveToFile(data, "flightFilters.txt");

        return "redirect:/sql/tables";
    }

    @RequestMapping("/paging/{size}&{pageNumber}")
    public String getUserInPaging(@PathVariable int size, @PathVariable int pageNumber, Model model) throws IOException {
        model.addAttribute("flights", flightService.getAllInPage(size, pageNumber));
        model.addAttribute("perms", logInController.perms);
        model.addAttribute("filters", FiltersManager.readFromFile("flightFilters.txt"));

        return "/sql/flight-paging";
    }
}
