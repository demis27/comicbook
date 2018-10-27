package org.demis27.comics.comicbook;

import org.demis27.comics.business.BusinessService;
import org.demis27.comics.business.converter.GenericConverter;
import org.demis27.comics.business.dto.DTO;
import org.demis27.comics.data.jpa.entity.ComicBook;
import org.demis27.comics.data.jpa.entity.EntityInterface;
import org.demis27.comics.paging.range.Range;
import org.demis27.comics.paging.range.RangeException;
import org.demis27.comics.paging.sort.SortParameterElement;
import org.demis27.comics.web.ControllerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.ExecutionException;

public abstract class GenericController<EntityImpl extends EntityInterface, DTOImpl extends DTO> {

    @Autowired
    private ControllerHelper helper;

//    @RequestMapping(method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"},
//            consumes = "application/json")
//    @ResponseBody
//    public List<DTOImpl> get(@RequestParam(value = "sort", required = false) String sortParameters,
//                             @RequestParam(value = "search", required = false) String searchParameters, HttpServletRequest request,
//                             HttpServletResponse response)
//            throws ExecutionException, InterruptedException, RangeException {
//        response.setHeader(HttpHeaders.ACCEPT_RANGES, "resources");
//
//        List<DTOImpl> dtos = null;
//        Range range = helper.getRange(request.getHeader("Range"));
//        List<SortParameterElement> sorts = helper.getSorts(sortParameters);
//
//        List<EntityImpl> entities;
//        if (searchParameters != null && !searchParameters.isEmpty()) {
//            entities = getBusinessService().searchEverywhere(searchParameters, range, sorts);
//        } else {
//            entities = getBusinessService().findPart(range, sorts);
//        }
//        if (entities.isEmpty()) {
//            response.setStatus(HttpStatus.NO_CONTENT.value());
//        } else {
//            response.setHeader(HttpHeaders.CONTENT_RANGE,
//                    "resources " + range.getStart() + "-" + Math.min(range.getEnd(), entities.size()) + "/*");
//            response.setStatus(HttpStatus.OK.value());
//            dtos = getConverter().convertEntities(entities);
//        }
//        return dtos;
//    }

    public abstract BusinessService getBusinessService();

    public abstract GenericConverter getConverter();

}
