package com.dev.services.interfaces;


import com.dev.domain.Roles;
import com.dev.services.CommonService;

import java.util.List;


/**
 * The service for managing the {@link Roles} entity
 */
public interface RoleService extends CommonService<Roles, String> {
    /**
     * Fetch one role by name
     */
    Roles fetchOneByName(String roleName);

    /**
     * Fetch all roles with given ids
     */
    List<Roles> fetchAllByIds(List<String> roleIds);
}
