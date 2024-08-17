package com.learntech.platformtool.lookupservice.service.impl;

import com.learntech.platformtool.lookupservice.entity.AllowedUserPrivilegeDefinition;
import com.learntech.platformtool.lookupservice.entity.UserRolePrivilegeDefinition;
import com.learntech.platformtool.lookupservice.helper.UserRolePrivilegeHelper;
import com.learntech.platformtool.lookupservice.mapper.Mapper;
import com.learntech.platformtool.lookupservice.model.AllowedUserPrivilege;
import com.learntech.platformtool.lookupservice.model.UserRoleInput;
import com.learntech.platformtool.lookupservice.model.UserRolePrivilege;
import com.learntech.platformtool.lookupservice.repository.UserRolePrivilegeRepository;
import com.learntech.platformtool.lookupservice.service.UpdateUserRolePrivilegeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * UpdateUserRolePrivilegeServiceImpl
 *
 * @author Uthiraraj Saminathan
 */
@Service
public class UpdateUserRolePrivilegeServiceImpl implements UpdateUserRolePrivilegeService {

    private final UserRolePrivilegeRepository userRolePrivilegeRepository;
    //Does this allowedUserPrivilegeMapper is available in application context?
    private final Mapper<AllowedUserPrivilege, AllowedUserPrivilegeDefinition> allowedUserPrivilegeDefinitionMapper;
    private final UserRolePrivilegeHelper userRolePrivilegeHelper;

    public UpdateUserRolePrivilegeServiceImpl(UserRolePrivilegeRepository userRolePrivilegeRepository,
                                              @Qualifier("allowedUserPrivilegeDefinitionMapper") Mapper<AllowedUserPrivilege, AllowedUserPrivilegeDefinition> allowedUserPrivilegeDefinitionMapper,
                                              UserRolePrivilegeHelper userRolePrivilegeHelper) {
        this.userRolePrivilegeRepository = userRolePrivilegeRepository;
        this.allowedUserPrivilegeDefinitionMapper  = allowedUserPrivilegeDefinitionMapper;
        this.userRolePrivilegeHelper     = userRolePrivilegeHelper;
    }

    /**
     * updateUserRolePrivileges will update user role specific allowed privileges.
     *
     * If will validate given User role is available in the DB. If not throw error.
     * If available Upsert with given input values.
     * Upon Successful update return new User Role Privilege Object in response.
     *
     * @param userRoleInput
     * @return
     */
    @Override
    public UserRolePrivilege updateUserRolePrivileges(UserRoleInput userRoleInput) {

        UserRolePrivilegeDefinition userRoleDef = userRolePrivilegeRepository
                                                             .findByUserRoleId(userRoleInput.getUserRoleId())
                .orElseThrow(() -> handleError("404", "User Role ID not available"));

        Map<String,AllowedUserPrivilegeDefinition> existingAllowedUserPrivileges = Map.of();

        if(!CollectionUtils.isEmpty(userRoleDef.getAllowedUserPrivileges())){
            existingAllowedUserPrivileges =  userRoleDef.getAllowedUserPrivileges()
                                                        .stream()
                         .collect(Collectors.toMap(AllowedUserPrivilegeDefinition::getUserRoleID, Function.identity()));
        }
        List<AllowedUserPrivilege> updateUserPrivileges = userRoleInput.getAllowedUserPrivileges();

        for(AllowedUserPrivilege allowedUserPrivilege:updateUserPrivileges){
            existingAllowedUserPrivileges.put(allowedUserPrivilege.getUserRoleID(),allowedUserPrivilegeDefinitionMapper.map(allowedUserPrivilege));
        }

        List<AllowedUserPrivilegeDefinition> updateDBUserPrivileges = new ArrayList<>(existingAllowedUserPrivileges.values());

        userRoleDef.setAllowedUserPrivileges(updateDBUserPrivileges);

        UserRolePrivilegeDefinition dbUserPrivilegeDef = userRolePrivilegeRepository.save(userRoleDef);

        return userRolePrivilegeHelper.getUserRolePrivilege(dbUserPrivilegeDef);
    }


    private RuntimeException handleError(String errorCode, String errorMessage){
        return new RuntimeException(errorMessage);
    }
}
