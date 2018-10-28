package org.demis27.comics.data.jpa.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

import org.demis27.comics.data.jpa.entity.ComicBook;
import org.demis27.comics.data.jpa.repository.ComicBookRepository;
import org.demis27.comics.paging.range.Range;
import org.demis27.comics.paging.sort.SortParameterElement;
import org.demis27.comics.paging.sort.SortParameterElementConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComicBookDataService extends GenericDataService<ComicBook> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComicBookDataService.class);

    @Resource
    private ComicBookRepository comicBookRepository;

    @Override
    protected JpaRepository<ComicBook, String> getRepository() {
        return comicBookRepository;
    }
}
