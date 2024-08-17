package com.learntech.platformtool.lookupservice.model;

import lombok.*;

/**
 * AllowedUserPrivilege
 *
 * @author Uthiraraj Saminathan
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = {"userRoleID"})
public class AllowedUserPrivilege {

  private String userRoleID;
  private Boolean create;
  private Boolean update;
  private Boolean delete;
  private Boolean read;


}
