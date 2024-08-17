package com.learntech.platformtool.lookupservice.datafetcher;

import com.learntech.platformtool.lookupservice.model.UserRole;
import com.learntech.platformtool.lookupservice.model.UserRoleInput;
import com.learntech.platformtool.lookupservice.model.UserRolePrivilege;
import com.learntech.platformtool.lookupservice.service.*;
import com.netflix.graphql.dgs.*;

import java.util.List;

/**
 * UserRolePrivilegeDataFetcher
 *
 * @author Uthiraraj Saminathan
 */
@DgsComponent
public class UserRolePrivilegeDataFetcher {

    private final CreateUserRolePrivilegeService createUserRolePrivilegeService;
    private final ReadUserRolePrivilegeService readUserRolePrivilegeService;
    private final UpdateUserRolePrivilegeService updateUserRolePrivilegeService;
    private final DeleteUserRolePrivilegeService deleteUserRolePrivilegeService;


    public UserRolePrivilegeDataFetcher(CreateUserRolePrivilegeService createUserRolePrivilegeService,
                                        ReadUserRolePrivilegeService readUserRolePrivilegeService,
                                        UpdateUserRolePrivilegeService updateUserRolePrivilegeService,
                                        DeleteUserRolePrivilegeService deleteUserRolePrivilegeService) {
        this.createUserRolePrivilegeService = createUserRolePrivilegeService;
        this.readUserRolePrivilegeService = readUserRolePrivilegeService;
        this.updateUserRolePrivilegeService = updateUserRolePrivilegeService;
        this.deleteUserRolePrivilegeService = deleteUserRolePrivilegeService;
    }

    @DgsData(parentType = "Query", field = "allUserRoles")
    public List<UserRole> allUserRoles(){
      return  readUserRolePrivilegeService.getAllUserRoles();
    }

    @DgsQuery
    public UserRolePrivilege userRolePrivileges(@InputArgument(name = "userRoleId")String userRoleId){
        return  readUserRolePrivilegeService.getUserRolePrivileges(userRoleId);
    }

    @DgsMutation
    public UserRolePrivilege createUserRolePrivileges(@InputArgument(name = "userRoleInput") UserRoleInput userRoleInput){
        return createUserRolePrivilegeService.createUserRole(userRoleInput);
    }

    @DgsMutation
    public UserRolePrivilege updateUserRolePrivileges(@InputArgument(name = "userRoleInput") UserRoleInput userRoleInput){
        return updateUserRolePrivilegeService.updateUserRolePrivileges(userRoleInput);
    }

    @DgsMutation
    public Boolean deleteUserRolePrivileges(@InputArgument(name = "userRoleId") String userRoleId){
        return deleteUserRolePrivilegeService.deleteUserRolePrivileges(userRoleId);
    }

}
