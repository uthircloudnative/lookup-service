package com.learntech.platformtool.lookupservice.model;

import lombok.*;

/**
 * UserRoleEnum
 *
 * @author Uthiraraj Saminathan
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRole {

  private String userRoleId;
  private String userRoleName;
  private String description;
  private Integer rolePriority;

}
