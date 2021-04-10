package com.dev.services;

import com.dev.core.GeneratorUtils;
import com.dev.domain.AbstractEntity;
import com.dev.exeptions.AppResponseEntityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;

/**
 * Default Implementation for {@link CommonService}
 */
@Slf4j
@Service
public abstract class AbstractCommonServiceImpl<E extends AbstractEntity<ID>, ID extends Serializable,
        R extends JpaRepository<E, ID>> implements CommonService<E, ID> {
    /**
     * the managed entity name
     */
    private final String targetEntity;

    /**
     * The repository must be redefined for each reel implementation
     */
    public abstract R repository();

    protected AbstractCommonServiceImpl(String targetEntity) {
        this.targetEntity = targetEntity;
    }

    @Override
    public E createOrUpdate(E entity) {
        log.info("[Saving] data of {} ", targetEntity);
        try {
            E entityValidate = validate(entity);
            if (entity.getId() == null) {
                entity.setId((ID) GeneratorUtils.uuid());
            }
            return repository().save(entityValidate);
        } catch (Exception e) {
            throw new AppResponseEntityException(e.getMessage());
        }
    }

    @Override
    public E fetchOneRecordById(ID id) {
        Assert.notNull(id, "Id cannot be null");
        log.info("[Fetch One] data of {} ", targetEntity);
        return repository().findById(id).orElseThrow(() -> new AppResponseEntityException("RECORD NOT FOUND"));
    }

    @Override
    public void removeRecordById(ID id, boolean softDelete) {
        log.info("[removing] data of {} ", targetEntity);
        Assert.notNull(id, "Id cannot be null");
        E entity = this.fetchOneRecordById(id);
        if (softDelete) {
            entity.setDeleted(true);
            createOrUpdate(entity);
        } else {
            repository().deleteById(id);
        }
    }

    @Override
    public List<E> fetchAllRecord() {
        log.info("[Fetching all] data of {} ", targetEntity);
        return repository().findAll();
    }

    @Override
    public Page<E> fetchAllByPage(Pageable pageable) {
        log.info("[Fetching all by page] data of {} ", targetEntity);
        return repository().findAll(pageable);
    }

}
