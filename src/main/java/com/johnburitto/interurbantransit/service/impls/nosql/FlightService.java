package com.johnburitto.interurbantransit.service.impls.nosql;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class FlightService
 * @version 1.0.0
 * @since 07.06.2022, 14:13
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.exceptions.ApiRequestException;
import com.johnburitto.interurbantransit.form.nosql.FlightPostponeForm;
import com.johnburitto.interurbantransit.model.nosql.*;
import com.johnburitto.interurbantransit.repository.FlightMongoRepository;
import com.johnburitto.interurbantransit.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FlightService implements IService<Flight> {
    @Autowired
    FlightMongoRepository flightRepository;
    @Autowired
    TransportService transportService;
    @Autowired
    DriverService driverService;
    @Autowired
    RouteService routeService;
    @Autowired
    BookedPlaceService bookedPlaceService;

    @Override
    public Flight create(Flight flight) {
        flight.setId(generateNextIndex());
        flight.setCreatedAt(LocalDateTime.now());
        flight.setUpdatedAt(LocalDateTime.now());

        return flightRepository.save(flight);
    }

    private String generateNextIndex() {
        List<Flight> data = flightRepository.findAll();

        try {
            return String.valueOf(Integer.parseInt(data.get(data.size() - 1).getId()) + 1);
        }
        catch (Exception e) {
            return "0";
        }
    }

    @Override
    public Flight get(String id) {
        return flightRepository.findById(id).orElseThrow( () -> new ApiRequestException("NotFound!", HttpStatus.NOT_FOUND));
    }

    public void cancel(String id) {
        Flight flightToCancel = get(id);

        if (flightToCancel.conditionOfCanceling()) {
            flightToCancel.setFlightStatus(FlightStatus.Canceled);
            update(flightToCancel);
        }
    }

    public void postpone(String id, FlightPostponeForm form) {
        Flight flightToPostpone = get(id);

        if (flightToPostpone.conditionOfPostponing()) {
            flightToPostpone.setFlightStatus(FlightStatus.Postponed);
            flightToPostpone.setStartDay(LocalDate.parse(form.getStartDay()));
            flightToPostpone.setEndDay(LocalDate.parse(form.getEndDay()));
            flightToPostpone.getRoute().setDepartureTime(form.getDepartureTime());
            flightToPostpone.getRoute().setArrivalTime(form.getArrivalTime());
            update(flightToPostpone);
        }
    }

    @Override
    public Flight update(Flight flight) {
        flight.setCreatedAt(get(flight.getId()).getCreatedAt());
        flight.setUpdatedAt(LocalDateTime.now());

        return flightRepository.save(flight);
    }

    @Override
    public void delete(String id) {
        flightRepository.deleteById(id);
    }

    @Override
    public List<Flight> getAll() {
        return flightRepository.findAll();
    }

    public List<Flight> updateAndGetAll() {
        flightRepository.findAll().forEach(this::updateFlight);

        return flightRepository.findAll();
    }

    private void updateFlight(Flight flight) {
        if (!flight.isPostponed()) {
            if (!(transportService.get(flight.getTransport().getId()) == null)) {
                flight.setTransport(transportService.get(flight.getTransport().getId()));
            }
            if (!(driverService.get(flight.getDriver().getId()) == null)) {
                flight.setDriver(driverService.get(flight.getDriver().getId()));
            }
            if (!(routeService.get(flight.getRoute().getId()) == null)) {
                flight.setRoute(routeService.get(flight.getRoute().getId()));
            }
        }
        updateStatus(flight);

        update(flight);
    }

    private void updateStatus(Flight flight) {
        if (flight.conditionOfInRoad()) {
            inRoad(flight);
        }
        if (flight.conditionOfCompleted()) {
            complete(flight);
        }
    }

    private void inRoad(Flight flight) {
        flight.setFlightStatus(FlightStatus.InRoad);
    }

    private void complete(Flight flight) {
        flight.setFlightStatus(FlightStatus.Completed);
        flight.getTransport().setNumberOfBookedPlaces(0);

        transportService.update(flight.getTransport());
    }

    public List<Flight> getFreeFlights() {
        List<Flight> freeFlights = findByStatus(FlightStatus.Waiting);
        freeFlights.addAll(findByStatus(FlightStatus.Postponed));

        return freeFlights.stream().filter(flight -> flight.getTransport().isFree()).collect(Collectors.toList());
    }

    public List<Transport> getAllBusyTransports() {
        List<Transport> busyTransports = new ArrayList<>();

        findByStatus(FlightStatus.InRoad).forEach(flight -> busyTransports.add(flight.getTransport()));
        findByStatus(FlightStatus.Waiting).forEach(flight -> busyTransports.add(flight.getTransport()));

        return busyTransports;
    }

    public List<Driver> getAllBusyDrivers() {
        List<Driver> busyDrivers = new ArrayList<>();

        findByStatus(FlightStatus.InRoad).forEach(flight -> busyDrivers.add(flight.getDriver()));
        findByStatus(FlightStatus.Waiting).forEach(flight -> busyDrivers.add(flight.getDriver()));

        return busyDrivers;
    }

    public List<Route> getAllRouteInRoads() {
        List<Route> uncompletedRoutes = new ArrayList<>();

        findByStatus(FlightStatus.InRoad).forEach(flight -> uncompletedRoutes.add(flight.getRoute()));
        findByStatus(FlightStatus.Waiting).forEach(flight -> uncompletedRoutes.add(flight.getRoute()));

        return uncompletedRoutes;
    }

    public List<Flight> getAllFlightsByStatus(String flightStatus) {
        List<Flight> allFlights = new ArrayList<>();

        switch (flightStatus) {
            case "canceled": {
                allFlights.addAll(findByStatus(FlightStatus.Canceled));
            } break;
            case "postponed": {
                allFlights.addAll(findByStatus(FlightStatus.Postponed));
            } break;
            case "waiting": {
                allFlights.addAll(findByStatus(FlightStatus.Waiting));
            } break;
            case "completed": {
                allFlights.addAll(findByStatus(FlightStatus.Completed));
            } break;
            case "in-road": {
                allFlights.addAll(findByStatus(FlightStatus.InRoad));
            } break;
            default: break;
        }

        return allFlights;
    }

    public List<Flight> findByStatus(FlightStatus flightStatus) {
        return flightRepository.queryFindByStatus(flightStatus);
    }

    public List<Flight> getAllFlightsByRouteAndStatus(String id, String flightStatus) {
        List<Flight> allFlights = new ArrayList<>();

        switch (flightStatus) {
            case "canceled": {
                allFlights.addAll(findByRouteAndStatus(id, FlightStatus.Canceled));
            } break;
            case "postponed": {
                allFlights.addAll(findByRouteAndStatus(id, FlightStatus.Postponed));
            } break;
            case "waiting": {
                allFlights.addAll(findByRouteAndStatus(id, FlightStatus.Waiting));
            } break;
            case "completed": {
                allFlights.addAll(findByRouteAndStatus(id, FlightStatus.Completed));
            } break;
            case "in-road": {
                allFlights.addAll(findByRouteAndStatus(id, FlightStatus.InRoad));
            } break;
            default: break;
        }

        return allFlights;
    }

    public List<Flight> findByRouteAndStatus(String id, FlightStatus flightStatus) {
        return flightRepository.queryFindByRouteAndStatus(routeService.get(id).getId(), flightStatus);
    }

    public List<User> getAllPassengersOfFlight(String id, String flightStatus) {
        List<User> allPassengers = new ArrayList<>();
        List<BookedPlace> allBookedPlaces = new ArrayList<>();

        switch (flightStatus) {
            case "canceled": {
              allBookedPlaces.addAll(bookedPlaceService.getAllPlacesByFlightAndItsStatus(id, FlightStatus.Canceled));
            } break;
            case "postponed": {
                allBookedPlaces.addAll(bookedPlaceService.getAllPlacesByFlightAndItsStatus(id, FlightStatus.Postponed));
            } break;
            case "waiting": {
                allBookedPlaces.addAll(bookedPlaceService.getAllPlacesByFlightAndItsStatus(id, FlightStatus.Waiting));
            } break;
            case "completed": {
                allBookedPlaces.addAll(bookedPlaceService.getAllPlacesByFlightAndItsStatus(id, FlightStatus.Completed));
            }
            default: break;
        }

        allBookedPlaces.forEach(bookedPlace -> allPassengers.add(bookedPlace.getPassenger()));

        return allPassengers;
    }

    public int getNumberOfFlights(List<BookedPlace> bookedPlaces) {
        Set<Flight> uniqueFlights = new HashSet<>();

        for (BookedPlace bookedPlace : bookedPlaces) {
            uniqueFlights.add(bookedPlace.getFlight());
        }

        return uniqueFlights.size();
    }

    public List<Flight> getByStartDay(LocalDate startDay) {
        return flightRepository.queryFindByStartDay(startDay);
    }

    public List<Flight> getAllInPage(int size, int pageNumber) {
        return flightRepository.findAll(PageRequest.of(pageNumber, size, Sort.by("id"))).getContent();
    }
}
