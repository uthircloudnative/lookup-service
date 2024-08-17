package com.learntech.platformtool.lookupservice.entity;

import lombok.*;

/**
 * UserRoleDefinition
 *
 * @author Uthiraraj Saminathan
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRoleDefinition {

    private String userRoleId;
    private String userRoleName;
    private String description;
    private Integer rolePriority;

}
