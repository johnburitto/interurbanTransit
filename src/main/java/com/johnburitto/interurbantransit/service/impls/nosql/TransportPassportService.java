package com.johnburitto.interurbantransit.service.impls.nosql;

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

import com.johnburitto.interurbantransit.exceptions.ApiRequestException;
import com.johnburitto.interurbantransit.model.nosql.TransportPassport;
import com.johnburitto.interurbantransit.repository.TransportPassportMongoRepository;
import com.johnburitto.interurbantransit.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class TransportPassportService implements IService<TransportPassport> {
    @Autowired
    TransportPassportMongoRepository repository;

    @Override
    public TransportPassport create(TransportPassport transportPassport) {
        transportPassport.setCreatedAt(LocalDateTime.now());
        transportPassport.setUpdatedAt(LocalDateTime.now());

        return repository.save(transportPassport);
    }

    @Override
    public TransportPassport get(String id) {
        return repository.findById(id).orElseThrow( () -> new ApiRequestException("NotFound!", HttpStatus.NOT_FOUND));
    }

    @Override
    public TransportPassport update(TransportPassport transportPassport) {
        transportPassport.setCreatedAt(get(transportPassport.getTransportNumber()).getCreatedAt());
        transportPassport.setUpdatedAt(LocalDateTime.now());

        return repository.save(transportPassport);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<TransportPassport> getAll() {
        return repository.findAll();
    }

    public List<TransportPassport> getOneAsList(String transportId) {
        return Collections.singletonList(get(transportId));
    }

    public List<TransportPassport> getAllInPage(int size, int pageNumber) {
        return repository.findAll(PageRequest.of(pageNumber, size, Sort.by("id"))).getContent();
    }
}
