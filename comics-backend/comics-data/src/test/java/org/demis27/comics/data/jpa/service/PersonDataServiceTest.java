package org.demis27.comics.data.jpa.service;

import org.demis27.comics.data.jpa.PersistenceJPAConfiguration;
import org.demis27.comics.data.jpa.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@ContextHierarchy({ @ContextConfiguration(classes = PersistenceJPAConfiguration.class) })
public class PersonDataServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private PersonDataService service;

    @Test
    public void create() throws Exception {
        Person person = new Person();
        person.setFirstname("Jacques");
        person.setLastname("Tardi");
        service.create(person);

        List<Person> personEntities = service.findAll();
        Assert.assertNotNull(personEntities);
        Assert.assertEquals(personEntities.size(), 1);

        Person result = personEntities.get(0);
        Assert.assertEquals(result.getFirstname(), person.getFirstname());
        Assert.assertEquals(result.getLastname(), person.getLastname());
        Assert.assertEquals(result.getMiddlename(), person.getMiddlename());

        service.delete(result.getId());

        personEntities = service.findAll();
        Assert.assertNotNull(personEntities);
        Assert.assertEquals(personEntities.size(), 0);
    }
}
