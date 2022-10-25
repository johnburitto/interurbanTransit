package com.johnburitto.interurbantransit.service.impls.sql;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class RouteService
 * @version 1.0.0
 * @since 22.08.2022, 15:11
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.sql.BookedPlace;
import com.johnburitto.interurbantransit.model.sql.Route;
import com.johnburitto.interurbantransit.repository.RoutePostgreSQLRepository;
import com.johnburitto.interurbantransit.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RouteService implements IService<Route> {
    @Autowired
    RoutePostgreSQLRepository repository;

    @Override
    public Route create(Route route) {
        route.setCreatedAt(LocalDateTime.now());
        route.setUpdatedAt(LocalDateTime.now());

        return repository.save(route);
    }

    @Override
    public Route get(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Route update(Route route) {
        route.setCreatedAt(get(route.getId()).getCreatedAt());
        route.setUpdatedAt(LocalDateTime.now());

        return repository.save(route);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Route> getAll() {
        return repository.findAll();
    }

    public List<Route> getAllInPage(int size, int pageNumber) {
        return repository.findAll(PageRequest.of(pageNumber, size, Sort.by("id"))).getContent();
    }
}
