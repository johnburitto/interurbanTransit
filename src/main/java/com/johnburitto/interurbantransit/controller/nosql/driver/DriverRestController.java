package com.johnburitto.interurbantransit.controller.nosql.driver;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class DriverRestController
 * @version 1.0.0
 * @since 10.06.2022, 16:33
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.nosql.Driver;
import com.johnburitto.interurbantransit.service.impls.nosql.DriverService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drivers")
public class DriverRestController {
    @Autowired
    DriverService driverService;

    @GetMapping("/")
    @ApiOperation(value = "Get All Drivers",
            notes = "Return list of all drivers",
            response = Driver.class,
            responseContainer = "List")
    public List<Driver> showAll() {
        return driverService.getAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Driver by id",
            notes = "Return driver with such id",
            response = Driver.class,
            responseContainer = "Driver")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found"),
            @ApiResponse(code = 404, message = "System doesn't have driver with such id")
    })
    public Driver showOne(@PathVariable String id) {
        return driverService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete Driver by id",
            notes = "Delete driver with such id",
            response = Object.class,
            responseContainer = "void")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "System doesn't have driver with such id")
    })
    public void deleteOne(@PathVariable String id) {
        driverService.delete(id);
    }


    @PostMapping("/create")
    @ApiOperation(value = "Create Driver",
            notes = "Create driver",
            response = Driver.class,
            responseContainer = "Driver")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created")
    })
    public Driver createOne(@RequestBody Driver driver) {
        return driverService.create(driver);
    }

    @PutMapping("/edit/{id}")
    @ApiOperation(value = "Edit Driver by id",
            notes = "Edit driver with such id",
            response = Driver.class,
            responseContainer = "Driver")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "System doesn't have driver with such id")
    })
    public Driver updateOne(@RequestBody Driver driver, @PathVariable String id) {
        driver.setId(id);

        return driverService.update(driver);
    }
}
