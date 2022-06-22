package com.johnburitto.interurbantransit.service.impls;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class BookedPlaceService
 * @version 1.0.0
 * @since 21.06.2022, 15:31
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.BookedPlace;
import com.johnburitto.interurbantransit.model.BookedPlaceStatus;
import com.johnburitto.interurbantransit.model.Flight;
import com.johnburitto.interurbantransit.model.Transport;
import com.johnburitto.interurbantransit.repository.BookedPlaceMongoRepository;
import com.johnburitto.interurbantransit.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookedPlaceService implements IService<BookedPlace> {
    @Autowired
    BookedPlaceMongoRepository bookedPlaceRepository;
    @Autowired
    FlightService flightService;
    @Autowired
    TransportService transportService;

    @Override
    public BookedPlace create(BookedPlace bookedPlace) {
        bookedPlace.setId(generateNextIndex());
        bookedPlace.setCreatedAt(LocalDateTime.now());
        bookedPlace.setUpdatedAt(LocalDateTime.now());

        return bookedPlaceRepository.save(bookedPlace);
    }

    private String generateNextIndex() {
        List<BookedPlace> data = bookedPlaceRepository.findAll();

        try {
            return String.valueOf(Integer.parseInt(data.get(data.size() - 1).getId()) + 1);
        }
        catch (Exception e) {
            return "0";
        }
    }

    @Override
    public BookedPlace get(String id) {
        return bookedPlaceRepository.findById(id).orElse(null);
    }

    public void cancel(BookedPlace bookedPlaceToCancel) {
        bookedPlaceToCancel.setStatus(BookedPlaceStatus.Canceled);
        update(bookedPlaceToCancel);
    }

    public void returnPlace(BookedPlace bookedPlaceToCancel) {
        bookedPlaceToCancel.setStatus(BookedPlaceStatus.Returned);
        update(bookedPlaceToCancel);
    }

    @Override
    public BookedPlace update(BookedPlace bookedPlace) {
        bookedPlace.setCreatedAt(get(bookedPlace.getId()).getCreatedAt());
        bookedPlace.setUpdatedAt(LocalDateTime.now());

        return bookedPlaceRepository.save(bookedPlace);
    }

    @Override
    public void delete(String id) {
        bookedPlaceRepository.deleteById(id);
    }

    @Override
    public List<BookedPlace> getAll() {
        return bookedPlaceRepository.findAll();
    }

    public List<BookedPlace> updateAndGetAll() {
        bookedPlaceRepository.findAll().forEach(this::updateBookedPlace);

        return bookedPlaceRepository.findAll();
    }

    private void updateBookedPlace(BookedPlace bookedPlace) {
        Flight flightToUpdate = flightService.get(bookedPlace.getFlight().getId());

        if (!(flightToUpdate == null)) {
            bookedPlace.setFlight(flightToUpdate);
            updateStatus(bookedPlace);
            update(bookedPlace);
        }
    }

    private void updateStatus(BookedPlace bookedPlace) {
        if (bookedPlace.getFlight().isCanceled()) {
            Transport transportToUpdate = transportService.get(bookedPlace.getFlight().getTransport().getId());
            Flight flightToUpdate = flightService.get(bookedPlace.getFlight().getId());

            bookedPlace.setStatus(BookedPlaceStatus.Canceled);
            transportToUpdate.setNumberOfBookedPlaces(0);
            flightToUpdate.setTransport(transportToUpdate);

            transportService.update(transportToUpdate);
            flightService.update(flightToUpdate);
        }
        if (bookedPlace.getFlight().isPostponed() && bookedPlace.getStatus().equals(BookedPlaceStatus.OK)) {
            bookedPlace.setStatus(BookedPlaceStatus.Postponed_OK);
        }
    }
}
