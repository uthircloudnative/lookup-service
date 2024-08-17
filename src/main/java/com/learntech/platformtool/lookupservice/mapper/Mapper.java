package com.learntech.platformtool.lookupservice.mapper;

/**
 * AllowedUserPrivilegeMapper
 *
 * @author Uthiraraj Saminathan
 */
public interface Mapper<S, T> {

    T map(S source);

}
