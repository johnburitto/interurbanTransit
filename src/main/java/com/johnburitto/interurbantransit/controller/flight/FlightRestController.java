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

import com.johnburitto.interurbantransit.model.Flight;
import com.johnburitto.interurbantransit.service.impls.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flights")
public class FlightRestController {
    @Autowired
    FlightService service;

    @GetMapping("/")
    public List<Flight> showAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Flight showOne(@PathVariable String id) {
        return service.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @PostMapping("/create")
    public Flight createOne(@RequestBody Flight flight) {
        return service.create(flight);
    }

    @PutMapping("/edit/{id}")
    public Flight updateOne(@RequestBody Flight flight, @PathVariable String id) {
        flight.setId(id);

        return service.update(flight);
    }
}
