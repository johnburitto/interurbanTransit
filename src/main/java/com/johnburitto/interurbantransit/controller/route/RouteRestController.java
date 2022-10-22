package com.johnburitto.interurbantransit.controller.route;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class RouteRestController
 * @version 1.0.0
 * @since 07.06.2022, 17:07
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.Driver;
import com.johnburitto.interurbantransit.model.Route;
import com.johnburitto.interurbantransit.service.impls.RouteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/routes")
public class RouteRestController {
    @Autowired
    RouteService service;

    @GetMapping("/")
    @ApiOperation(value = "Get All Routes",
            notes = "Return list of all routes",
            response = Route.class,
            responseContainer = "List")
    public List<Route> showAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Route by id",
            notes = "Return route with such id",
            response = Route.class,
            responseContainer = "Route")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found"),
            @ApiResponse(code = 404, message = "System doesn't have route with such id")
    })
    public Route showOne(@PathVariable String id) {
        return service.get(id);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete Route by id",
            notes = "Delete route with such id",
            response = Object.class,
            responseContainer = "void")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "System doesn't have route with such id")
    })
    public void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create Route",
            notes = "Create route",
            response = Route.class,
            responseContainer = "Route")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created")
    })
    public Route createOne(@RequestBody Route route) {
        return service.create(route);
    }

    @PutMapping("/edit/{id}")
    @ApiOperation(value = "Edit Route by id",
            notes = "Edit route with such id",
            response = Route.class,
            responseContainer = "Route")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "System doesn't have route with such id")
    })
    public Route updateOne(@RequestBody Route route, @PathVariable String id) {
        route.setId(id);

        return service.update(route);
    }
}
