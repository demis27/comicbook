package org.demis27.comics.data.jpa.service;

import java.util.List;
import java.util.UUID;

import org.demis27.comics.data.jpa.PersistenceJPAConfiguration;
import org.demis27.comics.data.jpa.entity.ComicBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextHierarchy({ @ContextConfiguration(classes = PersistenceJPAConfiguration.class) })
public class ComicBookDataServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ComicBookDataService service;

    @Test
    public void create() throws Exception {
        ComicBook comicBook = new ComicBook();
//        comicBook.setId(UUID.randomUUID().toString());
        comicBook.setTitle("Adèle et la bête");
        service.create(comicBook);

        List<ComicBook> comicBookEntities = service.findAll();
        Assert.assertNotNull(comicBookEntities);
        Assert.assertEquals(comicBookEntities.size(), 1);

        ComicBook result = comicBookEntities.get(0);
        Assert.assertEquals(result.getTitle(), comicBook.getTitle());

        service.delete(result.getId());

        comicBookEntities = service.findAll();
        Assert.assertNotNull(comicBookEntities);
        Assert.assertEquals(comicBookEntities.size(), 0);
    }
}
