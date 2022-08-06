package com.johnburitto.interurbantransit.controller.flight;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class FlightUIController
 * @version 1.0.0
 * @since 20.06.2022, 13:08
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.controller.LogInController;
import com.johnburitto.interurbantransit.form.FlightForm;
import com.johnburitto.interurbantransit.form.FlightPostponeForm;
import com.johnburitto.interurbantransit.model.Flight;
import com.johnburitto.interurbantransit.model.FlightStatus;
import com.johnburitto.interurbantransit.service.impls.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/ui/v1/flights")
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

    @RequestMapping("/")
    public String showAll(Model model) {
        model.addAttribute("flights", flightService.updateAndGetAll());
        model.addAttribute("perms", logInController.perms);
        bookedPlaceService.updateAndGetAll();

        return "flights-all";
    }

    @RequestMapping("/delete/{id}")
    public String deleteFlight(@PathVariable String id) {
        flightService.delete(id);

        return "redirect:/ui/v1/flights/";
    }

    @RequestMapping("/cancel/{id}")
    public String cancelFlight(@PathVariable String id) {
        flightService.cancel(id);

        return "redirect:/ui/v1/booked-places/redirect/flights";
    }

    @RequestMapping(value = "/postpone/{id}", method = RequestMethod.GET)
    public String postponeFlight(Model model, @PathVariable String id) {
        Flight flightToPostpone = flightService.get(id);
        FlightPostponeForm flightPostponeForm = new FlightPostponeForm();

        flightPostponeForm.fillFromFlight(flightToPostpone);
        model.addAttribute("form", flightPostponeForm);

        return "flight-postpone";
    }

    @RequestMapping(value = "/postpone/{id}", method = RequestMethod.POST)
    public String postponeFlight(@ModelAttribute("form") FlightPostponeForm form, @PathVariable String id) {
        flightService.postpone(id, form);

        return "redirect:/ui/v1/flights/";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createFlight(Model model) {
        model.addAttribute("form", new FlightForm());
        model.addAttribute("transports", transportService.getAllFreeTransports(flightService.getAllBusyTransports()));
        model.addAttribute("drivers", driverService.getAllFreeDrivers(flightService.getAllBusyDrivers()));
        model.addAttribute("routes", routeService.getAllFreeRoutes(flightService.getAllRouteInRoads()));

        return "flight-add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String createFlight(@ModelAttribute("form") FlightForm form) {
        Flight flightToAdd = new Flight();

        flightToAdd.fillFromForm(form);
        flightToAdd.setTransport(transportService.get(form.getTransport()));
        flightToAdd.setDriver(driverService.get(form.getDriver()));
        flightToAdd.setRoute(routeService.get(form.getRoute()));
        flightService.create(flightToAdd);

        return "redirect:/ui/v1/flights/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editFlight(Model model, @PathVariable String id) {
        Flight flightToEdit = flightService.get(id);
        FlightForm flightForm = new FlightForm();

        flightForm.fillFromFlight(flightToEdit);
        model.addAttribute("form", flightForm);
        model.addAttribute("transports", transportService.getAllFreeTransports(flightService.getAllBusyTransports()));
        model.addAttribute("drivers", driverService.getAllFreeDrivers(flightService.getAllBusyDrivers()));
        model.addAttribute("routes", routeService.getAllFreeRoutes(flightService.getAllRouteInRoads()));
        model.addAttribute("currentTransport", transportService.get(flightForm.getTransport()));
        model.addAttribute("currentDriver", driverService.get(flightForm.getDriver()));
        model.addAttribute("currentRoute", routeService.get(flightForm.getRoute()));

        return "flight-edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editFlight(@ModelAttribute("form") FlightForm form, @PathVariable("id") String id) {
        Flight flightToEdit = flightService.get(id);

        flightToEdit.fillFromForm(form);
        flightToEdit.setTransport(transportService.get(form.getTransport()));
        flightToEdit.setDriver(driverService.get(form.getDriver()));
        flightToEdit.setRoute(routeService.get(form.getRoute()));
        flightService.update(flightToEdit);

        return "redirect:/ui/v1/flights/";
    }

    @RequestMapping("/{flightStatus}")
    public String showAllFlightByStatus(Model model, @PathVariable String flightStatus) {
        List<Flight> flights = flightService.getAllFlightsByStatus(flightStatus);

        model.addAttribute("flights", flights);
        model.addAttribute("numberOfBookedPlaces", bookedPlaceService.getNumberOfAllBookedPlaces(flights));
        model.addAttribute("perms", logInController.perms);
        model.addAttribute("perms", logInController.perms);

        return "flights-all-with-number-of-booked-places";
    }

    @RequestMapping("/{flightStatus}/route/{id}")
    public String showAllFlightByRouteAndStatus(Model model, @PathVariable String id,
                                                @PathVariable String flightStatus) {
        List<Flight> flights = flightService.getAllFlightsByRouteAndStatus(id, flightStatus);

        model.addAttribute("flights", flights);
        model.addAttribute("numberOfBookedPlaces", bookedPlaceService.getNumberOfAllBookedPlaces(flights));
        model.addAttribute("perms", logInController.perms);

        return "flights-all-with-number-of-booked-places";
    }

    @RequestMapping("/{id}/{flightStatus}/passengers")
    public String showAllPassengersOfFlightByStatus(Model model, @PathVariable String id,
                                                    @PathVariable String flightStatus) {
        model.addAttribute("passengers", flightService.getAllPassengersOfFlight(id, flightStatus));
        model.addAttribute("perms", logInController.perms);

        return "passengers-all";
    }
}
