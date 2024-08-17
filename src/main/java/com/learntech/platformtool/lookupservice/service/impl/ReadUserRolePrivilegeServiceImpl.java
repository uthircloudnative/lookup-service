package com.learntech.platformtool.lookupservice.service.impl;

import com.learntech.platformtool.lookupservice.entity.UserRoleDefinition;
import com.learntech.platformtool.lookupservice.entity.UserRolePrivilegeDefinition;
import com.learntech.platformtool.lookupservice.model.UserRole;
import com.learntech.platformtool.lookupservice.model.UserRolePrivilege;
import com.learntech.platformtool.lookupservice.repository.UserRolePrivilegeRepository;
import com.learntech.platformtool.lookupservice.service.ReadUserRolePrivilegeService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ReadUserRolePrivilegeServiceImpl
 *
 * @author Uthiraraj Saminathan
 */
@Service
public class ReadUserRolePrivilegeServiceImpl implements ReadUserRolePrivilegeService {

    private static final String Y_STR = "Y";

    private final UserRolePrivilegeRepository userRolePrivilegeRepository;

    public ReadUserRolePrivilegeServiceImpl(UserRolePrivilegeRepository userRolePrivilegeRepository) {
        this.userRolePrivilegeRepository = userRolePrivilegeRepository;
    }

    /**
     * @return
     */
    @Override
    public List<UserRole> getAllUserRoles() {
        List<UserRolePrivilegeDefinition> userRoleDefinitions =  userRolePrivilegeRepository.findAllUserRoles();

        List<UserRole> userRoles = List.of();
        if(!CollectionUtils.isEmpty(userRoleDefinitions)){
            userRoles = userRoleDefinitions.parallelStream()
                    .map(userRoleDefinition -> getUserRole(userRoleDefinition.getUserRoleDefinition()))
                    .collect(Collectors.toList());
        }else{
            //ToDO Throw exception handling
        }
        return userRoles;
    }

    /**
     * @param userRoleId
     * @return
     */
    @Override
    public UserRolePrivilege getUserRolePrivileges(String userRoleId) {
        return null;
    }

    private UserRole getUserRole(UserRoleDefinition userRoleDefinition){

        UserRole userRole = new UserRole(userRoleDefinition.getUserRoleId(),
                userRoleDefinition.getUserRoleName(),
                userRoleDefinition.getDescription(),
                userRoleDefinition.getRolePriority());

        return userRole;
    }
}
