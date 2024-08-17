package com.learntech.platformtool.lookupservice.repository;

import com.learntech.platformtool.lookupservice.entity.UserRolePrivilegeDefinition;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * UserRolePrivilegeRepositoryImpl
 *
 * @author Uthiraraj Saminathan
 * 
 */
@Component
public class UserRolePrivilegeRepositoryImpl implements UserRolePrivilegeRepositoryCustom{

    private final MongoTemplate mongoTemplate;

    private static  final String USER_ROLE_DEF_COLLECTION = "UserRolePrivilegeDefinitions";

    public UserRolePrivilegeRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<UserRolePrivilegeDefinition> findAllUserRoles() {

        final Query userRoleQuery = new Query();
        userRoleQuery.fields().include("userRoleDefinition");

        List<UserRolePrivilegeDefinition> userRoles = mongoTemplate.find(userRoleQuery, UserRolePrivilegeDefinition.class,USER_ROLE_DEF_COLLECTION);

        return userRoles;
    }


    @Override
    public Optional<UserRolePrivilegeDefinition> findByUserRoleId(String userRoleId) {

        final Criteria roleIdCriteria = Criteria.where("userRoleDefinition.userRoleId")
                                                .is(userRoleId);
        final Query query = new Query(roleIdCriteria);
        UserRolePrivilegeDefinition userRolePrivilegeDefinition = mongoTemplate.findOne(query,UserRolePrivilegeDefinition.class);

        return Optional.ofNullable(userRolePrivilegeDefinition);
    }


}
