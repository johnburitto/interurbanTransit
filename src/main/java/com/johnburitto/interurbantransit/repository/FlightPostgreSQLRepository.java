package com.johnburitto.interurbantransit.repository;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class FlightPostgreSQLRepository
 * @version 1.0.0
 * @since 22.08.2022, 15:46
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightPostgreSQLRepository extends JpaRepository<Flight, Integer> {
    @Query("select f from Flight f where f.startDay = ?1")
    List<Flight> queryFindByStartDay(LocalDate startDay);
}
