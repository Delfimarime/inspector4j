package org.inspector4j.api;

import org.inspector4j.api.InspectorException;

public class UnsupportedTypeException extends InspectorException {

    public UnsupportedTypeException() {
    }

    public UnsupportedTypeException(String message) {
        super(message);
    }

    public UnsupportedTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedTypeException(Throwable cause) {
        super(cause);
    }

}