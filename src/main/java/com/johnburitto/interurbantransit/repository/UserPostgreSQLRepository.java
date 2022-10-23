package com.johnburitto.interurbantransit.repository;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class UserPostgreSQLRepository
 * @version 1.0.0
 * @since 22.08.2022, 15:57
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.sql.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPostgreSQLRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.login = ?1 and u.password = ?2")
    User queryFindByLoginAndPassword(String login, String password);

    @Query("select u from User u where u.firstName = ?1 and u.middleName = ?2 and u.lastName = ?3")
    List<User> queryFindByFullName(String firstName, String middleName, String lastName);

    @Query("select u from User u where u.lastName = ?1")
    List<User> queryFindByLastName(String lastName);
}
