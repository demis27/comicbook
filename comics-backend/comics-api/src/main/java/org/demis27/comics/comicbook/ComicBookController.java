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

    @Autowired
    private ControllerHelper helper;

    private static final Logger LOGGER = LoggerFactory.getLogger(ComicBookController.class);

    // ------------------------------------------------------------------------
    // GET
    // ------------------------------------------------------------------------

    @RequestMapping(value = { "/{id}" }, method = RequestMethod.GET, produces = { "application/json; charset=UTF-8" },
            consumes = "application/json")
    @ResponseBody
    ComicBookDTO getComicBook(@PathVariable(value = "id") String id, HttpServletResponse httpResponse) {
        ComicBook comicBook = service.findById(id);
        if (comicBook != null) {
            httpResponse.setStatus(HttpStatus.OK.value());
            httpResponse.setDateHeader(HttpHeaders.LAST_MODIFIED, comicBook.getUpdated().getTime());
            return converter.convert(comicBook);
        } else {
            httpResponse.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }
    }

    // ------------------------------------------------------------------------
    // POST
    // ------------------------------------------------------------------------

    @RequestMapping(consumes = {"application/json"},
            produces = {"application/json; charset=UTF-8"},
            method = RequestMethod.POST)
    @ResponseBody
    public ComicBookDTO postComicBook(@RequestBody ComicBookDTO dto, HttpServletResponse httpResponse) throws ExecutionException, InterruptedException {
        ComicBook entity = service.create(converter.convert(dto));
        if (entity != null) {
            httpResponse.setStatus(HttpStatus.CREATED.value());
            httpResponse.setDateHeader(HttpHeaders.LAST_MODIFIED, entity.getUpdated().getTime());
            return converter.convert(entity);
        } else {
            httpResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return null;
        }
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public void postComicBooks() {
    }

    // ------------------------------------------------------------------------
    // DELETE
    // ------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public void deleteComicBooks() {
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteComicBook(@PathVariable(value = "id") String id, HttpServletResponse httpResponse) {
        ComicBook entity = service.findById(id);
        if (entity != null) {
            try {
                service.delete(id);
            } catch (EntityNotFoundException e) {
                LOGGER.warn("Can't delete the resource: " + entity, e);
                httpResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
            httpResponse.setStatus(HttpStatus.NO_CONTENT.value());
        } else {
            httpResponse.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return null;
    }

    // ------------------------------------------------------------------------
    // PUT
    // ------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public void putComicBooks() {
    }

    @RequestMapping(value = {"/{id}"},
            consumes = {"application/json"},
            produces = {"application/json; charset=UTF-8"},
            method = RequestMethod.PUT)
    @ResponseBody
    public Object putComicBook(@PathVariable("id") String id, @RequestBody ComicBookDTO dto, HttpServletResponse httpResponse) throws ExecutionException, InterruptedException {
        ComicBook entity = service.findById(id);
        if (entity != null) {
            converter.updateEntity(entity, dto);
            try {
                ComicBook result = service.update(entity);
                httpResponse.setDateHeader(HttpHeaders.LAST_MODIFIED, ((ComicBook) result).getUpdated().getTime());
                return converter.convert(result);
            } catch (EntityNotFoundException e) {
                LOGGER.warn("Can't modify the resource: " + entity, e);
                httpResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                return null;
            }
        } else {
            httpResponse.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }
    }

    // ------------------------------------------------------------------------
    // OPTIONS
    // ------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.OPTIONS)
    @ResponseStatus(HttpStatus.OK)
    public void optionsComicBooks(HttpServletResponse httpResponse) {
        httpResponse.addHeader(HttpHeaders.ALLOW, "HEAD,GET,OPTIONS,POST");
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.OPTIONS)
    @ResponseStatus(HttpStatus.OK)
    public void optionsComicBook(HttpServletResponse httpResponse) {
        httpResponse.addHeader(HttpHeaders.ALLOW, "HEAD,GET,PUT,DELETE,OPTIONS");
    }

    @Override
    public GenericBusinessService<ComicBook> getBusinessService() {
        return service;
    }

    @Override
    public GenericConverter<ComicBook, ComicBookDTO> getConverter() {
        return converter;
    }
}
