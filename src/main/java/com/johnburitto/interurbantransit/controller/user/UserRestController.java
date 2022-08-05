package com.johnburitto.interurbantransit.controller.user;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class UserRestController
 * @version 1.0.0
 * @since 05.08.2022, 13:45
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.User;
import com.johnburitto.interurbantransit.service.impls.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {
    @Autowired
    UserService service;

    @GetMapping("/")
    public List<User> showAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public User showOne(@PathVariable String id) {
        return service.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @PostMapping("/create")
    public User createOne(@RequestBody User user) {
        return service.create(user);
    }

    @PutMapping("/edit/{id}")
    public User updateOne(@RequestBody User user, @PathVariable String id) {
        user.setId(id);

        return service.update(user);
    }
}
