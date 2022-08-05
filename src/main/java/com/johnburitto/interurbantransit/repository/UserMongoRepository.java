package com.johnburitto.interurbantransit.repository;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class UserMongoRepository
 * @version 1.0.0
 * @since 05.08.2022, 13:02
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMongoRepository extends MongoRepository<User, String> {
    @Query(value = "{$and: [{login: ?0}, {password: ?1}]}")
    public User queryFindByLoginAndPassword(String login, String password);
}
