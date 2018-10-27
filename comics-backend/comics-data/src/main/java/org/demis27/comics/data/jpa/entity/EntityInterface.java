package org.demis27.comics.data.jpa.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public interface EntityInterface {

    String getId();

    void setId(String id);

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modification_date", nullable = false, updatable = true)
    Date getUpdated();

    void setUpdated(Date updated);

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false, updatable = false)
    Date getCreated();

    void setCreated(Date created);
}
