package com.johnburitto.interurbantransit.service.impls;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class BookedPlaceService
 * @version 1.0.0
 * @since 22.08.2022, 16:06
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.BookedPlace;
import com.johnburitto.interurbantransit.repository.BookedPlacePostgreSQLRepository;
import com.johnburitto.interurbantransit.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookedPlaceService implements IService<BookedPlace> {
    @Autowired
    BookedPlacePostgreSQLRepository repository;

    @Override
    public BookedPlace create(BookedPlace bookedPlace) {
        bookedPlace.setCreatedAt(LocalDateTime.now());
        bookedPlace.setUpdatedAt(LocalDateTime.now());

        return repository.save(bookedPlace);
    }

    @Override
    public BookedPlace get(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public BookedPlace update(BookedPlace bookedPlace) {
        bookedPlace.setCreatedAt(get(bookedPlace.getId()).getCreatedAt());
        bookedPlace.setUpdatedAt(LocalDateTime.now());

        return repository.save(bookedPlace);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<BookedPlace> getAll() {
        return repository.findAll();
    }
}
