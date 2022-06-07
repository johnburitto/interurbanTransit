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

import java.util.List;

@Service
public class PassengerService implements IService<Passenger> {
    @Autowired
    PassengerMongoRepository repository;

    @Override
    public Passenger create(Passenger passenger) {
        return null;
    }

    @Override
    public Passenger get(String id) {
        return null;
    }

    @Override
    public Passenger update(Passenger passenger) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<Passenger> getAll() {
        return null;
    }
}