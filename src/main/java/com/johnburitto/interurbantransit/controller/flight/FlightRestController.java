package com.johnburitto.interurbantransit.controller.flight;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class FlightRestController
 * @version 1.0.0
 * @since 18.06.2022, 17:37
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.Driver;
import com.johnburitto.interurbantransit.model.Flight;
import com.johnburitto.interurbantransit.model.User;
import com.johnburitto.interurbantransit.service.impls.FlightService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/flights")
public class FlightRestController {
    @Autowired
    FlightService service;

    @GetMapping("/")
    @ApiOperation(value = "Get All Flights",
            notes = "Return list of all flights",
            response = Flight.class,
            responseContainer = "List")
    public List<Flight> showAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Flight by id",
            notes = "Return flight with such id",
            response = Flight.class,
            responseContainer = "Flight")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found"),
            @ApiResponse(code = 404, message = "System doesn't have flight with such id")
    })
    public Flight showOne(@PathVariable String id) {
        return service.get(id);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete Flight by id",
            notes = "Delete flight with such id",
            response = Object.class,
            responseContainer = "void")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "System doesn't have flight with such id")
    })
    public void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create Flight",
            notes = "Create flight",
            response = Flight.class,
            responseContainer = "Flight")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created")
    })
    public Flight createOne(@RequestBody Flight flight) {
        return service.create(flight);
    }

    @PutMapping("/edit/{id}")
    @ApiOperation(value = "Edit Flight by id",
            notes = "Edit flight with such id",
            response = Flight.class,
            responseContainer = "Flight")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "System doesn't have flight with such id")
    })
    public Flight updateOne(@RequestBody Flight flight, @PathVariable String id) {
        flight.setId(id);

        return service.update(flight);
    }

    @GetMapping("/get/byStartDate/{date}")
    public List<Flight> getAllByStartDate(@PathVariable String date) {
        return service.getByStartDay(LocalDate.parse(date));
    }

    @GetMapping("/{id}/{flightStatus}/passengers")
    public List<User> showAllPassengersOfFlightByStatus(@PathVariable String id,
                                                        @PathVariable String flightStatus) {
        return service.getAllPassengersOfFlight(id, flightStatus);
    }
}
