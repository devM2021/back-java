package com.dev.services;

import com.dev.domain.Roles;
import com.dev.exeptions.AppResponseEntityException;
import com.dev.repository.RoleRepository;
import com.dev.services.interfaces.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The implementation  of {@link RoleService}
 */
@Service
public class RoleServiceImpl extends AbstractCommonServiceImpl<Roles, String, RoleRepository> implements RoleService {

    private final RoleRepository roleRepository;

    protected RoleServiceImpl(RoleRepository roleRepository) {
        super("Role");
        this.roleRepository = roleRepository;
    }

    @Override
    public Roles fetchOneByName(String roleName) {
        if (roleName.isEmpty()) throw new IllegalArgumentException("Role name cannot be null");
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
    }

    @Override
    public List<Roles> fetchAllByIds(List<String> roleIds) {
        return repository().findAllById(roleIds);
    }

    @Override
    public RoleRepository repository() {
        return roleRepository;
    }

    @Override
    public Roles validate(Roles entity) {
        if (entity.getName() == null || entity.getName().isEmpty())
            throw new AppResponseEntityException("Role name cannot be null");
        return entity;
    }
}
