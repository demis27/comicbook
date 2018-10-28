package org.demis27.comics.api;

public class RestError {

    private final String message;

    public RestError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
