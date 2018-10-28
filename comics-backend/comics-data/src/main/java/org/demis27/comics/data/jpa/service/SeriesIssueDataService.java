package org.demis27.comics.data.jpa.service;

import org.demis27.comics.data.jpa.entity.SeriesIssue;
import org.demis27.comics.data.jpa.repository.SeriesIssueRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SeriesIssueDataService extends GenericDataService<SeriesIssue> {

    @Resource
    private SeriesIssueRepository repository;

    @Override
    protected JpaRepository<SeriesIssue, String> getRepository() {
        return repository;
    }
}
