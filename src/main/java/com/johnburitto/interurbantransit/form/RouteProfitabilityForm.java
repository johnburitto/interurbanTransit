package com.johnburitto.interurbantransit.form;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class RouteProfitabilityForm
 * @version 1.0.0
 * @since 22.06.2022, 0:13
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RouteProfitabilityForm {
    private String route;
    private String startDay;
    private String endDay;
}
