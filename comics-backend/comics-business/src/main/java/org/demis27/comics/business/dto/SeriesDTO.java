package org.demis27.comics.business.dto;

public class SeriesDTO extends AbstractDTO implements DTO {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
