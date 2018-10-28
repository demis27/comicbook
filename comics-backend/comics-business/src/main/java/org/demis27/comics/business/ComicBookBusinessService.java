package org.demis27.comics.business;

import org.demis27.comics.data.jpa.entity.ComicBook;
import org.demis27.comics.data.jpa.service.ComicBookDataService;
import org.demis27.comics.data.jpa.service.GenericDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComicBookBusinessService extends GenericBusinessService<ComicBook> {

    @Autowired
    private ComicBookDataService comicBookDataService;

    @Override
    protected GenericDataService<ComicBook> getService() {
        return comicBookDataService;
    }
}
