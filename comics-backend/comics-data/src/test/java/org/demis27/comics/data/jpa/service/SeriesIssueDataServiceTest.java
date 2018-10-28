package org.demis27.comics.data.jpa.service;

import org.demis27.comics.data.jpa.PersistenceJPAConfiguration;
import org.demis27.comics.data.jpa.entity.Series;
import org.demis27.comics.data.jpa.entity.SeriesIssue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@ContextHierarchy({@ContextConfiguration(classes = PersistenceJPAConfiguration.class)})
public class SeriesIssueDataServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SeriesIssueDataService service;

    @Test
    public void create() throws Exception {
        SeriesIssue seriesIssue = new SeriesIssue();
        seriesIssue.setNumber(1);
        seriesIssue.setLabel("1");

        service.create(seriesIssue);

        List<SeriesIssue> seriesIssues = service.findAll();
        Assert.assertNotNull(seriesIssues);
        Assert.assertEquals(seriesIssues.size(), 1);

        SeriesIssue result = seriesIssues.get(0);
        Assert.assertNotNull(result.getId());
        Assert.assertNotNull(result.getCreated());
        Assert.assertNotNull(result.getUpdated());
        Assert.assertEquals(result.getNumber(), seriesIssue.getNumber());
        Assert.assertEquals(result.getLabel(), seriesIssue.getLabel());

    }
}
