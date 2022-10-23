package com.johnburitto.interurbantransit.repository;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class RouteProfitabilityMongoRepository
 * @version 1.0.0
 * @since 21.06.2022, 22:42
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.nosql.RouteProfitability;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteProfitabilityMongoRepository extends MongoRepository<RouteProfitability, String> {

}
