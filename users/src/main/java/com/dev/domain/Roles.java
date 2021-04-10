package com.dev.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * THe Account Role entity *
 *
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@ToString(exclude = "localUsers")
@Builder
@AllArgsConstructor
public class Roles extends AbstractEntity<String> {

    /**
     * The role name
     * */
    @Column(unique = true)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<LocalUser> localUsers = new HashSet<>();

    public Roles(String name) {
        this.name = name;
    }
}
