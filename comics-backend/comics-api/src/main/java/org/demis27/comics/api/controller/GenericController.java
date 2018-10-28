package org.demis27.comics.api.controller;

import org.demis27.comics.business.GenericBusinessService;
import org.demis27.comics.business.converter.GenericConverter;
import org.demis27.comics.business.dto.DTO;
import org.demis27.comics.data.jpa.entity.EntityInterface;
import org.demis27.comics.paging.range.Range;
import org.demis27.comics.paging.range.RangeException;
import org.demis27.comics.paging.sort.SortParameterElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.ExecutionException;

public abstract class GenericController<EntityImpl extends EntityInterface, DTOImpl extends DTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericController.class);

    @Autowired
    private ControllerHelper helper;

    // ------------------------------------------------------------------------
    // GET
    // ------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"},
            consumes = "application/json")
    @ResponseBody
    public List<DTOImpl> getAll(@RequestParam(value = "sort", required = false) String sortParameters,
                             @RequestParam(value = "search", required = false) String searchParameters, HttpServletRequest request,
                             HttpServletResponse response)
            throws ExecutionException, InterruptedException, RangeException {
        response.setHeader(HttpHeaders.ACCEPT_RANGES, "resources");

        List<DTOImpl> dtos = null;
        Range range = helper.getRange(request.getHeader("Range"));
        List<SortParameterElement> sorts = helper.getSorts(sortParameters);

        List<EntityImpl> entities;
        if (searchParameters != null && !searchParameters.isEmpty()) {
            entities = getBusinessService().searchEverywhere(searchParameters, range, sorts);
        } else {
            entities = getBusinessService().findPart(range, sorts);
        }
        if (entities.isEmpty()) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
        } else {
            response.setHeader(HttpHeaders.CONTENT_RANGE,
                    "resources " + range.getStart() + "-" + Math.min(range.getEnd(), entities.size()) + "/*");
            response.setStatus(HttpStatus.OK.value());
            dtos = getConverter().convertEntities(entities);
        }
        return dtos;
    }

    @RequestMapping(value = { "/{id}" }, method = RequestMethod.GET, produces = { "application/json; charset=UTF-8" },
            consumes = "application/json")
    @ResponseBody
    public DTOImpl get(@PathVariable(value = "id") String id, HttpServletResponse httpResponse) {
        EntityImpl entity = getBusinessService().findById(id);
        if (entity != null) {
            httpResponse.setStatus(HttpStatus.OK.value());
            httpResponse.setDateHeader(HttpHeaders.LAST_MODIFIED, entity.getUpdated().getTime());
            return getConverter().convert(entity);
        } else {
            httpResponse.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }
    }

    // ------------------------------------------------------------------------
    // POST
    // ------------------------------------------------------------------------

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public void postAll() {
    }

    @RequestMapping(consumes = {"application/json"},
            produces = {"application/json; charset=UTF-8"},
            method = RequestMethod.POST)
    @ResponseBody
    public DTOImpl post(@RequestBody DTOImpl dto, HttpServletResponse httpResponse) throws ExecutionException, InterruptedException {
        EntityImpl entity = getBusinessService().create(getConverter().convert(dto));
        if (entity != null) {
            httpResponse.setStatus(HttpStatus.CREATED.value());
            httpResponse.setDateHeader(HttpHeaders.LAST_MODIFIED, entity.getUpdated().getTime());
            return getConverter().convert(entity);
        } else {
            httpResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return null;
        }
    }

    // ------------------------------------------------------------------------
    // DELETE
    // ------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public void deleteAlls() {
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@PathVariable(value = "id") String id, HttpServletResponse httpResponse) {
        EntityImpl entity = getBusinessService().findById(id);
        if (entity != null) {
            try {
                getBusinessService().delete(id);
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
    public void putAlls() {
    }

    @RequestMapping(value = {"/{id}"},
            consumes = {"application/json"},
            produces = {"application/json; charset=UTF-8"},
            method = RequestMethod.PUT)
    @ResponseBody
    public Object put(@PathVariable("id") String id, @RequestBody DTOImpl dto, HttpServletResponse httpResponse) throws ExecutionException, InterruptedException {
        EntityImpl entity = getBusinessService().findById(id);
        if (entity != null) {
            getConverter().updateEntity(entity, dto);
            try {
                EntityImpl result = getBusinessService().update(entity);
                httpResponse.setDateHeader(HttpHeaders.LAST_MODIFIED, result.getUpdated().getTime());
                return getConverter().convert(result);
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
    public void optionsAll(HttpServletResponse httpResponse) {
        httpResponse.addHeader(HttpHeaders.ALLOW, "HEAD,GET,OPTIONS");
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.OPTIONS)
    @ResponseStatus(HttpStatus.OK)
    public void options(HttpServletResponse httpResponse) {
        httpResponse.addHeader(HttpHeaders.ALLOW, "HEAD,GET,PUT,DELETE,OPTIONS");
    }


    public abstract GenericBusinessService<EntityImpl> getBusinessService();

    public abstract GenericConverter<EntityImpl, DTOImpl> getConverter();

}
