package com.johnburitto.interurbantransit.service.impls;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class UserService
 * @version 1.0.0
 * @since 22.08.2022, 15:57
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.User;
import com.johnburitto.interurbantransit.repository.UserPostgreSQLRepository;
import com.johnburitto.interurbantransit.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService implements IService<User> {
    @Autowired
    UserPostgreSQLRepository repository;

    @Override
    public User create(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return repository.save(user);
    }

    @Override
    public User get(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User update(User user) {
        user.setCreatedAt(get(user.getId()).getCreatedAt());
        user.setUpdatedAt(LocalDateTime.now());

        return repository.save(user);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }
}
