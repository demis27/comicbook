package org.demis27.comics.business;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.demis27.comics.data.jpa.entity.ComicBook;
import org.demis27.comics.data.jpa.service.ComicBookDataService;
import org.demis27.comics.paging.range.Range;
import org.demis27.comics.paging.sort.SortParameterElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "comicBookBusinessService")
public class ComicBookBusinessService {

    @Autowired
    @Qualifier("comicBookJPAService")
    private ComicBookDataService comicBookDataService;

    @Transactional
    public ComicBook create(ComicBook created) throws ExecutionException, InterruptedException {
        ComicBook entity = comicBookDataService.create(created);
        // comicBookSearchService.create(entity);
        return entity;
    }

    @Transactional
    public ComicBook delete(String id) {
        // comicBookSearchService.delete(id);
        return comicBookDataService.delete(id);
    }

    @Transactional
    public List<ComicBook> findAll() {
        return comicBookDataService.findAll();
    }

    @Transactional
    public ComicBook findById(String id) {
        return comicBookDataService.findById(id);
    }

    @Transactional
    public ComicBook update(ComicBook updated) throws ExecutionException, InterruptedException {
        ComicBook entity = comicBookDataService.update(updated);
        // comicBookSearchService.update(entity);
        return entity;
    }

    public List<ComicBook> findPart(Range range, List<SortParameterElement> sorts) {
        return comicBookDataService.findPart(range, sorts);
    }

    public List<ComicBook> searchEverywhere(String value, Range range, List<SortParameterElement> sorts)
            throws ExecutionException, InterruptedException {
        // List<Long> ids = comicBookSearchService.searchEverywhere(value, range, sorts);
        // List<ComicBook> result = new ArrayList<>(ids.size());
        //
        // for (Long id: ids) {
        // ComicBook entity = comicBookJPAService.findById(id);
        // if (entity != null) {comicBookDataService
        // result.add(entity);
        // }
        // }
        //
        // return result;
        return null;
    }
}
