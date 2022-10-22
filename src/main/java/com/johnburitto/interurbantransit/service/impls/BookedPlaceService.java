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

import com.johnburitto.interurbantransit.model.*;
import com.johnburitto.interurbantransit.repository.BookedPlacePostgreSQLRepository;
import com.johnburitto.interurbantransit.service.interfaces.IService;
import com.sun.jmx.remote.internal.ArrayQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class BookedPlaceService implements IService<BookedPlace> {
    @Autowired
    BookedPlacePostgreSQLRepository repository;
    @Autowired
    FlightService flightService;
    @Autowired
    TransportService transportService;
    @Autowired
    UserService userService;

    @Override
    public BookedPlace create(BookedPlace bookedPlace) {
        bookedPlace.setCreatedAt(LocalDateTime.now());
        bookedPlace.setUpdatedAt(LocalDateTime.now());

        Flight flight = flightService.get(bookedPlace.getFlight());
        Transport transport = transportService.get(flight.getTransport());

        transport.bookPlace();
        transportService.update(transport);

        return repository.save(bookedPlace);
    }

    @Override
    public BookedPlace get(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void cancel(BookedPlace bookedPlaceToCancel) {
        bookedPlaceToCancel.setStatus(BookedPlaceStatus.Canceled);
        update(bookedPlaceToCancel);

        Flight flight = flightService.get(bookedPlaceToCancel.getFlight());
        Transport transport = transportService.get(flight.getTransport());

        transport.unbookPlace();
        transportService.update(transport);
    }

    public void returnPlace(BookedPlace bookedPlaceToReturn) {
        bookedPlaceToReturn.setStatus(BookedPlaceStatus.Returned);
        update(bookedPlaceToReturn);

        Flight flight = flightService.get(bookedPlaceToReturn.getFlight());
        Transport transport = transportService.get(flight.getTransport());

        transport.unbookPlace();
        transportService.update(transport);
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

    public List<BookedPlace> getByPassenger(Integer passenger) {
        return repository.queryFindByPassenger(passenger);
    }

    public List<BookedPlace> updateAndGetAll() {
        getAll().forEach(this::updateBookedPlace);

        return getAll();
    }

    private void updateBookedPlace(BookedPlace bookedPlace) {
        Flight flight = flightService.get(bookedPlace.getFlight());

        if (!(flight == null)) {
            updateStatus(bookedPlace, flight);
            update(bookedPlace);
        }
    }

    private void updateStatus(BookedPlace bookedPlace, Flight flight) {
        if (flight.isCanceled()) {
            Transport transportToUpdate = transportService.get(flight.getTransport());
            Flight flightToUpdate = flightService.get(bookedPlace.getFlight());

            bookedPlace.setStatus(BookedPlaceStatus.Canceled);
            transportToUpdate.setNumberOfBookedPlaces(0);

            transportService.update(transportToUpdate);
            flightService.update(flightToUpdate);
        }
        if (flight.isPostponed() && bookedPlace.getStatus().equals(BookedPlaceStatus.OK)) {
            bookedPlace.setStatus(BookedPlaceStatus.Postponed_OK);
        }
    }

    public List<BookedPlace> getAllPlacesByFullName(String firstName, String middleName, String lastName) {
        List<BookedPlace> bookedPlaces = new ArrayList<>();
        List<User> users = userService.getByFullName(firstName, middleName, lastName);

        for (User user : users) {
            bookedPlaces.addAll(getByPassenger(user.getId()));
        }

        return bookedPlaces;
    }

    public List<BookedPlace> getAllPlacesByLastName(String lastName) {
        List<BookedPlace> bookedPlaces = new ArrayList<>();
        List<User> users = userService.getByLastName(lastName);

        for (User user : users) {
            bookedPlaces.addAll(getByPassenger(user.getId()));
        }

        return bookedPlaces;
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
        return repository.queryFindByDayOfBooking(dayOfBooking);
    }

    public int getNumberOfAllBookedPlaces(List<Flight> flights) {
        List<BookedPlace> allBookedPlaces = new ArrayList<>();

        for (Flight flight : flights) {
            allBookedPlaces.addAll(getAllByRouteAndEndDay(flight.getRoute(), flight.getEndDay()));
        }

        return allBookedPlaces.size();
    }

    public List<BookedPlace> getAllByRouteAndEndDay(Integer route, LocalDate endDay) {
        List<BookedPlace> bookedPlaces = new ArrayList<>();
        List<Flight> flights = flightService.getByRouteAndEndDay(route, endDay);

        for (Flight flight : flights) {
            bookedPlaces.addAll(repository.queryFindByFlight(flight.getId()));
        }

        return bookedPlaces;
    }

    public List<BookedPlace> getAllPlacesByFlightAndItsStatus(Integer flight, FlightStatus flightStatus) {
        List<BookedPlace> allBookedPlaces = repository.queryFindByFlight(flight);
        List<BookedPlace> neededPlaces = new ArrayList<>();

        for (BookedPlace bookedPlace : allBookedPlaces) {
            if (flightService.get(bookedPlace.getFlight()).getFlightStatus() == flightStatus) {
                neededPlaces.add(bookedPlace);
            }
        }

        return neededPlaces;
    }
}
