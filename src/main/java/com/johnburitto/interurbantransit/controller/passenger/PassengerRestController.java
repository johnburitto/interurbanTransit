package com.johnburitto.interurbantransit.controller.passenger;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class PassengerRestController
 * @version 1.0.0
 * @since 15.06.2022, 14:54
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.Passenger;
import com.johnburitto.interurbantransit.service.impls.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/passengers")
public class PassengerRestController {
    @Autowired
    PassengerService service;

    @GetMapping("/")
    public List<Passenger> showAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Passenger showOne(@PathVariable String id) {
        return service.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @PostMapping("/create")
    public Passenger createOne(@RequestBody Passenger passenger) {
        return service.create(passenger);
    }

    @PutMapping("/edit/{id}")
    public Passenger updateOne(@RequestBody Passenger passenger, @PathVariable String id) {
        passenger.setId(id);

        return service.update(passenger);
    }
}
