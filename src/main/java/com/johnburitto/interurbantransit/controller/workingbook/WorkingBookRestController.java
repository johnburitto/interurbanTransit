package com.johnburitto.interurbantransit.controller.workingbook;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class WorkingBookRestController
 * @version 1.0.0
 * @since 08.06.2022, 14:25
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.WorkingBook;
import com.johnburitto.interurbantransit.service.impls.WorkingBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/working-books")
public class WorkingBookRestController {
    @Autowired
    WorkingBookService service;

    @GetMapping("/")
    public List<WorkingBook> showAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public WorkingBook showOne(@PathVariable String id) {
        return service.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @PostMapping("/create")
    public WorkingBook createOne(@RequestBody WorkingBook workingBook) {
        return service.create(workingBook);
    }

    @PutMapping("/edit/{id}")
    public WorkingBook updateOne(@RequestBody WorkingBook workingBook, @PathVariable String id) {
        workingBook.setNumberOfWorkingBook(id);

        return service.update(workingBook);
    }
}
