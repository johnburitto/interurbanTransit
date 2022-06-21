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

import com.johnburitto.interurbantransit.form.BookedPlaceForm;
import com.johnburitto.interurbantransit.model.BookedPlace;
import com.johnburitto.interurbantransit.model.FlightStatus;
import com.johnburitto.interurbantransit.service.impls.BookedPlaceService;
import com.johnburitto.interurbantransit.service.impls.FlightService;
import com.johnburitto.interurbantransit.service.impls.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ui/v1/booked-places")
public class BookedPlaceUIController {
    @Autowired
    BookedPlaceService bookedPlaceService;
    @Autowired
    FlightService flightService;
    @Autowired
    PassengerService passengerService;

    @RequestMapping("/")
    public String showAll(Model model) {
        model.addAttribute("bookedPlaces", bookedPlaceService.getAll());

        return "booked-places-all";
    }

    @RequestMapping("/delete/{id}")
    public String deleteBookedPlace(@PathVariable String id) {
        bookedPlaceService.delete(id);

        return "redirect:/ui/v1/booked-places/";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addBookedPlace(Model model) {
        model.addAttribute("form", new BookedPlaceForm());
        model.addAttribute("flights", flightService.findByStatus(FlightStatus.Waiting));
        model.addAttribute("passengers", passengerService.getAll());

        return "booked-place-add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addBookedPlace(@ModelAttribute("form") BookedPlaceForm form) {
        BookedPlace bookedPlaceToAdd = new BookedPlace();

        bookedPlaceToAdd.fillFromForm(form);
        bookedPlaceToAdd.setFlight(flightService.get(form.getFlight()));
        bookedPlaceToAdd.setPassenger(passengerService.get(form.getPassenger()));
        bookedPlaceService.create(bookedPlaceToAdd);

        return "redirect:/ui/v1/booked-places/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBookedPlace(Model model, @PathVariable String id) {
        BookedPlace bookedPlaceToEdit = bookedPlaceService.get(id);
        BookedPlaceForm bookedPlaceForm = new BookedPlaceForm();

        bookedPlaceForm.fillFromBookedPlace(bookedPlaceToEdit);
        model.addAttribute("form", bookedPlaceForm);
        model.addAttribute("flights", flightService.findByStatus(FlightStatus.Waiting));
        model.addAttribute("passengers", passengerService.getAll());
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
}
