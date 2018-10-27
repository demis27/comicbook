package org.demis27.comics.paging.sort;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service("sortParameterElementConverter")
public class SortParameterElementConverter {

    public static org.springframework.data.domain.Sort convert(List<SortParameterElement> sorts) {
        List<org.springframework.data.domain.Sort.Order> dataSorts = sorts
                .stream()
                .map(sort -> new org.springframework.data.domain.Sort.Order(
                        sort.isAscending() ? org.springframework.data.domain.Sort.Direction.ASC
                                : org.springframework.data.domain.Sort.Direction.DESC,
                        sort.getProperty()))
                .collect(Collectors.toList());
        if (dataSorts.isEmpty()) {
            return null;
        } else {
            return org.springframework.data.domain.Sort.by(dataSorts);
        }
    }
}
