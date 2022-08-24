package com.johnburitto.interurbantransit.service.impls;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class FlightService
 * @version 1.0.0
 * @since 22.08.2022, 15:47
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.Flight;
import com.johnburitto.interurbantransit.repository.FlightPostgreSQLRepository;
import com.johnburitto.interurbantransit.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightService implements IService<Flight> {
    @Autowired
    FlightPostgreSQLRepository repository;

    @Override
    public Flight create(Flight flight) {
        flight.setCreatedAt(LocalDateTime.now());
        flight.setUpdatedAt(LocalDateTime.now());

        return repository.save(flight);
    }

    @Override
    public Flight get(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Flight update(Flight flight) {
        flight.setCreatedAt(get(flight.getId()).getCreatedAt());
        flight.setUpdatedAt(LocalDateTime.now());

        return repository.save(flight);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Flight> getAll() {
        return repository.findAll();
    }

    public List<Flight> getByStartDay(LocalDate startDay) {
        return repository.queryFindByStartDay(startDay);
    }
}
