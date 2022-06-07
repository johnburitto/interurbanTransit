package com.johnburitto.interurbantransit.service.impls;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class RouteService
 * @version 1.0.0
 * @since 07.06.2022, 14:09
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.Route;
import com.johnburitto.interurbantransit.repository.RouteMongoRepository;
import com.johnburitto.interurbantransit.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService implements IService<Route> {
    @Autowired
    RouteMongoRepository repository;

    @Override
    public Route create(Route route) {
        return null;
    }

    @Override
    public Route get(String id) {
        return null;
    }

    @Override
    public Route update(Route route) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<Route> getAll() {
        return null;
    }
}
