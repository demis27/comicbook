package org.demis27.comics.api.controller;


import org.demis27.comics.business.GenericBusinessService;
import org.demis27.comics.business.PersonBusinessService;
import org.demis27.comics.business.converter.GenericConverter;
import org.demis27.comics.business.converter.PersonConverter;
import org.demis27.comics.business.dto.PersonDTO;
import org.demis27.comics.data.jpa.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/person")
@RestController
public class PersonController extends GenericController<Person, PersonDTO> {

    @Autowired
    @Qualifier("personBusinessService")
    private PersonBusinessService service;

    @Autowired
    @Qualifier("personConverter")
    private PersonConverter converter;

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    @Override
    public GenericBusinessService<Person> getBusinessService() {
        return service;
    }

    @Override
    public GenericConverter<Person, PersonDTO> getConverter() {
        return converter;
    }
}
