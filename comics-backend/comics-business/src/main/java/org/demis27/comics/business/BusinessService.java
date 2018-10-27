package org.demis27.comics.business;

import org.demis27.comics.data.jpa.entity.EntityInterface;
import org.demis27.comics.paging.range.Range;
import org.demis27.comics.paging.sort.SortParameterElement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface BusinessService {

    @Transactional
    EntityInterface create(EntityInterface created) throws ExecutionException, InterruptedException;

    @Transactional
    EntityInterface delete(String id);

    @Transactional
    List<EntityInterface> findAll();

    @Transactional
    EntityInterface findById(String id);

    @Transactional
    EntityInterface update(EntityInterface updated) throws ExecutionException, InterruptedException;

    List<EntityInterface> findPart(Range range, List<SortParameterElement> sorts);

    List<EntityInterface> searchEverywhere(String value, Range range, List<SortParameterElement> sorts)
            throws ExecutionException, InterruptedException;
}
