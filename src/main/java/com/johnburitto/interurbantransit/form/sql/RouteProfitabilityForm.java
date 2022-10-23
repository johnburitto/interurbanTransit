package com.johnburitto.interurbantransit.form.sql;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class RouteProfitabilityForm
 * @version 1.0.0
 * @since 24.08.2022, 16:40
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RouteProfitabilityForm {
    private Integer route;
    private String startDay;
    private String endDay;
}
