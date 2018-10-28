package org.demis27.comics.imports;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.demis27.comics.data.jpa.entity.ComicBook;
import org.demis27.comics.data.jpa.entity.Series;
import org.demis27.comics.data.jpa.service.ComicBookDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVImporter {

    @Autowired
    private ComicBookDataService comicBookDataService;

    public static final String[] HEADERS = {"Série", "Tome", "Titre", "Éditeur", "Date de parution", "Dépôt légal", "ISBN", "Prix", "Edition originale ?", "Dédicace ?", "Perso1", "Perso2", "Perso3", "Commentaire"};

    public List<EntityRecord> readAllData(Reader input) throws IOException {

        List<EntityRecord> entities = new ArrayList<>();


        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().withDelimiter(';').withHeader(HEADERS).parse(input);
        for (CSVRecord record : records) {
            if (record.get("Titre") == null) {
                break;
            }
            EntityRecord entityRecord = new EntityRecord();
            entityRecord.setRecordId(record.getRecordNumber());
            entities.add(entityRecord);
            // Read comicBook
            ComicBook book = new ComicBook();
            book.setTitle(record.get("Titre"));
            book.setIsbn(record.get("ISBN"));
            entityRecord.setComicBook(book);
            // Read Series
            if (record.get("Série") != null) {
                Series series = new Series();
                series.setTitle(record.get("Série"));
                entityRecord.setSeries(series);
            }
        }
        return entities;
    }

    public void saveRecords(List<EntityRecord> records) {
        records.stream().forEach(record -> comicBookDataService.create(record.getComicBook()));
    }
}
