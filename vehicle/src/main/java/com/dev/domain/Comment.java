package com.dev.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

/**
 * Vehicle base entity
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends AbstractEntity<String> {

    /**
     * The comment content
     */
    private String content;

    /**
     * Comment date creation
     */
    private LocalDate commentDate = LocalDate.now();

    /**
     * The target {@link Vehicle} for the current comment
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Vehicle vehicle;

    /**
     * {@link LocalUser} writing the comment if connected otherwise null
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private LocalUser localUser;

}
