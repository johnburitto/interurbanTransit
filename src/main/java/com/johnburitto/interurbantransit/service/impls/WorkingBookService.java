package com.johnburitto.interurbantransit.service.impls;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class WorkingBookService
 * @version 1.0.0
 * @since 08.06.2022, 14:18
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.PlaceOfWork;
import com.johnburitto.interurbantransit.model.WorkingBook;
import com.johnburitto.interurbantransit.repository.WorkingBookMongoRepository;
import com.johnburitto.interurbantransit.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class WorkingBookService implements IService<WorkingBook> {
    @Autowired
    WorkingBookMongoRepository repository;

    @Override
    public WorkingBook create(WorkingBook workingBook) {
        workingBook.setCreatedAt(LocalDateTime.now());
        workingBook.setUpdatedAt(LocalDateTime.now());

        return repository.save(workingBook);
    }

    @Override
    public WorkingBook get(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public WorkingBook update(WorkingBook workingBook) {
        workingBook.setCreatedAt(get(workingBook.getNumberOfWorkingBook()).getCreatedAt());
        workingBook.setUpdatedAt(LocalDateTime.now());

        return repository.save(workingBook);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<WorkingBook> getAll() {
        return repository.findAll();
    }

    public List<WorkingBook> getOneAsList(String numberOfWorkingBook) {
        return Collections.singletonList(get(numberOfWorkingBook));
    }

    public List<PlaceOfWork> getPlacesOfWorkFromWorkingBook(String numberOfWorkingBook) {
        return get(numberOfWorkingBook).getPlacesOfWork();
    }
}
