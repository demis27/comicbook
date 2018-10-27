package org.demis27.comics.business;

import org.demis27.comics.data.jpa.entity.Person;
import org.demis27.comics.data.jpa.service.GenericDataService;
import org.demis27.comics.data.jpa.service.PersonDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value = "personBusinessService")
public class PersonBusinessService extends GenericBusinessService<Person> {

    @Autowired
    @Qualifier("personDataService")
    private PersonDataService personDataService;

    @Override
    protected GenericDataService<Person> getService() {
        return personDataService;
    }
}

