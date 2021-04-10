package com.dev.services;

import com.dev.domain.LocalUser;
import com.dev.domain.LocalUserDetails;
import com.dev.domain.Roles;
import com.dev.exeptions.AppResponseEntityException;
import com.dev.repository.UserRepository;
import com.dev.services.interfaces.RoleService;
import com.dev.services.interfaces.UserRoleService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * The implementation  of {@link UserRoleService}
 */
@Service
@Qualifier("userService")
public class UserDetailsServiceImpl extends AbstractCommonServiceImpl<LocalUser, String, UserRepository> implements UserRoleService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    public UserDetailsServiceImpl(UserRepository userRepository, RoleService roleService) {
        super("Local User");
        this.userRepository = userRepository;
        this.roleService = roleService;
    }


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        Optional<LocalUser> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return new LocalUserDetails(user.get());
        } else {
            throw new AppResponseEntityException(String.format("Cannot find the user with username  %s", username));
        }
    }

    @Override
    @Transactional
    public LocalUser saveUser(LocalUser localUser, List<String> roleId) {
        if (roleId != null && !roleId.isEmpty()) {
            List<Roles> rolesList = roleService.fetchAllByIds(roleId);
            checkBoolean(rolesList.isEmpty(), "No role found with these selected ids");
            localUser.getRoles().addAll(rolesList);
        }
        localUser.setPassword(new BCryptPasswordEncoder().encode(localUser.getPassword()));
        return this.createOrUpdate(localUser);
    }

    private void checkBoolean(boolean b, String s) {
        if (b) throw new AppResponseEntityException(s);
    }

    @Override
    public boolean comparePassword(String password1, String password2) {
        checkBoolean(!password1.equals(password2), "Password not match");
        return true;
    }

    @Override
    public LocalUser fetchOneByUsername(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(() -> new AppResponseEntityException("User not found "));
    }

    @Override
    public UserRepository repository() {
        return userRepository;
    }

    @Override
    public LocalUser validate(LocalUser entity) {
        if (entity.getUsername() == null || entity.getUsername().isEmpty())
            throw new AppResponseEntityException("Username cannot be null");
        return entity;
    }
}
