package com.johnburitto.interurbantransit.repository;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class FlightMongoRepository
 * @version 1.0.0
 * @since 07.06.2022, 14:43
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.nosql.Flight;
import com.johnburitto.interurbantransit.model.nosql.FlightStatus;
import com.johnburitto.interurbantransit.model.nosql.Route;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightMongoRepository extends MongoRepository<Flight, String> {
    @Query(value = "{flightStatus: ?0}")
    public List<Flight> queryFindByStatus(FlightStatus flightStatus);

    @Query(value = "{$and: [{route_id: ?0}, {flightStatus: ?1}]}")
    public List<Flight> queryFindByRouteAndStatus(String route, FlightStatus flightStatus);

    @Query(value = "{startDay: ?0}")
    public List<Flight> queryFindByStartDay(LocalDate startDay);
}
