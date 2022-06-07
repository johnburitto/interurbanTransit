package com.johnburitto.interurbantransit.service.impls;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class FlightService
 * @version 1.0.0
 * @since 07.06.2022, 14:13
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.Flight;
import com.johnburitto.interurbantransit.repository.FlightMongoRepository;
import com.johnburitto.interurbantransit.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService implements IService<Flight> {
    @Autowired
    FlightMongoRepository repository;

    @Override
    public Flight create(Flight flight) {
        return null;
    }

    @Override
    public Flight get(String id) {
        return null;
    }

    @Override
    public Flight update(Flight flight) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<Flight> getAll() {
        return null;
    }
}
