package com.johnburitto.interurbantransit.controller.transport;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class TransportRestController
 * @version 1.0.0
 * @since 13.06.2022, 12:46
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.Transport;
import com.johnburitto.interurbantransit.service.impls.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transports")
public class TransportRestController {
    @Autowired
    TransportService service;

    @GetMapping("/")
    public List<Transport> showAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Transport showOne(@PathVariable String id) {
        return service.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @PostMapping("/create")
    public Transport createOne(@RequestBody Transport transport) {
        return service.create(transport);
    }

    @PutMapping("/edit/{id}")
    public Transport updateOne(@RequestBody Transport transport, @PathVariable String id) {
        transport.setId(id);

        return service.update(transport);
    }
}
