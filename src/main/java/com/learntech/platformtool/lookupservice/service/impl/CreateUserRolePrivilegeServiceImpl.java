package com.learntech.platformtool.lookupservice.service.impl;

import com.learntech.platformtool.lookupservice.entity.AllowedUserPrivilegeDefinition;
import com.learntech.platformtool.lookupservice.entity.UserRoleDefinition;
import com.learntech.platformtool.lookupservice.entity.UserRolePrivilegeDefinition;
import com.learntech.platformtool.lookupservice.enums.UserRoleEnum;
import com.learntech.platformtool.lookupservice.helper.UserRolePrivilegeHelper;
import com.learntech.platformtool.lookupservice.model.UserRoleInput;
import com.learntech.platformtool.lookupservice.model.UserRolePrivilege;
import com.learntech.platformtool.lookupservice.repository.UserRolePrivilegeRepository;
import com.learntech.platformtool.lookupservice.service.CreateUserRolePrivilegeService;
import com.learntech.platformtool.lookupservice.validator.UserRolePrivilegeValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * CreateUserRolePrivilegeServiceImpl
 *
 * @author Uthiraraj Saminathan
 */
@Service
public class CreateUserRolePrivilegeServiceImpl implements CreateUserRolePrivilegeService {

    private final UserRolePrivilegeRepository userRolePrivilegeRepository;
    private final UserRolePrivilegeHelper userRolePrivilegeHelper;

    private UserRoleEnum userRoleEnum;
    private Optional<UserRolePrivilegeDefinition> userRolePrivilegeDefinition;

    public CreateUserRolePrivilegeServiceImpl(UserRolePrivilegeRepository userRolePrivilegeRepository,
                                              UserRolePrivilegeHelper userRolePrivilegeHelper) {
        this.userRolePrivilegeRepository = userRolePrivilegeRepository;
        this.userRolePrivilegeHelper     = userRolePrivilegeHelper;
    }

    /**
     * createUserRole will create a new UserRole with default privileges and return newly created User Role.
     *
     * Will check does this given User Role is already available.
     * If available it won't create this User Role instead will throw error.
     * If not available first add this new User Role to Admin with all the privilege for the Admin.
     * Then create the given User Role with default privileges.
     *
     * @param userRoleInput User Role input.
     * @return UserRolePrivilege for the new user with default privileges.
     */
    @Override
    public UserRolePrivilege createUserRole(UserRoleInput userRoleInput) {

        List<UserRolePrivilegeDefinition> userRoleDefinitions =  userRolePrivilegeRepository.findAllUserRoles();
        UserRolePrivilegeValidator.validateUserRoleIdAndUserRoleName(userRoleInput,userRoleDefinitions);

        UserRolePrivilegeDefinition adminRole = userRoleDefinitions.parallelStream()
                .filter(userRolePrivilege -> UserRoleEnum.ADN1.toString().equals(userRolePrivilege.getUserRoleDefinition().getUserRoleName())
                ).findAny().get();

        AllowedUserPrivilegeDefinition allowedAdminPrivilege = new AllowedUserPrivilegeDefinition(userRoleInput.getUserRoleId(),
                true,true,true,true);

        adminRole.getAllowedUserPrivileges().add(allowedAdminPrivilege);

        UserRolePrivilegeDefinition newUserRolePrivilege = new UserRolePrivilegeDefinition();
        newUserRolePrivilege.setUserRoleDefinition(getUserRoleDefinition(userRoleInput));

        List<UserRolePrivilegeDefinition> userRolePriDefDBList = List.of(newUserRolePrivilege,adminRole);
        userRolePrivilegeRepository.saveAll(userRolePriDefDBList);

        return userRolePrivilegeHelper.getUserRolePrivilege(newUserRolePrivilege);
    }



    private UserRoleDefinition getUserRoleDefinition(UserRoleInput userRoleInput){
        UserRoleDefinition userRoleDefinition = new UserRoleDefinition();
        BeanUtils.copyProperties(userRoleInput,userRoleDefinition);
        return userRoleDefinition;
    }
}
