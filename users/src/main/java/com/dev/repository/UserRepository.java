package com.dev.repository;

import com.dev.domain.LocalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * The User Repository base class
 */
@Repository
public interface UserRepository extends JpaRepository<LocalUser, String> {

    /**
     * Fetch one user by the provided username with all his roles
     *
     * @param username
     */
    @Query("select  u from LocalUser  u left join fetch  u.roles " +
            " where u.username =:username")
    Optional<LocalUser> findByUsername(@Param("username") String username);
}
