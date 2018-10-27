package org.demis27.comics.business;

import org.demis27.comics.data.jpa.entity.EntityInterface;
import org.demis27.comics.data.jpa.service.GenericDataService;
import org.demis27.comics.paging.range.Range;
import org.demis27.comics.paging.sort.SortParameterElement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutionException;

public abstract class GenericBusinessService<Entity extends EntityInterface> {

    @Transactional
    public Entity create(Entity created) throws ExecutionException, InterruptedException {
        return getService().create(created);
    }

    @Transactional
    public Entity delete(String id) {
        return getService().delete(id);
    }

    @Transactional
    public List<Entity> findAll() {
        return getService().findAll();
    }

    @Transactional
    public Entity findById(String id) {
        return getService().findById(id);
    }

    @Transactional
    public Entity update(Entity updated) throws ExecutionException, InterruptedException {
        return getService().update(updated);
    }

    @Transactional
    public List<Entity> findPart(Range range, List<SortParameterElement> sorts) {
        return getService().findPart(range, sorts);
    }

    public List<Entity> searchEverywhere(String value, Range range, List<SortParameterElement> sorts)
            throws ExecutionException, InterruptedException {
        // List<Long> ids = comicBookSearchService.searchEverywhere(value, range, sorts);
        // List<Entity> result = new ArrayList<>(ids.size());
        //
        // for (Long id: ids) {
        // Entity entity = comicBookJPAService.findById(id);
        // if (entity != null) {comicBookDataService
        // result.add(entity);
        // }
        // }
        //
        // return result;
        return null;
    }

    protected abstract GenericDataService<Entity> getService();
}
