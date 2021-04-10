package com.dev.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The entity base class
 *
 * @param <ID> the id type parameter
 *
 */

@Data
@MappedSuperclass
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {
    /**
     * The ID of the target entity
     */
    @Id
    public ID id;

    /**
     * The created date of the record
     */
    @Column(name = "last_update")
    protected LocalDateTime lastUpdate = LocalDateTime.now();

    /**
     * Indicate if the record id deleted or not
     */
    @Column(name = "deleted")
    protected boolean deleted = false;

    /**
     * The current connected user
     */
    private String connectedUser = "System";


}
