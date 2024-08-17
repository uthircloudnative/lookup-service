package com.learntech.platformtool.lookupservice.mapper.impl;

import com.learntech.platformtool.lookupservice.entity.AllowedUserPrivilegeDefinition;
import com.learntech.platformtool.lookupservice.mapper.Mapper;
//import com.learntech.platformtool.lookupservice.model.types.AllowedUserPrivilege;
import com.learntech.platformtool.lookupservice.model.AllowedUserPrivilege;
import org.springframework.stereotype.Component;


/**
 * AllowedUserPrivilegeDefinitionMapper
 *
 * @author Uthiraraj Saminathan
 */
@Component("allowedUserPrivilegeDefinitionMapper")
public class AllowedUserPrivilegeDefinitionMapper implements Mapper<AllowedUserPrivilege, AllowedUserPrivilegeDefinition> {


    /**
     * @param allowedUserPrivilege
     * @return AllowedUserPrivilegeDefinition
     */
    @Override
    public AllowedUserPrivilegeDefinition map(AllowedUserPrivilege allowedUserPrivilege) {

        AllowedUserPrivilegeDefinition allowedUserPrivilegeDefinition = new AllowedUserPrivilegeDefinition();
        allowedUserPrivilegeDefinition.setUserRoleID(allowedUserPrivilege.getUserRoleID());
        allowedUserPrivilegeDefinition.setCreate(allowedUserPrivilege.getCreate());
        allowedUserPrivilegeDefinition.setUpdate(allowedUserPrivilege.getUpdate());
        allowedUserPrivilegeDefinition.setDelete(allowedUserPrivilege.getDelete());
        allowedUserPrivilegeDefinition.setRead(allowedUserPrivilege.getRead());

        return allowedUserPrivilegeDefinition;
    }
}
