package com.dev.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Vehicle base entity
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"comments"})
public class Vehicle extends AbstractEntity<String> {

    private String name;

    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vehicle")
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();
}
