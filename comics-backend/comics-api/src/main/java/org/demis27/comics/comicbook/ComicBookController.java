package org.demis27.comics.comicbook;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.demis27.comics.business.BusinessService;
import org.demis27.comics.business.ComicBookBusinessService;
import org.demis27.comics.business.GenericBusinessService;
import org.demis27.comics.business.converter.ComicBookConverter;
import org.demis27.comics.business.converter.GenericConverter;
import org.demis27.comics.business.dto.ComicBookDTO;
import org.demis27.comics.data.jpa.entity.ComicBook;
import org.demis27.comics.paging.range.Range;
import org.demis27.comics.paging.range.RangeException;
import org.demis27.comics.paging.sort.SortParameterElement;
import org.demis27.comics.web.ControllerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/comic-book")
@RestController
public class ComicBookController extends GenericController<ComicBook, ComicBookDTO> {

    @Autowired
    @Qualifier("comicBookBusinessService")
    private ComicBookBusinessService service;

    @Autowired
    @Qualifier("comicBookConverter")
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
