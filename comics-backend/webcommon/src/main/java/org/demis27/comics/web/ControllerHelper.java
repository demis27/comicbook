package org.demis27.comics.web;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.demis27.comics.paging.range.Range;
import org.demis27.comics.paging.range.RangeConverter;
import org.demis27.comics.paging.range.RangeException;
import org.demis27.comics.paging.range.RequestedRangeUnsatisfiableException;
import org.demis27.comics.paging.sort.SortParameterElement;
import org.demis27.comics.paging.sort.SortParameterParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service("controllerHelper")
public class ControllerHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerHelper.class);

    @Autowired
    private RestConfiguration configuration;

    @Autowired
    private RangeConverter rangeConverter;

    @Autowired
    private SortParameterParser sortParameterParser;

    public Range getRange(String requestRange) throws RangeException {
        Range range;

        if (requestRange != null) {
            try {
                range = rangeConverter.parse(requestRange);
            } catch (RequestedRangeUnsatisfiableException e) {
                String reason =
                        "Wrong format for the range parameter. The format is: \"resources: page=[page-number];size=[page-size]\" and the parameter value is: "
                                + requestRange;
                throw new RangeException(reason);
            }
        } else {
            range = new Range(0, configuration.getDefaultPageSize());
        }
        return range;
    }

    public List<SortParameterElement> getSorts(String sortParameters) {
        List<SortParameterElement> sorts;
        if (sortParameters != null && sortParameters.length() > 0) {
            sorts = SortParameterParser.parse(sortParameters);
        } else {
            sorts = Collections.emptyList();
        }
        return sorts;
    }

    @ExceptionHandler(RangeException.class)
    public Object handleRangeException(HttpServletResponse httpResponse, RangeException ex) {
        LOGGER.warn(ex.getReason());
        httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        return ex;
    }
}
