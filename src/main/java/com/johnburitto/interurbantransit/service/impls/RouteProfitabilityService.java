package com.johnburitto.interurbantransit.service.impls;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class RouteProfitabilityService
 * @version 1.0.0
 * @since 21.06.2022, 22:43
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.RouteProfitability;
import com.johnburitto.interurbantransit.repository.RouteProfitabilityMongoRepository;
import com.johnburitto.interurbantransit.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RouteProfitabilityService implements IService<RouteProfitability> {
    @Autowired
    RouteProfitabilityMongoRepository repository;

    @Override
    public RouteProfitability create(RouteProfitability routeProfitability) {
        routeProfitability.setId(generateNextIndex());
        routeProfitability.setCreatedAt(LocalDateTime.now());
        routeProfitability.setUpdatedAt(LocalDateTime.now());

        return repository.save(routeProfitability);
    }

    private String generateNextIndex() {
        List<RouteProfitability> data = repository.findAll();

        try {
            return String.valueOf(Integer.parseInt(data.get(data.size() - 1).getId()) + 1);
        }
        catch (Exception e) {
            return "0";
        }
    }

    @Override
    public RouteProfitability get(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public RouteProfitability update(RouteProfitability routeProfitability) {
        routeProfitability.setCreatedAt(get(routeProfitability.getId()).getCreatedAt());
        routeProfitability.setUpdatedAt(LocalDateTime.now());

        return repository.save(routeProfitability);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<RouteProfitability> getAll() {
        return repository.findAll();
    }
}
