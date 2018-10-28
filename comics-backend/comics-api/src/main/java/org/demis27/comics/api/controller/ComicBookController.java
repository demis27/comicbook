package org.demis27.comics.api.controller;

import org.demis27.comics.business.ComicBookBusinessService;
import org.demis27.comics.business.GenericBusinessService;
import org.demis27.comics.business.converter.ComicBookConverter;
import org.demis27.comics.business.converter.GenericConverter;
import org.demis27.comics.business.dto.ComicBookDTO;
import org.demis27.comics.data.jpa.entity.ComicBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("/comic-book")
@RestController
public class ComicBookController extends GenericController<ComicBook, ComicBookDTO> {

    @Autowired
    private ComicBookBusinessService service;

    @Autowired
    private ComicBookConverter converter;

    private static final Logger LOGGER = LoggerFactory.getLogger(ComicBookController.class);

    @Override
    public GenericBusinessService<ComicBook> getBusinessService() {
        return service;
    }

    @Override
    public GenericConverter<ComicBook, ComicBookDTO> getConverter() {
        return converter;
    }

    @ResponseBody
    @RequestMapping(value = {"/{id}"},
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"},
            consumes = "application/hal+json"
    )
    public HttpEntity<Resource> getComicBookHal(@PathVariable(value = "id") String id, HttpServletResponse httpResponse) {
        ComicBook incomingEmail = service.findById(id);
        if (incomingEmail != null) {
            httpResponse.setStatus(HttpStatus.OK.value());
            httpResponse.setDateHeader(HttpHeaders.LAST_MODIFIED, incomingEmail.getUpdated().getTime());
            ComicBookDTO dto = converter.convert(incomingEmail);
            Resource resource = new Resource(dto, ControllerLinkBuilder.linkTo(ComicBookController.class) //
                    .slash(dto.getId()).withSelfRel());

            return new HttpEntity<>(resource);
        } else {
            httpResponse.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }
    }
}
