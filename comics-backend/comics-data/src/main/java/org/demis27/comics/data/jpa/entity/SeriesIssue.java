package org.demis27.comics.data.jpa.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "series_issue")
public class SeriesIssue  extends AbstractEntity implements EntityInterface {

    private String id;

    private int number;

    private String label;

    @Id
    @Column(name = "series_issue_id", precision = 10)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "number_in_series", nullable = false, unique = false)
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Column(name = "label", nullable = false, unique = false, length = 4)
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
