package com.learntech.platformtool.lookupservice.service;

import com.learntech.platformtool.lookupservice.model.UserRoleInput;
import com.learntech.platformtool.lookupservice.model.UserRolePrivilege;

/**
 * CreateUserRolePrivilegeService
 *
 * @author Uthiraraj Saminathan
 */
public interface CreateUserRolePrivilegeService {

    UserRolePrivilege createUserRole(UserRoleInput userRoleInput);
}
