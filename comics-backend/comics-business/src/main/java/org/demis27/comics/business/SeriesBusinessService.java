package org.demis27.comics.business;

import org.demis27.comics.data.jpa.entity.Series;
import org.demis27.comics.data.jpa.service.GenericDataService;
import org.demis27.comics.data.jpa.service.SeriesDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeriesBusinessService extends GenericBusinessService<Series> {

    @Autowired
    private SeriesDataService dataService;

    @Override
    protected GenericDataService<Series> getService() {
        return dataService;
    }
}

