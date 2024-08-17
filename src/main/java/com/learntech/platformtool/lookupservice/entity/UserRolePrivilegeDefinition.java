package com.learntech.platformtool.lookupservice.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
/**
 * UserRolePrivilegeDefinition
 *
 * @author Uthiraraj Saminathan
 */
@Document(collection = "UserRolePrivilegeDefinitions")
@Getter
@Setter
@ToString
public class UserRolePrivilegeDefinition {

    @MongoId
    private ObjectId id;
    private UserRoleDefinition userRoleDefinition;
    private List<AllowedUserPrivilegeDefinition> allowedUserPrivileges;

}
