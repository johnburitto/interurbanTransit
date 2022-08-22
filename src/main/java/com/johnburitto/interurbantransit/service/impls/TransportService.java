package com.johnburitto.interurbantransit.service.impls;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class TransportService
 * @version 1.0.0
 * @since 22.08.2022, 15:22
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.Transport;
import com.johnburitto.interurbantransit.repository.TransportPostgreSQLRepository;
import com.johnburitto.interurbantransit.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransportService implements IService<Transport> {
    @Autowired
    TransportPostgreSQLRepository repository;

    @Override
    public Transport create(Transport transport) {
        transport.setCreatedAt(LocalDateTime.now());
        transport.setUpdatedAt(LocalDateTime.now());

        return repository.save(transport);
    }

    @Override
    public Transport get(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Transport update(Transport transport) {
        transport.setCreatedAt(get(transport.getId()).getCreatedAt());
        transport.setUpdatedAt(LocalDateTime.now());

        return repository.save(transport);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Transport> getAll() {
        return repository.findAll();
    }
}
