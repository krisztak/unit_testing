package com.example.layering.data.impl.client.exception;

public class UnableToSaveClientException extends RuntimeException {

    public UnableToSaveClientException() {
        super("Invalid client details, unable to save");
    }
}
