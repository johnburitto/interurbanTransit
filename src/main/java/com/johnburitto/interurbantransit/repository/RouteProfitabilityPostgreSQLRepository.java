package com.johnburitto.interurbantransit.repository;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class RouteProfitabilityPostgreSQLRepository
 * @version 1.0.0
 * @since 22.08.2022, 16:11
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.sql.RouteProfitability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteProfitabilityPostgreSQLRepository extends JpaRepository<RouteProfitability, Integer> {

}
