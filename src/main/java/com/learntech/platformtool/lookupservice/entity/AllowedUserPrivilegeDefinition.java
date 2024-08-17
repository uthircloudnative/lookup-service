package com.learntech.platformtool.lookupservice.entity;

import lombok.*;

/**
 * AllowedUserPrivilegeDefinition
 *
 * @author Uthiraraj Saminathan
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = {"userRoleID"})
public class AllowedUserPrivilegeDefinition {

    private String userRoleID;
    private Boolean create;
    private Boolean update;
    private Boolean delete;
    private Boolean read;

}
