package com.johnburitto.interurbantransit.repository;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class BookedPlacePostgreSQLRepository
 * @version 1.0.0
 * @since 22.08.2022, 16:04
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.BookedPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookedPlacePostgreSQLRepository extends JpaRepository<BookedPlace, Integer> {
    @Query("select b from BookedPlace b where b.passenger = ?1")
    List<BookedPlace> queryFindByPassenger(Integer passenger);
}
