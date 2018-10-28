package org.demis27.comics.data.jpa.service;

import org.demis27.comics.data.jpa.PersistenceJPAConfiguration;
import org.demis27.comics.data.jpa.entity.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@ContextHierarchy({@ContextConfiguration(classes = PersistenceJPAConfiguration.class)})
public class SeriesDataServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SeriesDataService service;

    @Test
    public void create() throws Exception {
        Series series = new Series();
        series.setTitle("Adèle Blanc-sec");

        service.create(series);

        List<Series> seriesEntities = service.findAll();
        Assert.assertNotNull(seriesEntities);
        Assert.assertEquals(seriesEntities.size(), 1);

        Series result = seriesEntities.get(0);
        Assert.assertNotNull(result.getId());
        Assert.assertNotNull(result.getCreated());
        Assert.assertNotNull(result.getUpdated());
        Assert.assertEquals(result.getTitle(), series.getTitle());

        service.delete(result.getId());

        seriesEntities = service.findAll();
        Assert.assertNotNull(seriesEntities);
        Assert.assertEquals(seriesEntities.size(), 0);

    }
}
