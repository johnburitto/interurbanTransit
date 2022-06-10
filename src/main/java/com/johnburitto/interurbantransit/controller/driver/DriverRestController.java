package com.johnburitto.interurbantransit.controller.driver;

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

import com.johnburitto.interurbantransit.model.Driver;
import com.johnburitto.interurbantransit.service.impls.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drivers")
public class DriverRestController {
    @Autowired
    DriverService service;

    @GetMapping("/")
    public List<Driver> showAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Driver showOne(@PathVariable String id) {
        return service.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @PostMapping("/create")
    public Driver createOne(@RequestBody Driver driver) {
        return service.create(driver);
    }

    @PutMapping("/edit/{id}")
    public Driver updateOne(@RequestBody Driver driver, @PathVariable String id) {
        driver.setId(id);

        return service.update(driver);
    }
}
