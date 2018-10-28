package org.demis27.comics.data.jpa.service;

import org.demis27.comics.data.jpa.entity.Series;
import org.demis27.comics.data.jpa.repository.SeriesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SeriesDataService extends GenericDataService<Series> {

    @Resource
    private SeriesRepository repository;

    @Override
    protected JpaRepository<Series, String> getRepository() {
        return repository;
    }
}
