package com.johnburitto.interurbantransit.service.impls;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class DriverService
 * @version 1.0.0
 * @since 07.06.2022, 14:07
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.exceptions.ApiRequestException;
import com.johnburitto.interurbantransit.model.Driver;
import com.johnburitto.interurbantransit.model.Transport;
import com.johnburitto.interurbantransit.repository.DriverMongoRepository;
import com.johnburitto.interurbantransit.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DriverService implements IService<Driver> {
    @Autowired
    DriverMongoRepository repository;

    @Override
    public Driver create(Driver driver) {
        driver.setId(generateNextIndex());
        driver.setCreatedAt(LocalDateTime.now());
        driver.setUpdatedAt(LocalDateTime.now());

        return repository.save(driver);
    }

    private String generateNextIndex() {
        List<Driver> data = repository.findAll();

        try {
            return String.valueOf(Integer.parseInt(data.get(data.size() - 1).getId()) + 1);
        }
        catch (Exception e) {
            return "0";
        }
    }

    @Override
    public Driver get(String id) {
        return repository.findById(id).orElseThrow( () -> new ApiRequestException("NotFound!", HttpStatus.NOT_FOUND));
    }

    @Override
    public Driver update(Driver driver) {
        driver.setCreatedAt(get(driver.getId()).getCreatedAt());
        driver.setUpdatedAt(LocalDateTime.now());

        return repository.save(driver);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Driver> getAll() {
        return repository.findAll();
    }

    public Driver getByWorkingBookNumber(String workingBookNumber) {
        return repository.queryFindByWorkingBookNumber(workingBookNumber);
    }

    public List<Driver> getAllFreeDrivers(List<Driver> busyDrivers) {
        List<Driver> freeDrivers = repository.findAll();

        freeDrivers.removeAll(busyDrivers);

        return freeDrivers;
    }

    public List<Driver> getAllInPage(int size, int pageNumber) {
        return repository.findAll(PageRequest.of(pageNumber, size, Sort.by("id"))).getContent();
    }
}
