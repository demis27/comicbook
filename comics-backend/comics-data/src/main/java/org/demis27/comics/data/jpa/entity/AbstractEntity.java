package org.demis27.comics.data.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class AbstractEntity {

    private Date created;

    private Date updated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modification_date", nullable = false, updatable = true)
    public Date getUpdated() {
        if (created == null) {
            onCreate();
        }
        return new Date(updated.getTime());
    }

    public void setUpdated(Date updated) {
        this.updated = new Date(updated.getTime());
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false, updatable = false)
    public Date getCreated() {
        if (created == null) {
            onCreate();
        }
        return new Date(created.getTime());
    }

    public void setCreated(Date created) {
        this.created = new Date(created.getTime());
    }

    @PrePersist
    protected void onCreate() {
        updated = created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }
}
