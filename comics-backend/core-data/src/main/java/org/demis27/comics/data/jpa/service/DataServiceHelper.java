package org.demis27.comics.data.jpa.service;

import org.demis27.comics.paging.range.Range;
import org.demis27.comics.paging.sort.SortParameterElement;
import org.demis27.comics.paging.sort.SortParameterElementConverter;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceHelper {

    public PageRequest getPageRequest(Range range, List<SortParameterElement> sorts) {
        if (sorts.isEmpty()) {
            return PageRequest.of(range.getPage(), range.getSize());
        }
        else {
            return PageRequest.of(range.getPage(), range.getSize(), SortParameterElementConverter.convert(sorts));
        }
    }
}
