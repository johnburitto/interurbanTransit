package com.johnburitto.interurbantransit.service.impls;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class PassengerService
 * @version 1.0.0
 * @since 07.06.2022, 14:11
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.Passenger;
import com.johnburitto.interurbantransit.repository.PassengerMongoRepository;
import com.johnburitto.interurbantransit.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PassengerService implements IService<Passenger> {
    @Autowired
    PassengerMongoRepository repository;

    @Override
    public Passenger create(Passenger passenger) {
        passenger.setId(generateNextIndex());
        passenger.setCreatedAt(LocalDateTime.now());
        passenger.setUpdatedAt(LocalDateTime.now());

        return repository.save(passenger);
    }

    @Override
    public Passenger get(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Passenger update(Passenger passenger) {
        passenger.setCreatedAt(get(passenger.getId()).getCreatedAt());
        passenger.setUpdatedAt(LocalDateTime.now());

        return repository.save(passenger);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Passenger> getAll() {
        return repository.findAll();
    }

    private String generateNextIndex() {
        return String.valueOf(repository.findAll().size() + 1);
    }
}
