package org.inspector4j.api;

public class InspectorException extends RuntimeException {
    public InspectorException() {
    }

    public InspectorException(String message) {
        super(message);
    }

    public InspectorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InspectorException(Throwable cause) {
        super(cause);
    }
}
