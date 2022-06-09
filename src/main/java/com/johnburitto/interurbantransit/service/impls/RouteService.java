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

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RouteService implements IService<Route> {
    @Autowired
    RouteMongoRepository repository;

    @Override
    public Route create(Route route) {
        route.setId(generateNextIndex());
        route.setCreatedAt(LocalDateTime.now());
        route.setUpdatedAt(LocalDateTime.now());

        return repository.save(route);
    }

    @Override
    public Route get(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Route update(Route route) {
        route.setCreatedAt(get(route.getId()).getCreatedAt());
        route.setUpdatedAt(LocalDateTime.now());

        return repository.save(route);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Route> getAll() {
        return repository.findAll();
    }

    private String generateNextIndex() {
        return String.valueOf(repository.findAll().size() + 1);
    }
}
