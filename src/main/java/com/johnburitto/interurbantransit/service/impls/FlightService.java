package com.johnburitto.interurbantransit.service.impls;

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

import com.johnburitto.interurbantransit.model.*;
import com.johnburitto.interurbantransit.repository.FlightMongoRepository;
import com.johnburitto.interurbantransit.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
        return flightRepository.findById(id).orElse(null);
    }

    public void cancel(String id) {
        Flight flightToCancel = get(id);

        flightToCancel.setFlightStatus(FlightStatus.Canceled);
        update(flightToCancel);
    }

    public void postpone(String id) {
        Flight flightToPostpone = get(id);

        flightToPostpone.setFlightStatus(FlightStatus.Postponed);
        update(flightToPostpone);
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
        flight.setTransport(transportService.get(flight.getTransport().getId()));
        flight.setDriver(driverService.get(flight.getDriver().getId()));
        flight.setRoute(routeService.get(flight.getRoute().getId()));
        updateStatus(flight);
        generateNextFlightItItNeed(flight);

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
    }

    private void generateNextFlightItItNeed(Flight flight) {
        if (flight.isCompleted()) {
            create(flight.generateNextFlight());
            flight.setFlightStatus(FlightStatus.Completed_HasNext);
        }
    }

    public List<Flight> getFreeFlights() {
        List<Flight> freeFlights = findByStatus(FlightStatus.InRoad);
        freeFlights.addAll(findByStatus(FlightStatus.Postponed));

        return freeFlights;
    }

    public List<Flight> findByStatus(FlightStatus flightStatus) {
        return flightRepository.queryFindByStatus(flightStatus);
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
}
