package com.johnburitto.interurbantransit.controller.nosql.user;

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

import com.johnburitto.interurbantransit.model.nosql.User;
import com.johnburitto.interurbantransit.service.impls.nosql.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {
    @Autowired
    UserService service;

    @GetMapping("/")
    @ApiOperation(value = "Get All Users",
            notes = "Return list of all users",
            response = User.class,
            responseContainer = "List")
    public List<User> showAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get User by id",
            notes = "Return user with such id",
            response = User.class,
            responseContainer = "User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found"),
            @ApiResponse(code = 404, message = "System doesn't have user with such id")
    })
    public User showOne(@PathVariable String id) {
        return service.get(id);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete User by id",
            notes = "Delete user with such id",
            response = Object.class,
            responseContainer = "void")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "System doesn't have user with such id")
    })
    public void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create User",
            notes = "Create user",
            response = User.class,
            responseContainer = "User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created")
    })
    public User createOne(@RequestBody User user) {
        return service.create(user);
    }

    @PutMapping("/edit/{id}")
    @ApiOperation(value = "Edit User by id",
            notes = "Edit user with such id",
            response = User.class,
            responseContainer = "User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "System doesn't have user with such id")
    })
    public User updateOne(@RequestBody User user, @PathVariable String id) {
        user.setId(id);

        return service.update(user);
    }

    @GetMapping("/get/paging/{size}&{pageNumber}")
    public List<User> showPage(@PathVariable int size, @PathVariable int pageNumber) {
        return service.getAllInPage(size, pageNumber);
    }
}
