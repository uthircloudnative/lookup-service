package com.learntech.platformtool.lookupservice.model;

import lombok.*;

import java.util.List;

/**
 * UserRolePrivilege
 *
 * @author Uthiraraj Saminathan
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRolePrivilege {

  private UserRole userRole;
  private List<AllowedUserPrivilege> allowedUserPrivileges;

}
