package com.johnburitto.interurbantransit.service.impls;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class DriverService
 * @version 1.0.0
 * @since 07.06.2022, 14:07
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.Driver;
import com.johnburitto.interurbantransit.repository.DriverMongoRepository;
import com.johnburitto.interurbantransit.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService implements IService<Driver> {
    @Autowired
    DriverMongoRepository repository;

    @Override
    public Driver create(Driver driver) {
        return null;
    }

    @Override
    public Driver get(String id) {
        return null;
    }

    @Override
    public Driver update(Driver driver) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<Driver> getAll() {
        return null;
    }
}
