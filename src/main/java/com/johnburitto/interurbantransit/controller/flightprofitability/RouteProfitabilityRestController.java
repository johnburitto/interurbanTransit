package com.johnburitto.interurbantransit.controller.flightprofitability;

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

import com.johnburitto.interurbantransit.model.Route;
import com.johnburitto.interurbantransit.model.RouteProfitability;
import com.johnburitto.interurbantransit.service.impls.RouteProfitabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/route-profitability")
public class RouteProfitabilityRestController {
    @Autowired
    RouteProfitabilityService service;

    @GetMapping("/")
    public List<RouteProfitability> showAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public RouteProfitability showOne(@PathVariable String id) {
        return service.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @PostMapping("/create")
    public RouteProfitability createOne(@RequestBody RouteProfitability routeProfitability) {
        return service.create(routeProfitability);
    }

    @PutMapping("/edit/{id}")
    public RouteProfitability updateOne(@RequestBody RouteProfitability routeProfitability, @PathVariable String id) {
        routeProfitability.setId(id);

        return service.update(routeProfitability);
    }
}
