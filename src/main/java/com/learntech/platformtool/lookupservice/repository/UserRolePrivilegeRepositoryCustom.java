package com.learntech.platformtool.lookupservice.repository;

import com.learntech.platformtool.lookupservice.entity.UserRolePrivilegeDefinition;

import java.util.List;
import java.util.Optional;

/**
 * CustomUserRolePrivilegeRepository
 *
 * @author Uthiraraj Saminathan
 */
public interface UserRolePrivilegeRepositoryCustom {

    List<UserRolePrivilegeDefinition> findAllUserRoles();

    Optional<UserRolePrivilegeDefinition> findByUserRoleId(String userRoleId);

}
