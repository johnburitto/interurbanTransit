package com.johnburitto.interurbantransit.service.interfaces;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class IService
 * @version 1.0.0
 * @since 22.08.2022, 15:02
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import java.util.List;

public interface IService<T> {
    T create(T t);
    T get(Integer id);
    T update(T t);
    void delete(Integer id);
    List<T> getAll();
}
