package com.learntech.platformtool.lookupservice.model;

import lombok.*;

import java.util.List;

/**
 * UserRoleInput
 *
 * @author Uthiraraj Saminathan
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleInput {

    private String userRoleId;
    private String userRoleName;
    private String description;
    private Integer rolePriority;
    private List<AllowedUserPrivilege> allowedUserPrivileges;
}
