package com.johnburitto.interurbantransit.service.impls.nosql;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class UserService
 * @version 1.0.0
 * @since 05.08.2022, 13:03
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.annotations.LoggIt;
import com.johnburitto.interurbantransit.exceptions.ApiRequestException;
import com.johnburitto.interurbantransit.model.nosql.User;
import com.johnburitto.interurbantransit.repository.UserMongoRepository;
import com.johnburitto.interurbantransit.service.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService implements IService<User> {
    @Autowired
    UserMongoRepository repository;

    @LoggIt
    @Override
    public User create(User user) {
        user.setId(generateNextIndex());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return repository.save(user);
    }

    private String generateNextIndex() {
        List<User> data = repository.findAll();

        try {
            return String.valueOf(Integer.parseInt(data.get(data.size() - 1).getId()) + 1);
        }
        catch (Exception e) {
            return "0";
        }
    }

    @LoggIt
    @Override
    public User get(String id) {
        return repository.findById(id).orElseThrow( () -> new ApiRequestException("NotFound!", HttpStatus.NOT_FOUND));
    }

    @LoggIt
    @Override
    public User update(User user) {
        user.setCreatedAt(get(user.getId()).getCreatedAt());
        user.setUpdatedAt(LocalDateTime.now());

        return repository.save(user);
    }

    @LoggIt
    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @LoggIt
    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    public User getByLoginAndPassword(String login, String password) {
        return repository.queryFindByLoginAndPassword(login, password);
    }

    public List<User> getAllInPage(int size, int pageNumber) {
        return repository.findAll(PageRequest.of(pageNumber, size, Sort.by("id"))).getContent();
    }
}
