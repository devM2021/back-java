package com.dev.services;

import com.dev.domain.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * Commons methods for managing an entity
 */
public interface CommonService<E extends AbstractEntity<ID>, ID extends Serializable> {

    /**
     * Save the object  sent from client
     *
     * @param entity object to save
     * @return the object saved
     * @throws com.dev.exeptions.AppResponseEntityException an exception is thrown if something wrong goes here
     */
    E createOrUpdate(E entity);

    /**
     * Fecth one Object type of T
     *
     * @param id the id of object T to find
     * @return the Object found with the specified parameter
     */
    E fetchOneRecordById(ID id);

    /**
     * Excecute an update of T instance to avoid a physical delete
     *
     * @param id         the id of object to delete
     * @param softDelete to tell that the data is to be update or to be delete
     *                   if the value is true, then the physical delete will apply
     */
    void removeRecordById(ID id, boolean softDelete);

    /**
     * Fetch all records without criteria
     */
    List<E> fetchAllRecord();

    /**
     * Fetch all records without criteria page per page
     */
    Page<E> fetchAllByPage(Pageable pageable);

    /**
     * Validate an entity before saving
     *
     * @param entity the entity to validate
     */
    E validate(E entity);
}
