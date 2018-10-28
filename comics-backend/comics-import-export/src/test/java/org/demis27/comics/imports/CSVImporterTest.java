package org.demis27.comics.imports;

import org.demis27.comics.data.jpa.PersistenceJPAConfiguration;
import org.demis27.comics.data.jpa.entity.ComicBook;
import org.demis27.comics.data.jpa.entity.Series;
import org.demis27.comics.data.jpa.service.ComicBookDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@ContextHierarchy({@ContextConfiguration(classes = PersistenceJPAConfiguration.class)})
public class CSVImporterTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private CSVImporter importer;

    @Autowired
    private ComicBookDataService service;

    @Test
    public void oneLineFile() throws Exception {
        Reader input = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("oneLine.csv")));

        List<EntityRecord> entities = importer.readAllData(input);

        Assert.assertEquals(entities.size(), 1);

        ComicBook comicBook = entities.get(0).getComicBook();
        Assert.assertEquals(comicBook.getTitle(), "Le petit soldat");
        Assert.assertEquals(comicBook.getIsbn(), "978-2-7560-9607-0");

        Series series = entities.get(0).getSeries();
        Assert.assertEquals(series.getTitle(), "14-18");
    }

    @Test
    public void sevenLinesFile() throws Exception {
        Reader input = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("sevenLines.csv")));

        List<EntityRecord> entities = importer.readAllData(input);

        Assert.assertEquals(entities.size(), 7);

        ComicBook comicBook = entities.get(0).getComicBook();
        Assert.assertEquals(comicBook.getTitle(), "Adèle et la bête");
        Assert.assertEquals(comicBook.getIsbn(), "978-2-2030-3709-0");

        Series series = entities.get(0).getSeries();
        Assert.assertEquals(series.getTitle(), "Aventures extraordinaires d'Adèle Blanc-Sec (Les)");
    }

    @Test
    public void allLinesFile() throws Exception {
        Reader input = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("allLines.csv")));

        List<EntityRecord> entities = importer.readAllData(input);

        Assert.assertEquals(entities.size(), 645);
    }

    @Test
    public void saveOneLine() throws Exception {
        Reader input = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("oneLine.csv")));

        List<EntityRecord> entities = importer.readAllData(input);
        Assert.assertEquals(entities.size(), 1);

        importer.saveRecords(entities);

        List<ComicBook> all = service.findAll();
        Assert.assertEquals(all.size(), 1);

    }

    @AfterTest
    @AfterClass
    public void deleteAll() throws Exception {
        List<ComicBook> all = service.findAll();
        for (ComicBook comicBook : all) {
            service.delete(comicBook.getId());
        }

    }
}
