package org.demis27.comics.comicbook;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.demis27.comics.business.ComicBookBusinessService;
import org.demis27.comics.business.converter.ComicBookConverter;
import org.demis27.comics.business.dto.ComicBookDTO;
import org.demis27.comics.data.jpa.entity.ComicBook;
import org.demis27.comics.paging.range.Range;
import org.demis27.comics.paging.range.RangeException;
import org.demis27.comics.paging.sort.SortParameterElement;
import org.demis27.comics.web.ControllerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/comic-book")
@RestController
public class ComicbookController {

    @Autowired
    @Qualifier("comicBookBusinessService")
    private ComicBookBusinessService service;

    @Autowired
    @Qualifier("comicBookConverter")
    private ComicBookConverter converter;

    @Autowired
    private ControllerHelper helper;

    @RequestMapping(method = RequestMethod.GET, produces = { "application/json; charset=UTF-8" },
            consumes = "application/json")
    @ResponseBody
    public List<ComicBookDTO> getComicBooks(@RequestParam(value = "sort", required = false) String sortParameters,
            @RequestParam(value = "search", required = false) String searchParameters, HttpServletRequest request,
            HttpServletResponse response)
            throws ExecutionException, InterruptedException, RangeException {
        response.setHeader(HttpHeaders.ACCEPT_RANGES, "resources");

        List<ComicBookDTO> dtos = null;
        Range range = helper.getRange(request.getHeader("Range"));
        List<SortParameterElement> sorts = helper.getSorts(sortParameters);

        List<ComicBook> entities;
        if (searchParameters != null && !searchParameters.isEmpty()) {
            entities = service.searchEverywhere(searchParameters, range, sorts);
        } else {
            entities = service.findPart(range, sorts);
        }
        if (entities.isEmpty()) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
        } else {
            response.setHeader(HttpHeaders.CONTENT_RANGE,
                    "resources " + range.getStart() + "-" + Math.min(range.getEnd(), entities.size()) + "/*");
            response.setStatus(HttpStatus.OK.value());
            dtos = converter.convertEntities(entities);
        }
        return dtos;
    }

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
}
