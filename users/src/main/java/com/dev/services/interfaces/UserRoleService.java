package com.dev.services.interfaces;

import com.dev.domain.LocalUser;
import com.dev.services.CommonService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * <p>
 * The service for managing the {@link LocalUser} entity
 */
public interface UserRoleService extends UserDetailsService, CommonService<LocalUser, String> {

    /**
     * Save new User with his roles
     *
     * @param localUser the user data
     * @param roleId    the roles lists
     */
    LocalUser saveUser(LocalUser localUser, List<String> roleId);

    /**
     * compare two password string
     *
     * @param password1
     * @param password2
     */
    boolean comparePassword(String password1, String password2);

    LocalUser fetchOneByUsername(String username);


}
