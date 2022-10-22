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

import com.johnburitto.interurbantransit.model.Driver;
import com.johnburitto.interurbantransit.model.Transport;
import com.johnburitto.interurbantransit.service.impls.TransportService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transports")
public class TransportRestController {
    @Autowired
    TransportService service;

    @GetMapping("/")
    @ApiOperation(value = "Get All Transports",
            notes = "Return list of all transports",
            response = Driver.class,
            responseContainer = "List")
    public List<Transport> showAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Transport by id",
            notes = "Return transport with such id",
            response = Transport.class,
            responseContainer = "Transport")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found"),
            @ApiResponse(code = 404, message = "System doesn't have transport with such id")
    })
    public Transport showOne(@PathVariable String id) {
        return service.get(id);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete Transport by id",
            notes = "Delete transport with such id",
            response = Object.class,
            responseContainer = "void")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "System doesn't have transport with such id")
    })
    public void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create Transport",
            notes = "Create transport",
            response = Transport.class,
            responseContainer = "Transport")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created")
    })
    public Transport createOne(@RequestBody Transport transport) {
        return service.create(transport);
    }

    @PutMapping("/edit/{id}")
    @ApiOperation(value = "Edit Transport by id",
            notes = "Edit transport with such id",
            response = Transport.class,
            responseContainer = "Transport")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "System doesn't have transport with such id")
    })
    public Transport updateOne(@RequestBody Transport transport, @PathVariable String id) {
        transport.setId(id);

        return service.update(transport);
    }
}
