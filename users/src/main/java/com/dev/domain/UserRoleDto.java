package com.dev.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 *
 * <p>
 * The Data transfert Object for account data
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDto implements Serializable {

    private String id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String confirmPassword;

    @JsonIgnore
    private String newPassword;

    private List<String> rolesId;

}
