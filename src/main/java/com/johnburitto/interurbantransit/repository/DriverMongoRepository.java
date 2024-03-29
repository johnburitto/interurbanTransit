package com.johnburitto.interurbantransit.repository;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class DriverMongoRepository
 * @version 1.0.0
 * @since 07.06.2022, 14:43
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.nosql.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverMongoRepository extends MongoRepository<Driver, String> {
    @Query(value = "{workingBook_numberOfWorkingBook: ?0}")
    public Driver queryFindByWorkingBookNumber(String workingBookNumber);
}
