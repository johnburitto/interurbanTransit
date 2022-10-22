package com.johnburitto.interurbantransit.controller.bookedplace;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class BookedPlaceUIController
 * @version 1.0.0
 * @since 24.08.2022, 16:23
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.controller.LogInController;
import com.johnburitto.interurbantransit.model.*;
import com.johnburitto.interurbantransit.service.impls.BookedPlaceService;
import com.johnburitto.interurbantransit.service.impls.FlightService;
import com.johnburitto.interurbantransit.service.impls.TransportService;
import com.johnburitto.interurbantransit.service.impls.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/sql/ui/v1/booked-places")
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
            model.addAttribute("bookedPlaces", bookedPlaceService.getByPassenger(logInController.user.getId()));
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
    public String bookPlace(@PathVariable Integer id) {
        BookedPlace newBookedPlace = new BookedPlace(flightService.get(id), userService.get(logInController.user.getId()), LocalDate.now());

        bookedPlaceService.create(newBookedPlace);

        return "redirect:/";
    }

    @RequestMapping("/cancel/{id}")
    public String cancelBookedPlace(@PathVariable Integer id) {
        BookedPlace bookedPlaceToCancel = bookedPlaceService.get(id);

        if (bookedPlaceToCancel.canBeCanceledRoReturn() && !(bookedPlaceToCancel.getFlight() == null)) {
            Transport transportToUpdate = transportService.get(flightService.get(bookedPlaceToCancel.getFlight()).getTransport());
            transportToUpdate.unbookPlace();

            transportService.update(transportToUpdate);
            bookedPlaceService.cancel(bookedPlaceToCancel);
        }
        else {
            bookedPlaceService.cancel(bookedPlaceToCancel);
        }

        return "redirect:/sql/ui/v1/booked-places/";
    }

    @RequestMapping("/return/{id}")
    public String returnBookedPlace(@PathVariable Integer id) {
        BookedPlace bookedPlaceToReturn = bookedPlaceService.get(id);

        if (bookedPlaceToReturn.canBeCanceledRoReturn() && !(bookedPlaceToReturn.getFlight() == null)) {
            Transport transportToUpdate = transportService.get(flightService.get(bookedPlaceToReturn.getFlight()).getTransport());
            transportToUpdate.unbookPlace();

            transportService.update(transportToUpdate);
            bookedPlaceService.returnPlace(bookedPlaceToReturn);
        }
        else {
            bookedPlaceService.returnPlace(bookedPlaceToReturn);
        }

        return "redirect:/sql/ui/v1/booked-places/";
    }

    @RequestMapping("/name/{id}")
    public String allBookedPlacesByName(Model model, @PathVariable Integer id) throws IOException {
        User user = userService.get(id);

        model.addAttribute("bookedPlaces",
                bookedPlaceService.getAllPlacesByFullName(user.getFirstName(), user.getMiddleName(), user.getLastName()));
        model.addAttribute("perms", logInController.perms);
        model.addAttribute("filters", FiltersManager.readFromFile("bookedPlaceFilters.txt"));

        return "booked-places-all";
    }

    @RequestMapping("/last-name/{id}")
    public String allBookedPlacesByLastName(Model model, @PathVariable Integer id) throws IOException {
        User user = userService.get(id);

        model.addAttribute("bookedPlaces", bookedPlaceService.getAllPlacesByLastName(user.getLastName()));
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

        return "redirect:/sql/ui/v1/booked-places/";
    }

    @RequestMapping("/redirect/flights")
    public String redirectFlights() {
        bookedPlaceService.updateAndGetAll();

        return "redirect:/sql/tables";
    }
}
