package com.johnburitto.interurbantransit.model;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class UserPerm
 * @version 1.0.0
 * @since 05.08.2022, 16:03
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Represent permissions of user")
public class UserPerm {
    private String keys;
    private String create;
    private String delete;
    private String edit;
    private String columnFilters;
    private String allTables;
    private UserType type;

    private UserPerm(String keys, String create, String delete, String edit, String columnFilters, String allTables, UserType type) {
        this.keys = keys;
        this.create = create;
        this.delete = delete;
        this.edit = edit;
        this.columnFilters = columnFilters;
        this.allTables = allTables;
        this.type = type;
    }

    public static UserPerm PermOf(UserType type) {
        switch (type) {
            case Owner: {
                return new UserPerm("block", "block", "table-cell", "table-cell", "flex", "flex", type);
            }
            case Administrator: {
                return new UserPerm("none", "block", "table-cell", "table-cell", "flex", "flex", type);
            }
            case Operator: {
                return new UserPerm("none", "none", "none", "table-cell", "none", "flex", type);
            }
            default: {
                return new UserPerm("none", "none", "none", "none", "none", "none",type);
            }
        }
    }
}
