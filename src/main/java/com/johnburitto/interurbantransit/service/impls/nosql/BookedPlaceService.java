package com.johnburitto.interurbantransit.service.impls.nosql;

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

import com.johnburitto.interurbantransit.exceptions.ApiRequestException;
import com.johnburitto.interurbantransit.model.nosql.*;
import com.johnburitto.interurbantransit.repository.BookedPlaceMongoRepository;
import com.johnburitto.interurbantransit.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

        Flight flight = bookedPlace.getFlight();

        flight.getTransport().bookPlace();
        bookedPlace.setFlight(flight);
        flightService.update(flight);
        transportService.update(flight.getTransport());

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
        return bookedPlaceRepository.findById(id).orElseThrow( () -> new ApiRequestException("NotFound!", HttpStatus.NOT_FOUND));
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

    public List<BookedPlace> getAllPlacesByFlightAndItsStatus(String flight, FlightStatus flightStatus) {
        return bookedPlaceRepository.queryFindByFlightAndItsStatus(flight, flightStatus);
    }

    public List<BookedPlace> getAllPlacesByName(Name name) {
        return bookedPlaceRepository.queryFindByName(name);
    }

    public List<BookedPlace> getAllPlacesByLastName(String lastName) {
        return bookedPlaceRepository.queryFindByLastName(lastName);
    }

    public int getNumberOfAllBookedPlaces(List<Flight> flights) {
        List<BookedPlace> allBookedPlaces = new ArrayList<>();

        for (Flight flight : flights) {
            allBookedPlaces.addAll(getAllByRouteAndEndDay(flight.getRoute(), flight.getEndDay()));
        }

        return allBookedPlaces.size();
    }

    public List<BookedPlace> getAllByRouteAndEndDay(Route route, LocalDate endDay) {
        return bookedPlaceRepository.queryFindByRouteAndEndDay(route.getId(), endDay);
    }

    public List<BookedPlace> getAllByDayOfBooking(String startDay, String endDay) {
        LocalDate sDay = LocalDate.parse(startDay);
        LocalDate eDay = LocalDate.parse(endDay);
        List<BookedPlace> allBookedPlaces = new ArrayList<>();
        long dayScale = ChronoUnit.DAYS.between(sDay, eDay);

        for (long i = 0; i <= dayScale; i++) {
            allBookedPlaces.addAll(getByDayOfBooking(sDay.plusDays(i)));
        }

        return allBookedPlaces;
    }

    public List<BookedPlace> getByDayOfBooking(LocalDate dayOfBooking) {
        return bookedPlaceRepository.queryFindByDayOfBooking(dayOfBooking);
    }

    public List<BookedPlace> getAllInPage(int size, int pageNumber) {
        return bookedPlaceRepository.findAll(PageRequest.of(pageNumber, size, Sort.by("id"))).getContent();
    }
}
