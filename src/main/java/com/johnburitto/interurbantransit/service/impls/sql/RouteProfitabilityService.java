package com.johnburitto.interurbantransit.service.impls.sql;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class RouteProfitabilityService
 * @version 1.0.0
 * @since 22.08.2022, 16:12
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.sql.RouteProfitability;
import com.johnburitto.interurbantransit.repository.RouteProfitabilityPostgreSQLRepository;
import com.johnburitto.interurbantransit.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RouteProfitabilityService implements IService<RouteProfitability> {
    @Autowired
    RouteProfitabilityPostgreSQLRepository repository;

    @Override
    public RouteProfitability create(RouteProfitability routeProfitability) {
        routeProfitability.setCreatedAt(LocalDateTime.now());
        routeProfitability.setUpdatedAt(LocalDateTime.now());

        return repository.save(routeProfitability);
    }

    @Override
    public RouteProfitability get(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public RouteProfitability update(RouteProfitability routeProfitability) {
        routeProfitability.setCreatedAt(get(routeProfitability.getId()).getCreatedAt());
        routeProfitability.setUpdatedAt(LocalDateTime.now());

        return repository.save(routeProfitability);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<RouteProfitability> getAll() {
        return repository.findAll();
    }
}
