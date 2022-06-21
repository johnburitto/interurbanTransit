package com.johnburitto.interurbantransit.controller.bookedplace;

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

import com.johnburitto.interurbantransit.model.BookedPlace;
import com.johnburitto.interurbantransit.service.impls.BookedPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booked-places")
public class BookedPlaceRestController {
    @Autowired
    BookedPlaceService bookedPlaceService;

    @GetMapping("/")
    public List<BookedPlace> showAll() {
        return bookedPlaceService.getAll();
    }

    @GetMapping("/{id}")
    public BookedPlace showOne(@PathVariable String id) {
        return bookedPlaceService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOne(@PathVariable String id) {
        bookedPlaceService.delete(id);
    }

    @PostMapping("/create")
    public BookedPlace createOne(@RequestBody BookedPlace bookedPlace) {
        return bookedPlaceService.create(bookedPlace);
    }

    @PutMapping("/edit/{id}")
    public BookedPlace updateOne(@RequestBody BookedPlace bookedPlace, @PathVariable String id) {
        bookedPlace.setId(id);

        return bookedPlaceService.update(bookedPlace);
    }
}
