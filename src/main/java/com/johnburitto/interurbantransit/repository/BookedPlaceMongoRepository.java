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

import com.johnburitto.interurbantransit.model.BookedPlace;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookedPlaceMongoRepository extends MongoRepository<BookedPlace, String> {

}
