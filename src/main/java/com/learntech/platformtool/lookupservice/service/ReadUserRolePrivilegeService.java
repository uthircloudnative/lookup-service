package com.learntech.platformtool.lookupservice.service;

import com.learntech.platformtool.lookupservice.model.UserRole;
import com.learntech.platformtool.lookupservice.model.UserRolePrivilege;

import java.util.List;

/**
 * ReadUserRolePrivilegeService
 *
 * @author Uthiraraj Saminathan
 */
public interface ReadUserRolePrivilegeService {

    List<UserRole> getAllUserRoles();

    UserRolePrivilege getUserRolePrivileges(String userRoleId);
}
