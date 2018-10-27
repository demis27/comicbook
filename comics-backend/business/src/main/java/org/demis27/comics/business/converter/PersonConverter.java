package org.demis27.comics.business.converter;

import org.demis27.comics.business.dto.PersonDTO;
import org.demis27.comics.data.jpa.entity.Person;

public class PersonConverter extends GenericConverter<Person, PersonDTO> {

    public PersonConverter() {
        super(Person.class, PersonDTO.class);
    }

    @Override
    protected void copyAttributes(Person entity, PersonDTO dto) {
        dto.setFirstname(entity.getFirstname());
        dto.setLastname(entity.getLastname());
        dto.setMiddlename(entity.getMiddlename());
        dto.setCreated(entity.getCreated());
        dto.setUpdated(entity.getUpdated());
    }

    @Override
    protected void copyAttributes(PersonDTO dto, Person entity) {
        entity.setFirstname(dto.getFirstname());
        entity.setLastname(dto.getLastname());
        entity.setMiddlename(dto.getMiddlename());
    }

    public void updateEntity(Person entity, PersonDTO dto) {
        entity.setFirstname(dto.getFirstname());
        entity.setLastname(dto.getLastname());
        entity.setMiddlename(dto.getMiddlename());
    }
}
