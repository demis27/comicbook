package org.demis27.comics.data.jpa.service;

import org.demis27.comics.data.jpa.entity.ComicBook;
import org.demis27.comics.data.jpa.entity.EntityInterface;
import org.demis27.comics.paging.range.Range;
import org.demis27.comics.paging.sort.SortParameterElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

public abstract class GenericDataService<Entity extends EntityInterface> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericDataService.class);

    @Autowired
    protected DataServiceHelper helper;

    @Transactional
    public Entity create(Entity created) {
        return getRepository().save(created);
    }

    @Transactional
    public Entity delete(String id) throws EntityNotFoundException {
        Optional<Entity> deleted = getRepository().findById(id);

        if (!deleted.isPresent()) {
            LOGGER.debug("No model found with id: {}", id);
            throw new EntityNotFoundException();
        }
        getRepository().delete(deleted.get());
        return deleted.get();
    }

    @Transactional(readOnly = true)
    public List<Entity> findAll() {
        return getRepository().findAll();
    }

    @Transactional(readOnly = true)
    public Entity findById(String id) {
        return getRepository().findById(id).get();
    }

    @Transactional
    public Entity update(Entity updated) throws EntityNotFoundException {
        Optional<Entity> founded = getRepository().findById(updated.getId());

        if (!founded.isPresent()) {
            LOGGER.debug("No model found with id: {}", updated.getId());
            throw new EntityNotFoundException();
        } else {
            return getRepository().save(updated);
        }
    }

    @Transactional(readOnly = true)
    public List<Entity> findPart(Range range, List<SortParameterElement> sorts) {
        return getRepository().findAll(helper.getPageRequest(range, sorts)).getContent();
    }

    protected abstract JpaRepository<Entity, String> getRepository();

}
