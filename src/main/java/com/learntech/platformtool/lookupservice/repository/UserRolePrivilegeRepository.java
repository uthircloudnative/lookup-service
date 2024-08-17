package com.learntech.platformtool.lookupservice.repository;

import com.learntech.platformtool.lookupservice.entity.UserRolePrivilegeDefinition;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRolePrivilegeRepository
 *
 * @author Uthiraraj Saminathan
 */
@Repository
public interface UserRolePrivilegeRepository extends MongoRepository<UserRolePrivilegeDefinition, ObjectId>,
                                                       UserRolePrivilegeRepositoryCustom {

}
