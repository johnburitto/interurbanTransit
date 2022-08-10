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

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class TransportService implements IService<Transport> {
    @Autowired
    TransportMongoRepository transportRepository;

    @Override
    public Transport create(Transport transport) {
        transport.setId(generateNextIndex());
        transport.setCreatedAt(LocalDateTime.now());
        transport.setUpdatedAt(LocalDateTime.now());

        return transportRepository.save(transport);
    }

    private String generateNextIndex() {
        List<Transport> data = transportRepository.findAll();

        try {
            return String.valueOf(Integer.parseInt(data.get(data.size() - 1).getId()) + 1);
        }
        catch (Exception e) {
            return "0";
        }
    }

    @Override
    public Transport get(String id) {
        return transportRepository.findById(id).orElse(null);
    }

    public List<Transport> getOneAsList(String id) {
        return Collections.singletonList(get(id));
    }

    @Override
    public Transport update(Transport transport) {
        transport.setCreatedAt(get(transport.getId()).getCreatedAt());
        transport.setUpdatedAt(LocalDateTime.now());

        return transportRepository.save(transport);
    }

    @Override
    public void delete(String id) {
        transportRepository.deleteById(id);
    }

    @Override
    public List<Transport> getAll() {
        return transportRepository.findAll();
    }

    public List<Transport> getAllFreeTransports(List<Transport> busyTransports) {
        List<Transport> freeTransports = transportRepository.findAll();

        freeTransports.removeAll(busyTransports);

        return  freeTransports;
    }
}
