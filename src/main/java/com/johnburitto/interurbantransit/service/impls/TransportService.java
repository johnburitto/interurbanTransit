package com.johnburitto.interurbantransit.service.impls;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class TransportService
 * @version 1.0.0
 * @since 07.06.2022, 14:08
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.Transport;
import com.johnburitto.interurbantransit.repository.TransportMongoRepository;
import com.johnburitto.interurbantransit.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportService implements IService<Transport> {
    @Autowired
    TransportMongoRepository repository;

    @Override
    public Transport create(Transport transport) {
        return null;
    }

    @Override
    public Transport get(String id) {
        return null;
    }

    @Override
    public Transport update(Transport transport) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<Transport> getAll() {
        return null;
    }
}