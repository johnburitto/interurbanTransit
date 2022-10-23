package com.johnburitto.interurbantransit.controller.nosql.transportpassport;

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

import com.johnburitto.interurbantransit.model.nosql.TransportPassport;
import com.johnburitto.interurbantransit.service.impls.nosql.TransportPassportService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transport-passports")
public class TransportPassportRestController {
    @Autowired
    TransportPassportService service;

    @GetMapping("/")
    @ApiOperation(value = "Get All Transport passports",
            notes = "Return list of all transport passports",
            response = TransportPassport.class,
            responseContainer = "List")
    public List<TransportPassport> showAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Transport passport by id",
            notes = "Return transport passport with such id",
            response = TransportPassport.class,
            responseContainer = "TransportPassport")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found"),
            @ApiResponse(code = 404, message = "System doesn't have transport passport with such id")
    })
    public TransportPassport showOne(@PathVariable String id) {
        return service.get(id);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete Transport passport by id",
            notes = "Delete transport passport with such id",
            response = Object.class,
            responseContainer = "void")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "System doesn't have transport passport with such id")
    })
    public void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create Transport passport",
            notes = "Create transport passport",
            response = TransportPassport.class,
            responseContainer = "TransportPassport")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created")
    })
    public TransportPassport createOne(@RequestBody TransportPassport transportPassport) {
        return service.create(transportPassport);
    }

    @PutMapping("/edit/{id}")
    @ApiOperation(value = "Edit Transport passport by id",
            notes = "Edit transport passport with such id",
            response = TransportPassport.class,
            responseContainer = "TransportPassport")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "System doesn't have transport passport with such id")
    })
    public TransportPassport updateOne(@RequestBody TransportPassport transportPassport, @PathVariable String id) {
        transportPassport.setTransportNumber(id);

        return service.update(transportPassport);
    }
}
