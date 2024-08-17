package com.learntech.platformtool.lookupservice.service;

import com.learntech.platformtool.lookupservice.validator.UserRolePrivilegeValidator;
import com.learntech.platformtool.lookupservice.entity.AllowedUserPrivilegeDefinition;
import com.learntech.platformtool.lookupservice.entity.UserRoleDefinition;
import com.learntech.platformtool.lookupservice.entity.UserRolePrivilegeDefinition;
import com.learntech.platformtool.lookupservice.enums.UserRoleEnum;
import com.learntech.platformtool.lookupservice.model.AllowedUserPrivilege;
import com.learntech.platformtool.lookupservice.model.UserRole;
import com.learntech.platformtool.lookupservice.model.UserRoleInput;
import com.learntech.platformtool.lookupservice.model.UserRolePrivilege;
import com.learntech.platformtool.lookupservice.repository.UserRolePrivilegeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



/**
 * UserRolePrivilegeServiceImpl
 *
 * @author Uthiraraj Saminathan
 *
 */
@Service
public class UserRolePrivilegeServiceImpl implements UserRolePrivilegeService{

    private static final String Y_STR = "Y";


    private final UserRolePrivilegeRepository userRolePrivilegeRepository;

    private UserRoleEnum userRoleEnum;
    private Optional<UserRolePrivilegeDefinition> userRolePrivilegeDefinition;

    public UserRolePrivilegeServiceImpl(UserRolePrivilegeRepository userRolePrivilegeRepository) {
        this.userRolePrivilegeRepository = userRolePrivilegeRepository;
    }

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

    @Override
    public UserRolePrivilege getUserRolePrivileges(String userRoleId) {

        Optional<UserRolePrivilegeDefinition> userRoleDefinition = userRolePrivilegeRepository.findByUserRoleId(userRoleId);

        UserRolePrivilegeDefinition userRolePrivilege = userRoleDefinition.orElseThrow(() -> handleError("No Data Found"));

        return getUserRolePrivilege(userRolePrivilege);
    }


    @Override
    public UserRolePrivilege createUserRole(UserRoleInput userRoleInput) {
        //Check given UserRoleId is already or UserRoleName is already available
        //If any one of them is available throw error
        //If not Take get the Admin Document
        //Add the new userRole with default access for the Admin.
        //Create new userRole with default no access
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
        getUserRolePrivilege(newUserRolePrivilege);
        return getUserRolePrivilege(newUserRolePrivilege);
    }


    private UserRoleDefinition getUserRoleDefinition(UserRoleInput userRoleInput){
        UserRoleDefinition userRoleDefinition = new UserRoleDefinition();
        BeanUtils.copyProperties(userRoleInput,userRoleDefinition);
        return userRoleDefinition;
    }
    private UserRole getUserRole(UserRoleDefinition userRoleDefinition){

        UserRole userRole = new UserRole(userRoleDefinition.getUserRoleId(),
                userRoleDefinition.getUserRoleName(),
                userRoleDefinition.getDescription(),
                userRoleDefinition.getRolePriority());

        return userRole;
    }

    private UserRolePrivilege getUserRolePrivilege(UserRolePrivilegeDefinition userRolePrivilegeDefinition){

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

    private RuntimeException handleError(String errorMessage){
        return new RuntimeException(errorMessage);
    }

}
