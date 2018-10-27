package org.demis27.comics.paging.sort;

public class SortParameterElement {
    private final String property;

    private final boolean ascending;

    public SortParameterElement(String property, boolean ascending) {
        this.property = property;
        this.ascending = ascending;
    }

    public String getProperty() {
        return property;
    }

    public boolean isAscending() {
        return ascending;
    }
}
