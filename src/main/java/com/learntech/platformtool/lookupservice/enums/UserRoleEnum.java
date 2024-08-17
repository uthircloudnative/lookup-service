package com.learntech.platformtool.lookupservice.enums;

/**
 * UserRoleEnum
 *
 * @author Uthiraraj Saminathan
 */
public enum UserRoleEnum {

    ADN1("ADMIN");

    private String userRoleName;

    private UserRoleEnum(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

}
