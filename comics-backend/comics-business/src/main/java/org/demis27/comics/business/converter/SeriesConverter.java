package org.demis27.comics.business.converter;

import org.demis27.comics.business.dto.SeriesDTO;
import org.demis27.comics.data.jpa.entity.Series;
import org.springframework.stereotype.Service;

@Service
public class SeriesConverter extends GenericConverter<Series, SeriesDTO>{

    public SeriesConverter() {
        super(Series.class, SeriesDTO.class);
    }

    @Override
    protected void copyAttributes(Series entity, SeriesDTO dto) {
        dto.setTitle(entity.getTitle());
        dto.setCreated(entity.getCreated());
        dto.setUpdated(entity.getUpdated());
    }

    @Override
    protected void copyAttributes(SeriesDTO dto, Series entity) {
        entity.setTitle(dto.getTitle());
    }

    @Override
    public void updateEntity(Series entity, SeriesDTO dto) {
        entity.setTitle(dto.getTitle());
    }
}
