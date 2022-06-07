package com.johnburitto.interurbantransit.service.impls;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class TransportPassportService
 * @version 1.0.0
 * @since 07.06.2022, 14:12
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.TransportPassport;
import com.johnburitto.interurbantransit.repository.TransportPassportMongoRepository;
import com.johnburitto.interurbantransit.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportPassportService implements IService<TransportPassport> {
    @Autowired
    TransportPassportMongoRepository repository;

    @Override
    public TransportPassport create(TransportPassport transportPassport) {
        return null;
    }

    @Override
    public TransportPassport get(String id) {
        return null;
    }

    @Override
    public TransportPassport update(TransportPassport transportPassport) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<TransportPassport> getAll() {
        return null;
    }
}
