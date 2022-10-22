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
import com.johnburitto.interurbantransit.service.impls.TransportService;
import com.johnburitto.interurbantransit.service.impls.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/ui/v1/booked-places")
public class BookedPlaceUIController {
    @Autowired
    BookedPlaceService bookedPlaceService;
    @Autowired
    FlightService flightService;
    @Autowired
    TransportService transportService;
    @Autowired
    UserService userService;
    @Autowired
    LogInController logInController;

    @RequestMapping("/")
    public String showAll(Model model) throws IOException {
        if (logInController.perms.getType() == UserType.Guest) {
            model.addAttribute("bookedPlaces", bookedPlaceService.getAllPlacesByName(logInController.contactInf.getName()));
            model.addAttribute("perms", logInController.perms);
            model.addAttribute("filters", FiltersManager.readFromFile("bookedPlaceFilters.txt"));

            return "booked-places-all";
        }

        model.addAttribute("bookedPlaces", bookedPlaceService.updateAndGetAll());
        model.addAttribute("perms", logInController.perms);
        model.addAttribute("filters", FiltersManager.readFromFile("bookedPlaceFilters.txt"));

        return "booked-places-all";
    }

    @RequestMapping("/book/{id}")
    public String bookPlace(@PathVariable String id) {
        BookedPlace newBookedPlace = new BookedPlace("", flightService.get(id), userService.get(logInController.currentUserId), LocalDate.now());

        bookedPlaceService.create(newBookedPlace);

        return "redirect:/";
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

        return "redirect:/ui/v1/booked-places/paging/5&0";
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

        return "redirect:/ui/v1/booked-places/paging/5&0";
    }

    @RequestMapping("/redirect/flights")
    public String redirectFlights() {
        bookedPlaceService.updateAndGetAll();

        return "redirect:/ui/v1/flights/";
    }

    @RequestMapping("/name/{name}")
    public String allBookedPlacesByName(Model model, @PathVariable String name) throws IOException {
        Name searchName = Name.parse(name);

        model.addAttribute("bookedPlaces", bookedPlaceService.getAllPlacesByName(searchName));
        model.addAttribute("perms", logInController.perms);
        model.addAttribute("filters", FiltersManager.readFromFile("bookedPlaceFilters.txt"));

        return "booked-places-all";
    }

    @RequestMapping("/last-name/{lastName}")
    public String allBookedPlacesByLastName(Model model, @PathVariable String lastName) throws IOException {
        model.addAttribute("bookedPlaces", bookedPlaceService.getAllPlacesByLastName(lastName));
        model.addAttribute("perms", logInController.perms);
        model.addAttribute("filters", FiltersManager.readFromFile("bookedPlaceFilters.txt"));

        return "booked-places-all";
    }

    @RequestMapping("/interval/{startDay}/{endDay}")
    public String showAllInterval(Model model, @PathVariable String startDay, @PathVariable String endDay) throws IOException {
        List<BookedPlace> bookedPlaces = bookedPlaceService.getAllByDayOfBooking(startDay, endDay);
        int numberOfFlights = flightService.getNumberOfFlights(bookedPlaces);

        model.addAttribute("bookedPlaces", bookedPlaces);
        model.addAttribute("numberOfFlights", numberOfFlights);
        model.addAttribute("averageNumber", (double) bookedPlaces.size() / (double) numberOfFlights);
        model.addAttribute("perms", logInController.perms);
        model.addAttribute("filters", FiltersManager.readFromFile("bookedPlaceFilters.txt"));

        return "booked-places-all-with-average";
    }

    @RequestMapping("/filters/{data}")
    public String saveFilters(@PathVariable String data) throws FileNotFoundException {
        FiltersManager.parseAndSaveToFile(data, "bookedPlaceFilters.txt");

        return "redirect:/ui/v1/booked-places/paging/5&0";
    }

    @RequestMapping("/paging/{size}&{pageNumber}")
    public String getUserInPaging(@PathVariable int size, @PathVariable int pageNumber, Model model) throws IOException {
        model.addAttribute("bookedPlaces", bookedPlaceService.getAllInPage(size, pageNumber));
        model.addAttribute("perms", logInController.perms);
        model.addAttribute("filters", FiltersManager.readFromFile("bookedPlaceFilters.txt"));

        return "booked-places-paging";
    }
}
