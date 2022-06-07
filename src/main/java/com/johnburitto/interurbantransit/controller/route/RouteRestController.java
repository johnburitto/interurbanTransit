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

import com.johnburitto.interurbantransit.model.Route;
import com.johnburitto.interurbantransit.service.impls.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/routes")
public class RouteRestController {
    @Autowired
    RouteService service;

    @GetMapping("/")
    public List<Route> showAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Route showOne(@PathVariable String id) {
        return service.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @PostMapping("/create")
    public Route createOne(@RequestBody Route route) {
        return service.create(route);
    }

    @PutMapping("/edit/{id}")
    public Route updateOne(@RequestBody Route route, @PathVariable String id) {
        route.setId(id);
        return service.update(route);
    }
}
