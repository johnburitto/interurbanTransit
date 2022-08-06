package com.johnburitto.interurbantransit.controller.bookedplace;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class BookedPlaceUIController
 * @version 1.0.0
 * @since 21.06.2022, 16:02
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.controller.LogInController;
import com.johnburitto.interurbantransit.form.BookedPlaceForm;
import com.johnburitto.interurbantransit.model.*;
import com.johnburitto.interurbantransit.service.impls.BookedPlaceService;
import com.johnburitto.interurbantransit.service.impls.FlightService;
import com.johnburitto.interurbantransit.service.impls.PassengerService;
import com.johnburitto.interurbantransit.service.impls.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/ui/v1/booked-places")
public class BookedPlaceUIController {
    @Autowired
    BookedPlaceService bookedPlaceService;
    @Autowired
    FlightService flightService;
    @Autowired
    PassengerService passengerService;
    @Autowired
    TransportService transportService;
    @Autowired
    LogInController logInController;

    @RequestMapping("/")
    public String showAll(Model model) {
        model.addAttribute("bookedPlaces", bookedPlaceService.updateAndGetAll());
        model.addAttribute("perms", logInController.perms);

        return "booked-places-all";
    }

    @RequestMapping("/cancel/{id}")
    public String cancelBookedPlace(@PathVariable String id) {
        BookedPlace bookedPlaceToCancel = bookedPlaceService.get(id);

        if (bookedPlaceToCancel.canBeCanceledRoReturn() && !(bookedPlaceToCancel.getFlight() == null)) {
            Transport transportToUpdate = transportService.get(bookedPlaceToCancel.getFlight().getTransport().getId());
            transportToUpdate.unbookPlace();

            Flight flightToUpdate = flightService.get(bookedPlaceToCancel.getFlight().getId());
            flightToUpdate.setTransport(transportToUpdate);

            transportService.update(transportToUpdate);
            flightService.update(flightToUpdate);
            bookedPlaceService.cancel(bookedPlaceToCancel);
        }
        else {
            bookedPlaceService.cancel(bookedPlaceToCancel);
        }

        return "redirect:/ui/v1/booked-places/";
    }

    @RequestMapping("/return/{id}")
    public String returnBookedPlace(@PathVariable String id) {
        BookedPlace bookedPlaceToReturn = bookedPlaceService.get(id);

        if (bookedPlaceToReturn.canBeCanceledRoReturn() && !(bookedPlaceToReturn.getFlight() == null)) {
            Transport transportToUpdate = transportService.get(bookedPlaceToReturn.getFlight().getTransport().getId());
            transportToUpdate.unbookPlace();

            Flight flightToUpdate = flightService.get(bookedPlaceToReturn.getFlight().getId());
            flightToUpdate.setTransport(transportToUpdate);

            transportService.update(transportToUpdate);
            flightService.update(flightToUpdate);
            bookedPlaceService.returnPlace(bookedPlaceToReturn);
        }
        else {
            bookedPlaceService.returnPlace(bookedPlaceToReturn);
        }

        return "redirect:/ui/v1/booked-places/";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addBookedPlace(Model model) {
        model.addAttribute("form", new BookedPlaceForm());
        model.addAttribute("flights", flightService.getFreeFlights());
        model.addAttribute("passengers", passengerService.getAll());

        return "booked-place-add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addBookedPlace(@ModelAttribute("form") BookedPlaceForm form) {
        BookedPlace bookedPlaceToAdd = new BookedPlace();


        bookedPlaceToAdd.fillFromForm(form);
        bookedPlaceToAdd.setFlight(flightService.get(form.getFlight()));
        bookedPlaceToAdd.setPassenger(passengerService.get(form.getPassenger()));

        Transport transportToUpdate = bookedPlaceToAdd.getFlight().getTransport();
        transportToUpdate.bookPlace();
        bookedPlaceToAdd.getFlight().setTransport(transportToUpdate);

        bookedPlaceService.create(bookedPlaceToAdd);
        transportService.update(transportToUpdate);
        flightService.update(bookedPlaceToAdd.getFlight());

        return "redirect:/ui/v1/booked-places/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBookedPlace(Model model, @PathVariable String id) {
        BookedPlace bookedPlaceToEdit = bookedPlaceService.get(id);
        BookedPlaceForm bookedPlaceForm = new BookedPlaceForm();

        bookedPlaceForm.fillFromBookedPlace(bookedPlaceToEdit);
        model.addAttribute("form", bookedPlaceForm);
        model.addAttribute("currentFlight", flightService.get(bookedPlaceForm.getFlight()));
        model.addAttribute("currentPassenger", passengerService.get(bookedPlaceForm.getPassenger()));

        if (!flightService.get(bookedPlaceForm.getFlight()).getFlightStatus().equals(FlightStatus.Waiting)) {
            return "redirect:/ui/v1/booked-places/";
        }

        return "booked-place-edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editBookedPlace(@ModelAttribute("form") BookedPlaceForm form, @PathVariable("id") String id) {
        BookedPlace bookedPlaceToEdit = bookedPlaceService.get(id);

        bookedPlaceToEdit.fillFromForm(form);
        bookedPlaceToEdit.setFlight(flightService.get(form.getFlight()));
        bookedPlaceToEdit.setPassenger(passengerService.get(form.getPassenger()));
        bookedPlaceService.update(bookedPlaceToEdit);

        return "redirect:/ui/v1/booked-places/";
    }

    @RequestMapping("/redirect/flights")
    public String redirectFlights() {
        bookedPlaceService.updateAndGetAll();

        return "redirect:/ui/v1/flights/";
    }

    @RequestMapping("/name/{name}")
    public String allBookedPlacesByName(Model model, @PathVariable String name) {
        Name searchName = Name.parse(name);

        model.addAttribute("bookedPlaces", bookedPlaceService.getAllPlacesByName(searchName));
        model.addAttribute("perms", logInController.perms);

        return "booked-places-all";
    }

    @RequestMapping("/last-name/{lastName}")
    public String allBookedPlacesByLastName(Model model, @PathVariable String lastName) {
        model.addAttribute("bookedPlaces", bookedPlaceService.getAllPlacesByLastName(lastName));
        model.addAttribute("perms", logInController.perms);

        return "booked-places-all";
    }

    @RequestMapping("/interval/{startDay}/{endDay}")
    public String showAllInterval(Model model, @PathVariable String startDay, @PathVariable String endDay) {
        List<BookedPlace> bookedPlaces = bookedPlaceService.getAllByDayOfBooking(startDay, endDay);
        int numberOfFlights = flightService.getNumberOfFlights(bookedPlaces);

        model.addAttribute("bookedPlaces", bookedPlaces);
        model.addAttribute("numberOfFlights", numberOfFlights);
        model.addAttribute("averageNumber", (double) bookedPlaces.size() / (double) numberOfFlights);
        model.addAttribute("perms", logInController.perms);

        return "booked-places-all-with-average";
    }
}
