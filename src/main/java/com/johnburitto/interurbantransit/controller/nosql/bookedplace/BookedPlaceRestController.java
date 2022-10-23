package com.johnburitto.interurbantransit.controller.nosql.bookedplace;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class BookedPlaceRestController
 * @version 1.0.0
 * @since 21.06.2022, 15:36
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.nosql.BookedPlace;
import com.johnburitto.interurbantransit.service.impls.nosql.BookedPlaceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booked-places")
public class BookedPlaceRestController {
    @Autowired
    BookedPlaceService bookedPlaceService;

    @GetMapping("/")
    @ApiOperation(value = "Get All Booked pales",
            notes = "Return list of all booked places",
            response = BookedPlace.class,
            responseContainer = "List")
    public List<BookedPlace> showAll() {
        return bookedPlaceService.getAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Booked place by id",
            notes = "Return booked place with such id",
            response = BookedPlace.class,
            responseContainer = "BookedPlace")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found"),
            @ApiResponse(code = 404, message = "System doesn't have booked place with such id")
    })
    public BookedPlace showOne(@PathVariable String id) {
        return bookedPlaceService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete Booked place by id",
            notes = "Delete booked place with such id",
            response = Object.class,
            responseContainer = "void")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "System doesn't have booked place with such id")
    })
    public void deleteOne(@PathVariable String id) {
        bookedPlaceService.delete(id);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create Booked place",
            notes = "Create booked place",
            response = BookedPlace.class,
            responseContainer = "BookedPlace")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created")
    })
    public BookedPlace createOne(@RequestBody BookedPlace bookedPlace) {
        return bookedPlaceService.create(bookedPlace);
    }

    @PutMapping("/edit/{id}")
    @ApiOperation(value = "Edit Booked place by id",
            notes = "Edit booked place with such id",
            response = BookedPlace.class,
            responseContainer = "BookedPlace")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "System doesn't have booked place with such id")
    })
    public BookedPlace updateOne(@RequestBody BookedPlace bookedPlace, @PathVariable String id) {
        bookedPlace.setId(id);

        return bookedPlaceService.update(bookedPlace);
    }
}
