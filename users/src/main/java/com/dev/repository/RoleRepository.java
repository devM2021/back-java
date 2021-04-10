package com.dev.repository;


import com.dev.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The Role Repository base class
 */
@Repository
public interface RoleRepository extends JpaRepository<Roles, String> {

    /**
     * Fetch one role from db by the provided name
     *
     * @param name
     */
    Optional<Roles> findByName(String name);
}
