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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PersonDataService extends GenericDataService<Person> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonDataService.class);

    @Resource
    private PersonRepository personRepository;

    @Override
    protected JpaRepository<Person, String> getRepository() {
        return personRepository;
    }
}
