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

import com.johnburitto.interurbantransit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPostgreSQLRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.login = ?1 and u.password = ?2")
    User queryFindByLoginAndPassword(String login, String password);
}
