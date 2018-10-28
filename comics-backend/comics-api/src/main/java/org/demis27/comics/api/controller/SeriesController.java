package org.demis27.comics.api.controller;

import org.demis27.comics.business.GenericBusinessService;
import org.demis27.comics.business.SeriesBusinessService;
import org.demis27.comics.business.converter.GenericConverter;
import org.demis27.comics.business.converter.SeriesConverter;
import org.demis27.comics.business.dto.SeriesDTO;
import org.demis27.comics.data.jpa.entity.Series;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/series")
@RestController
public class SeriesController extends GenericController<Series, SeriesDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeriesController.class);

    @Autowired
    private SeriesBusinessService service;

    @Autowired
    private SeriesConverter converter;


    @Override
    public GenericBusinessService<Series> getBusinessService() {
        return service;
    }

    @Override
    public GenericConverter<Series, SeriesDTO> getConverter() {
        return converter;
    }

}
