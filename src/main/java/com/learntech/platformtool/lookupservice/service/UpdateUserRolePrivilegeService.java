package com.learntech.platformtool.lookupservice.service;

import com.learntech.platformtool.lookupservice.model.UserRoleInput;
import com.learntech.platformtool.lookupservice.model.UserRolePrivilege;

/**
 * UpdateUserRolePrivilegeService
 *
 * @author Uthiraraj Saminathan
 */
public interface UpdateUserRolePrivilegeService {

    UserRolePrivilege updateUserRolePrivileges(UserRoleInput userRoleInput);
}
