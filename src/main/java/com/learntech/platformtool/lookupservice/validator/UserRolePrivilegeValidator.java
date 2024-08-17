package com.learntech.platformtool.lookupservice.validator;

import com.learntech.platformtool.lookupservice.entity.UserRolePrivilegeDefinition;
import com.learntech.platformtool.lookupservice.enums.UserRoleEnum;
import com.learntech.platformtool.lookupservice.model.UserRoleInput;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * UserRolePrivilegeValidator
 *
 * @author Uthiraraj Saminathan
 */
public class UserRolePrivilegeValidator {

    public static  void validateUserRoleIdAndUserRoleName(UserRoleInput userRoleInput,
                                                  List<UserRolePrivilegeDefinition> userRolePrivilegeDefinitionList){
        if(!CollectionUtils.isEmpty(userRolePrivilegeDefinitionList)){
            Boolean hasAdmin = false;
            for(UserRolePrivilegeDefinition userRoleDef:userRolePrivilegeDefinitionList){
                if(UserRoleEnum.ADN1.toString().equals(userRoleInput.getUserRoleName())){
                    hasAdmin = true;
                }

                if(userRoleDef.getUserRoleDefinition().getUserRoleId().equals(userRoleInput.getUserRoleId())){
                    throw new RuntimeException("User Role Id exist already");
                }

                if(userRoleDef.getUserRoleDefinition().getUserRoleName().equals(userRoleInput.getUserRoleName())){
                    throw new RuntimeException("User Role Name exist already");
                }
            }

            if(!hasAdmin){
                throw new RuntimeException("No Admin Role available");
            }
        }
    }
}
