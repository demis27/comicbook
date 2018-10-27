package org.demis27.comics.data.jpa.service;

import org.demis27.comics.data.jpa.entity.Person;
import org.demis27.comics.data.jpa.repository.PersonRepository;
import org.demis27.comics.paging.range.Range;
import org.demis27.comics.paging.sort.SortParameterElement;
import org.demis27.comics.paging.sort.SortParameterElementConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service(value = "personDataService")
public class PersonDataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonDataService.class);

    @Resource(name = "personRepository")
    private PersonRepository personRepository;

    @Autowired
    private DataServiceHelper helper;

    @Transactional
    public Person create(Person created) {
        return personRepository.save(created);
    }

    @Transactional
    public Person delete(String id) throws EntityNotFoundException {
        Optional<Person> deleted = personRepository.findById(id);

        if (!deleted.isPresent()) {
            LOGGER.debug("No model found with id: {}", id);
            throw new EntityNotFoundException();
        }
        personRepository.delete(deleted.get());
        return deleted.get();
    }

    @Transactional(readOnly = true)
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Person findById(String id) {
        return personRepository.findById(id).get();
    }

    @Transactional
    public Person update(Person updated) throws EntityNotFoundException {
        Optional<Person> founded = personRepository.findById(updated.getId());

        if (!founded.isPresent()) {
            LOGGER.debug("No model found with id: {}", updated.getId());
            throw new EntityNotFoundException();
        } else {
            return personRepository.save(updated);
        }
    }

    @Transactional(readOnly = true)
    public List<Person> findPart(Range range, List<SortParameterElement> sorts) {
        return personRepository.findAll(helper.getPageRequest(range, sorts)).getContent();
    }
}
