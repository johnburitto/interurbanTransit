package com.johnburitto.interurbantransit.service.interfaces;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class IService
 * @version 1.0.0
 * @since 07.06.2022, 14:03
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import java.util.List;

public interface IService<T> {
    T create(T t);
    T get(String id);
    T update(T t);
    void delete(String id);
    List<T> getAll();
}
