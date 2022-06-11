package com.johnburitto.interurbantransit.controller.transportpassport;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class TransportPassportRestController
 * @version 1.0.0
 * @since 11.06.2022, 13:18
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.TransportPassport;
import com.johnburitto.interurbantransit.service.impls.TransportPassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transport-passports")
public class TransportPassportRestController {
    @Autowired
    TransportPassportService service;

    @GetMapping("/")
    public List<TransportPassport> showAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public TransportPassport showOne(@PathVariable String id) {
        return service.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @PostMapping("/create")
    public TransportPassport createOne(@RequestBody TransportPassport transportPassport) {
        return service.create(transportPassport);
    }

    @PutMapping("/edit/{id}")
    public TransportPassport updateOne(@RequestBody TransportPassport transportPassport, @PathVariable String id) {
        transportPassport.setTransportNumber(id);

        return service.update(transportPassport);
    }
}
