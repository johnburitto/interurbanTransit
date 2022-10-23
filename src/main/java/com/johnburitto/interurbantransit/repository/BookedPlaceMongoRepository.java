package com.johnburitto.interurbantransit.repository;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class BookedPlaceMongoRepository
 * @version 1.0.0
 * @since 21.06.2022, 15:31
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.nosql.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookedPlaceMongoRepository extends MongoRepository<BookedPlace, String> {
    @Query(value = "{$and: [{flight: ?0}, {flight_flightStatus: ?1}]}")
    public List<BookedPlace> queryFindByFlightAndItsStatus(Flight flight, FlightStatus flightStatus);

    @Query(value = "{flight: ?0}")
    public List<BookedPlace> queryFindByFlight(Flight flight);

    @Query(value = "{flight_flightStatus: ?0}")
    public List<BookedPlace> queryFindByFlightStatus(FlightStatus flightStatus);

    @Query(value = "{passenger_contactPerson_name: ?0}")
    public List<BookedPlace> queryFindByName(Name name);

    @Query(value = "{passenger_contactPerson_name_lastName: ?0}")
    public List<BookedPlace> queryFindByLastName(String lastName);

    @Query(value = "{$and: [{flight_route: ?0}, {flight_endDay: ?1}]}")
    public List<BookedPlace> queryFindByRouteAndEndDay(Route route, LocalDate endDay);

    @Query(value = "{dayOfBooking: ?0}")
    public List<BookedPlace> queryFindByDayOfBooking(LocalDate dayOfBooking);
}
