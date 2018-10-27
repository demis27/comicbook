package org.demis27.comics.business;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.demis27.comics.data.jpa.entity.ComicBook;
import org.demis27.comics.data.jpa.entity.EntityInterface;
import org.demis27.comics.data.jpa.service.ComicBookDataService;
import org.demis27.comics.data.jpa.service.GenericDataService;
import org.demis27.comics.paging.range.Range;
import org.demis27.comics.paging.sort.SortParameterElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "comicBookBusinessService")
public class ComicBookBusinessService extends GenericBusinessService<ComicBook> {

    @Autowired
    @Qualifier("comicBookDataService")
    private ComicBookDataService comicBookDataService;

    @Override
    protected GenericDataService<ComicBook> getService() {
        return comicBookDataService;
    }
}
