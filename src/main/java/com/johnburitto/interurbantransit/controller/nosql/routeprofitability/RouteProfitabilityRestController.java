package com.johnburitto.interurbantransit.controller.nosql.routeprofitability;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class RouteProfitabilityRestController
 * @version 1.0.0
 * @since 21.06.2022, 22:49
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.nosql.RouteProfitability;
import com.johnburitto.interurbantransit.service.impls.nosql.RouteProfitabilityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/route-profitability")
public class RouteProfitabilityRestController {
    @Autowired
    RouteProfitabilityService service;

    @GetMapping("/")
    @ApiOperation(value = "Get All Route profitabilities",
            notes = "Return list of all route profitabilities",
            response = RouteProfitability.class,
            responseContainer = "RouteProfitability")
    public List<RouteProfitability> showAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Route profitability by id",
            notes = "Return route profitability with such id",
            response = RouteProfitability.class,
            responseContainer = "RouteProfitability")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found"),
            @ApiResponse(code = 404, message = "System doesn't have route profitability with such id")
    })
    public RouteProfitability showOne(@PathVariable String id) {
        return service.get(id);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete Route profitability by id",
            notes = "Delete route profitability with such id",
            response = RouteProfitability.class,
            responseContainer = "RouteProfitability")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "System doesn't have route profitability with such id")
    })
    public void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create Route profitability",
            notes = "Create route profitability",
            response = RouteProfitability.class,
            responseContainer = "RouteProfitability")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created")
    })
    public RouteProfitability createOne(@RequestBody RouteProfitability routeProfitability) {
        return service.create(routeProfitability);
    }

    @PutMapping("/edit/{id}")
    @ApiOperation(value = "Edit Route profitability by id",
            notes = "Edit route profitability with such id",
            response = RouteProfitability.class,
            responseContainer = "RouteProfitability")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "System doesn't have route profitability with such id")
    })
    public RouteProfitability updateOne(@RequestBody RouteProfitability routeProfitability, @PathVariable String id) {
        routeProfitability.setId(id);

        return service.update(routeProfitability);
    }
}
