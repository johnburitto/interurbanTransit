package com.johnburitto.interurbantransit.service.impls;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class FlightService
 * @version 1.0.0
 * @since 22.08.2022, 15:47
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.form.FlightPostponeForm;
import com.johnburitto.interurbantransit.model.*;
import com.johnburitto.interurbantransit.repository.FlightPostgreSQLRepository;
import com.johnburitto.interurbantransit.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FlightService implements IService<Flight> {
    @Autowired
    FlightPostgreSQLRepository repository;
    @Autowired
    RouteService routeService;
    @Autowired
    BookedPlaceService bookedPlaceService;
    @Autowired
    UserService userService;

    @Override
    public Flight create(Flight flight) {
        flight.setCreatedAt(LocalDateTime.now());
        flight.setUpdatedAt(LocalDateTime.now());

        return repository.save(flight);
    }

    @Override
    public Flight get(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Flight update(Flight flight) {
        flight.setCreatedAt(get(flight.getId()).getCreatedAt());
        flight.setUpdatedAt(LocalDateTime.now());

        return repository.save(flight);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Flight> getAll() {
        return repository.findAll();
    }

    public List<Flight> getByStartDay(LocalDate startDay) {
        return repository.queryFindByStartDay(startDay);
    }

    public int getNumberOfFlights(List<BookedPlace> bookedPlaces) {
        Set<Integer> uniqueFlights = new HashSet<>();

        for (BookedPlace bookedPlace : bookedPlaces) {
            uniqueFlights.add(bookedPlace.getFlight());
        }

        return uniqueFlights.size();
    }

    public boolean conditionOfInRoad(Flight flight) {
        if (LocalDate.now().isAfter(flight.getStartDay()) && (flight.getFlightStatus().equals(FlightStatus.Waiting) ||
                flight.getFlightStatus().equals(FlightStatus.Postponed))) {
            return true;
        }
        else return LocalDate.now().equals(flight.getStartDay()) &&
                LocalTime.now().isAfter(routeService.get(flight.getRoute()).getDepartureTime()) &&
                (flight.getFlightStatus().equals(FlightStatus.Waiting) ||
                 flight.getFlightStatus().equals(FlightStatus.Postponed));
    }

    public boolean conditionOfCompleted(Flight flight) {
        if (LocalDate.now().isAfter(flight.getEndDay()) && flight.getFlightStatus().equals(FlightStatus.InRoad)) {
            return true;
        }
        else return LocalDate.now().equals(flight.getEndDay()) &&
                LocalTime.now().isAfter(routeService.get(flight.getRoute()).getArrivalTime()) &&
                flight.getFlightStatus().equals(FlightStatus.InRoad);
    }

    public void cancel(Integer id) {
        Flight flightToCancel = get(id);

        if (flightToCancel.conditionOfCanceling()) {
            flightToCancel.setFlightStatus(FlightStatus.Canceled);
            update(flightToCancel);
        }
    }

    public void postpone(Integer id, FlightPostponeForm form) {
        Flight flightToPostpone = get(id);
        Route route = routeService.get(flightToPostpone.getRoute());

        if (flightToPostpone.conditionOfPostponing()) {
            flightToPostpone.setFlightStatus(FlightStatus.Postponed);
            flightToPostpone.setStartDay(LocalDate.parse(form.getStartDay()));
            flightToPostpone.setEndDay(LocalDate.parse(form.getEndDay()));
            route.setDepartureTime(form.getDepartureTime());
            route.setArrivalTime(form.getArrivalTime());
            update(flightToPostpone);
            routeService.update(route);
        }
    }

    public List<Flight> getAllFlightsByStatus(String flightStatus) {
        List<Flight> allFlights = new ArrayList<>();

        switch (flightStatus) {
            case "canceled": {
                allFlights.addAll(findByStatus(FlightStatus.Canceled));
                allFlights.addAll(findByStatus(FlightStatus.Canceled_HasNext));
            } break;
            case "postponed": {
                allFlights.addAll(findByStatus(FlightStatus.Postponed));
            } break;
            case "waiting": {
                allFlights.addAll(findByStatus(FlightStatus.Waiting));
            } break;
            case "completed": {
                allFlights.addAll(findByStatus(FlightStatus.Completed));
                allFlights.addAll(findByStatus(FlightStatus.Completed_HasNext));
            } break;
            case "in-road": {
                allFlights.addAll(findByStatus(FlightStatus.InRoad));
            } break;
            default: break;
        }

        return allFlights;
    }

    public List<Flight> findByStatus(FlightStatus flightStatus) {
        return repository.queryFindByFlightStatus(flightStatus);
    }

    public List<Flight> getByRouteAndEndDay(Integer route, LocalDate endDay) {
        return repository.queryFindByRouteAndEndDay(route, endDay);
    }

    public List<Flight> getAllFlightsByRouteAndStatus(Integer route, String flightStatus) {
        List<Flight> allFlights = new ArrayList<>();

        switch (flightStatus) {
            case "canceled": {
                allFlights.addAll(repository.queryFindByRouteAndFlightStatus(route, FlightStatus.Canceled));
                allFlights.addAll(repository.queryFindByRouteAndFlightStatus(route, FlightStatus.Canceled_HasNext));
            } break;
            case "postponed": {
                allFlights.addAll(repository.queryFindByRouteAndFlightStatus(route, FlightStatus.Postponed));
            } break;
            case "waiting": {
                allFlights.addAll(repository.queryFindByRouteAndFlightStatus(route, FlightStatus.Waiting));
            } break;
            case "completed": {
                allFlights.addAll(repository.queryFindByRouteAndFlightStatus(route, FlightStatus.Completed));
                allFlights.addAll(repository.queryFindByRouteAndFlightStatus(route, FlightStatus.Completed_HasNext));
            } break;
            case "in-road": {
                allFlights.addAll(repository.queryFindByRouteAndFlightStatus(route, FlightStatus.InRoad));
            } break;
            default: break;
        }

        return allFlights;
    }

    public List<User> getAllPassengersOfFlight(Integer id, String flightStatus) {
        List<User> allPassengers = new ArrayList<>();
        Set<Integer> uniquePassengersIds = new HashSet<>();
        List<BookedPlace> allBookedPlaces = new ArrayList<>();

        switch (flightStatus) {
            case "canceled": {
                allBookedPlaces.addAll(bookedPlaceService.getAllPlacesByFlightAndItsStatus(id, FlightStatus.Canceled));
                allBookedPlaces.addAll(bookedPlaceService.getAllPlacesByFlightAndItsStatus(id, FlightStatus.Canceled_HasNext));
            } break;
            case "postponed": {
                allBookedPlaces.addAll(bookedPlaceService.getAllPlacesByFlightAndItsStatus(id, FlightStatus.Postponed));
            } break;
            case "waiting": {
                allBookedPlaces.addAll(bookedPlaceService.getAllPlacesByFlightAndItsStatus(id, FlightStatus.Waiting));
            } break;
            case "completed": {
                allBookedPlaces.addAll(bookedPlaceService.getAllPlacesByFlightAndItsStatus(id, FlightStatus.Completed));
                allBookedPlaces.addAll(bookedPlaceService.getAllPlacesByFlightAndItsStatus(id, FlightStatus.Completed_HasNext));
            }
            default: break;
        }

        allBookedPlaces.forEach(bookedPlace -> uniquePassengersIds.add(bookedPlace.getPassenger()));
        uniquePassengersIds.forEach(passengerId -> allPassengers.add(userService.get(passengerId)));

        return allPassengers;
    }
}
