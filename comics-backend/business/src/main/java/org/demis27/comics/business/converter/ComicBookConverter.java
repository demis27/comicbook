package org.demis27.comics.business.converter;

import org.demis27.comics.business.dto.ComicBookDTO;
import org.demis27.comics.data.jpa.entity.ComicBook;
import org.springframework.stereotype.Service;

@Service(value="comicBookConverter")
public class ComicBookConverter extends GenericConverter<ComicBook, ComicBookDTO> {

    public ComicBookConverter() {
        super(ComicBook.class, ComicBookDTO.class);
    }

    @Override
    protected void copyAttributes(ComicBook entity, ComicBookDTO dto) {
        dto.setTitle(entity.getTitle());
    }

    @Override
    protected void copyAttributes(ComicBookDTO dto, ComicBook entity) {
        entity.setTitle(dto.getTitle());
    }
}
