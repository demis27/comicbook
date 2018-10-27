package org.demis27.comics.business.converter;

import org.demis27.comics.business.dto.DTO;
import org.demis27.comics.data.jpa.entity.EntityInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class GenericConverter<EntityImpl extends EntityInterface, DTOImpl extends DTO> {

    protected final Class<EntityImpl> entityClass;

    protected final Class<DTOImpl> dtoClass;

    public GenericConverter(Class<EntityImpl> entityClass, Class<DTOImpl> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    protected abstract void copyAttributes(EntityImpl entity, DTOImpl dto);

    public DTOImpl convert(EntityImpl entity) {
        DTOImpl dto = null;

        try {
            dto = dtoClass.newInstance();
            copyAttributes(entity, dto);
            dto.setId(entity.getId());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return dto;
    }

    public List<DTOImpl> convertEntities(List<EntityImpl> entities) {
        return entities.stream().map(this::convert).collect(Collectors.toList());
    }

    protected abstract void copyAttributes(DTOImpl dto, EntityImpl entity);

    public EntityImpl convert(DTOImpl dto) {
        EntityImpl entity = null;

        try {
            entity = entityClass.newInstance();
            copyAttributes(dto, entity);
            entity.setId(dto.getId());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return entity;
    }

    public List<EntityImpl> convertDtos(List<DTOImpl> dtos) {
        List<EntityImpl> entities = new ArrayList<>(dtos.size());

        entities.addAll(dtos.stream().map(this::convert).collect(Collectors.toList()));

        return entities;
    }

    public abstract void updateEntity(EntityImpl entity, DTOImpl dto);

}
