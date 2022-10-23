package com.johnburitto.interurbantransit.service.impls.nosql;

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

import com.johnburitto.interurbantransit.exceptions.ApiRequestException;
import com.johnburitto.interurbantransit.model.nosql.Route;
import com.johnburitto.interurbantransit.repository.RouteMongoRepository;
import com.johnburitto.interurbantransit.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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

    private String generateNextIndex() {
        List<Route> data = repository.findAll();

        try {
            return String.valueOf(Integer.parseInt(data.get(data.size() - 1).getId()) + 1);
        }
        catch (Exception e) {
            return "0";
        }
    }

    @Override
    public Route get(String id) {
        return repository.findById(id).orElseThrow( () -> new ApiRequestException("NotFound!", HttpStatus.NOT_FOUND));
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

    public List<Route> getAllFreeRoutes(List<Route> uncompletedRoutes) {
        List<Route> freeRoutes = repository.findAll();

        freeRoutes.removeAll(uncompletedRoutes);

        return freeRoutes;
    }

    public List<Route> getAllInPage(int size, int pageNumber) {
        return repository.findAll(PageRequest.of(pageNumber, size, Sort.by("id"))).getContent();
    }
}
