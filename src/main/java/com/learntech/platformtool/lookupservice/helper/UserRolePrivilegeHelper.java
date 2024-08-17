package com.learntech.platformtool.lookupservice.helper;

import com.learntech.platformtool.lookupservice.entity.UserRolePrivilegeDefinition;
import com.learntech.platformtool.lookupservice.model.AllowedUserPrivilege;
import com.learntech.platformtool.lookupservice.model.UserRole;
import com.learntech.platformtool.lookupservice.model.UserRolePrivilege;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * UserRolePrivilegeHelper
 *
 * @author Uthiraraj Saminathan
 */
@Component
public class UserRolePrivilegeHelper {

    public UserRolePrivilege getUserRolePrivilege(UserRolePrivilegeDefinition userRolePrivilegeDefinition){

        UserRolePrivilege userRolePrivilege = new UserRolePrivilege();
        UserRole userRole                   = new UserRole();

        BeanUtils.copyProperties(userRolePrivilegeDefinition.getUserRoleDefinition(), userRole);
        userRolePrivilege.setUserRole(userRole);

        List<AllowedUserPrivilege> allowedUserPrivileges = List.of();
        if(!CollectionUtils.isEmpty(userRolePrivilegeDefinition.getAllowedUserPrivileges())){
            allowedUserPrivileges =  userRolePrivilegeDefinition.getAllowedUserPrivileges()
                    .parallelStream().map(allowedUserPrivilege -> {
                        AllowedUserPrivilege userPrivilege = new AllowedUserPrivilege();
                        BeanUtils.copyProperties(allowedUserPrivilege,userPrivilege);
                        return userPrivilege;
                    }).collect(Collectors.toList());
        }
        userRolePrivilege.setAllowedUserPrivileges(allowedUserPrivileges);

        return userRolePrivilege;
    }
}
