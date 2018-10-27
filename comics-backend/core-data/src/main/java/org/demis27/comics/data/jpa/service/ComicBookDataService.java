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
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "comicBookDataService")
public class ComicBookDataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComicBookDataService.class);

    @Resource(name = "comicBookRepository")
    private ComicBookRepository comicBookRepository;

    @Transactional
    public ComicBook create(ComicBook created) {
        return comicBookRepository.save(created);
    }

    @Transactional
    public ComicBook delete(String id) throws EntityNotFoundException {
        Optional<ComicBook> deleted = comicBookRepository.findById(id);

        if (!deleted.isPresent()) {
            LOGGER.debug("No model found with id: {}", id);
            throw new EntityNotFoundException();
        }
        comicBookRepository.delete(deleted.get());
        return deleted.get();
    }

    @Transactional(readOnly = true)
    public List<ComicBook> findAll() {
        return comicBookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ComicBook findById(String id) {
        return comicBookRepository.findById(id).get();
    }

    @Transactional
    public ComicBook update(ComicBook updated) throws EntityNotFoundException {
        Optional<ComicBook> founded = comicBookRepository.findById(updated.getId());

        if (!founded.isPresent()) {
            LOGGER.debug("No model found with id: {}", updated.getId());
            throw new EntityNotFoundException();
        } else {
            return comicBookRepository.save(updated);
        }
    }

    @Transactional(readOnly = true)
    public List<ComicBook> findPart(Range range, List<SortParameterElement> sorts) {
        return comicBookRepository.findAll();
//                .findAll(
//                        new PageRequest(range.getPage(), range.getSize(), SortParameterElementConverter.convert(sorts)))
//                .getContent();
    }
}
