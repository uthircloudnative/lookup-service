package com.learntech.platformtool.lookupservice;

import com.learntech.platformtool.lookupservice.entity.AllowedUserPrivilegeDefinition;
import com.learntech.platformtool.lookupservice.entity.UserRoleDefinition;
import com.learntech.platformtool.lookupservice.entity.UserRolePrivilegeDefinition;
import com.learntech.platformtool.lookupservice.repository.UserRolePrivilegeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * DataLoader
 *
 * @author Uthiraraj Saminathan
 *
 */
@Component
public class DataLoader implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(DataLoader.class);
    private final UserRolePrivilegeRepository userRolePrivilegeRepository;

    public DataLoader(UserRolePrivilegeRepository userRolePrivilegeRepository) {
        this.userRolePrivilegeRepository = userRolePrivilegeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        createAdminUserRoleDefinition("ADN1","ADMIN","Admin User");
    }

    private void createAdminUserRoleDefinition(String userRoleId,String userRoleName,String desc){

        logger.info("createUserRoleDefinition() starts");
        UserRolePrivilegeDefinition userRolePrivilegeDefinition = new UserRolePrivilegeDefinition();

        UserRoleDefinition userRoleDefinition = new UserRoleDefinition();
        userRoleDefinition.setUserRoleId(userRoleId);
        userRoleDefinition.setUserRoleName(userRoleName);
        userRoleDefinition.setDescription(desc);

        userRolePrivilegeDefinition.setUserRoleDefinition(userRoleDefinition);
        userRolePrivilegeDefinition.setAllowedUserPrivileges(getAdminRoleAllowedUserPrivilege());

        logger.info("data count before load : {}", userRolePrivilegeRepository.count());
        userRolePrivilegeRepository.save(userRolePrivilegeDefinition);

        logger.info("data count after load : {}", userRolePrivilegeRepository.count());

        logger.info("createUserRoleDefinition() ends");
    }

    private List<AllowedUserPrivilegeDefinition> getAdminRoleAllowedUserPrivilege(){

        AllowedUserPrivilegeDefinition adminPrivilege = new AllowedUserPrivilegeDefinition("ADN1",false,
                                                                       false,false,false);

        AllowedUserPrivilegeDefinition pmrPrivilege = new AllowedUserPrivilegeDefinition("PMR1",true,
                                                                     true,true,true);

        AllowedUserPrivilegeDefinition edrPrivilege = new AllowedUserPrivilegeDefinition("EDR1",true,
                                                                     true,true,true);

        AllowedUserPrivilegeDefinition emrPrivilege = new AllowedUserPrivilegeDefinition("EMR1",true,
                                                                    true,true,true);

        AllowedUserPrivilegeDefinition dlePrivilege = new AllowedUserPrivilegeDefinition("DLE1",true,
                                                                     true,true,true);

        AllowedUserPrivilegeDefinition tlePrivilege = new AllowedUserPrivilegeDefinition("TLE1",true,
                                                                   true,true,true);

        AllowedUserPrivilegeDefinition derPrivilege = new AllowedUserPrivilegeDefinition("DER1",true,
                                                                    true,true,true);

        AllowedUserPrivilegeDefinition terPrivilege = new AllowedUserPrivilegeDefinition("TER1",true,
                                                                    true,true,true);

        AllowedUserPrivilegeDefinition perPrivilege = new AllowedUserPrivilegeDefinition("PER1",true,
                                                                     true,true,true);

        AllowedUserPrivilegeDefinition doePrivilege = new AllowedUserPrivilegeDefinition("DOE1",true,
                                                                     true,true,true);

        AllowedUserPrivilegeDefinition rmrPrivilege = new AllowedUserPrivilegeDefinition("RMR1",true,
                                                                     true,true,true);

       return List.of(adminPrivilege,pmrPrivilege,edrPrivilege,emrPrivilege,dlePrivilege,tlePrivilege,
                     derPrivilege,terPrivilege,perPrivilege,doePrivilege,rmrPrivilege);
    }
}
