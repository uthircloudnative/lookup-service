package com.learntech.platformtool.lookupservice.service;

import com.learntech.platformtool.lookupservice.model.UserRole;
import com.learntech.platformtool.lookupservice.model.UserRoleInput;
import com.learntech.platformtool.lookupservice.model.UserRolePrivilege;

import java.util.List;

/**
 * UserRolePrivilegeService
 *
 * @author Uthiraraj Saminathan
 */
public interface UserRolePrivilegeService {

    List<UserRole> getAllUserRoles();

    UserRolePrivilege getUserRolePrivileges(String userRoleId);

    UserRolePrivilege createUserRole(UserRoleInput userRoleInput);
}
